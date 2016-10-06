package com.github.simonpercic.aircycle.manager;

import android.app.Activity;
import android.os.Bundle;

import com.github.simonpercic.aircycle.exception.MethodArgumentsException;
import com.github.simonpercic.aircycle.model.ListenerMethod;
import com.github.simonpercic.aircycle.model.type.ActivityLifecycleType;
import com.github.simonpercic.aircycle.utils.ElementValidator;
import com.github.simonpercic.collectionhelper.CollectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Listener method parser.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
public class MethodParser {

    private final ProcessorLogger logger;
    private final Elements elementUtils;
    private final Types typeUtils;

    @Inject
    public MethodParser(ProcessorLogger logger, Elements elementUtils, Types typeUtils) {
        this.logger = logger;
        this.elementUtils = elementUtils;
        this.typeUtils = typeUtils;
    }

    public Map<ActivityLifecycleType, List<ListenerMethod>> parseLifecycleMethods(TypeElement element,
            List<ExecutableElement> ignoredMethods, List<ActivityLifecycleType> ignoreLifecycleCallbacks) {

        Map<ActivityLifecycleType, List<ListenerMethod>> lifecycleMethods = new TreeMap<>();

        List<? extends Element> allMembers = elementUtils.getAllMembers(element);
        List<ExecutableElement> allMethods = ElementFilter.methodsIn(allMembers);

        for (ExecutableElement method : allMethods) {
            if (!isLifecycleMethod(method)) {
                continue;
            }

            if (ignoredMethods != null && ignoredMethods.contains(method)) {
                continue;
            }

            if (!CollectionHelper.isEmpty(ignoreLifecycleCallbacks)) {
                String methodName = method.getSimpleName().toString();
                ActivityLifecycleType activityLifecycle = parseLifecycle(methodName);

                if (ignoreLifecycleCallbacks.contains(activityLifecycle)) {
                    continue;
                }
            }

            LifecycleMethod lifecycleMethod = parseLifecycleMethod(method, element);
            if (lifecycleMethod == null) {
                return null;
            }

            List<ListenerMethod> methods = lifecycleMethods.get(lifecycleMethod.lifecycleType);

            if (methods == null) {
                methods = new ArrayList<>();
                lifecycleMethods.put(lifecycleMethod.lifecycleType, methods);
            }

            methods.add(lifecycleMethod.listenerMethod);
        }

        if (lifecycleMethods.size() == 0) {
            String validMethods = "Valid lifecycle methods are: \n"
                    + "onCreate() / onActivityCreated(), \n"
                    + "onStart() / onActivityStarted(), \n"
                    + "onResume() / onActivityResumed(), \n"
                    + "onPause() / onActivityPaused(), \n"
                    + "onStop() / onActivityStopped(), \n"
                    + "onSaveInstanceState() / onActivitySaveInstanceState(), \n"
                    + "onDestroy() / onActivityDestroyed()";

            String message = String.format("`%s` does not contain any Activity lifecycle methods. %s",
                    element.getQualifiedName(), validMethods);

            logger.w(message, element);
        }

        return lifecycleMethods;
    }

    private LifecycleMethod parseLifecycleMethod(ExecutableElement method, TypeElement enclosingElement) {
        if (method == null) {
            throw new IllegalArgumentException("method is null");
        }

        String methodName = method.getSimpleName().toString();
        ActivityLifecycleType activityLifecycle = parseLifecycle(methodName);

        if (activityLifecycle == null) {
            return null;
        }

        if (!ElementValidator.isPublic(method)) {
            String message = String.format("Method `%s` in `%s` is not public.",
                    methodName,
                    enclosingElement.getQualifiedName());

            logger.e(message, enclosingElement);
            return null;
        }

        if (method.isVarArgs()) {
            String message = String.format("Method `%s` in `%s` accepts a variable number of arguments.",
                    methodName,
                    enclosingElement.getQualifiedName());

            logger.e(message, enclosingElement);
            return null;
        }

        ListenerMethod.Builder builder = ListenerMethod.builder(activityLifecycle, methodName);

        List<? extends VariableElement> parameters = method.getParameters();

        if (!CollectionHelper.isEmpty(parameters)) {
            TypeMirror bundleType = elementUtils.getTypeElement(Bundle.class.getCanonicalName()).asType();
            TypeMirror activityType = elementUtils.getTypeElement(Activity.class.getCanonicalName()).asType();

            for (int i = 0; i < parameters.size(); i++) {
                VariableElement parameter = parameters.get(i);
                TypeMirror paramType = parameter.asType();

                try {
                    if (typeUtils.isSubtype(paramType, activityType)) {
                        builder.addActivityArg();
                    } else if (typeUtils.isSameType(paramType, bundleType)) {
                        builder.addBundleArg();
                    } else {
                        String message = String.format(
                                "Invalid method argument of type `%s` at index `%s` in `%s",
                                paramType.toString(), i, methodName);

                        logger.e(message, method);
                    }
                } catch (MethodArgumentsException e) {
                    logger.e(e.getMessage(), method);
                    return null;
                }
            }
        }

        return new LifecycleMethod(activityLifecycle, builder.build());
    }

    private static boolean isLifecycleMethod(ExecutableElement method) {
        if (method == null) {
            throw new IllegalArgumentException("method is null");
        }

        ActivityLifecycleType activityLifecycle = parseLifecycle(method.getSimpleName().toString());
        return activityLifecycle != null;
    }

    private static ActivityLifecycleType parseLifecycle(String methodName) {
        switch (methodName) {
            case "onCreate":
            case "onActivityCreated":
                return ActivityLifecycleType.CREATE;
            case "onStart":
            case "onActivityStarted":
                return ActivityLifecycleType.START;
            case "onResume":
            case "onActivityResumed":
                return ActivityLifecycleType.RESUME;
            case "onPause":
            case "onActivityPaused":
                return ActivityLifecycleType.PAUSE;
            case "onStop":
            case "onActivityStopped":
                return ActivityLifecycleType.STOP;
            case "onSaveInstanceState":
            case "onActivitySaveInstanceState":
                return ActivityLifecycleType.SAVE_INSTANCE_STATE;
            case "onDestroy":
            case "onActivityDestroyed":
                return ActivityLifecycleType.DESTROY;
            default:
                return null;
        }
    }

    private static class LifecycleMethod {

        final ActivityLifecycleType lifecycleType;
        final ListenerMethod listenerMethod;

        LifecycleMethod(ActivityLifecycleType lifecycleType, ListenerMethod listenerMethod) {
            this.lifecycleType = lifecycleType;
            this.listenerMethod = listenerMethod;
        }
    }
}
