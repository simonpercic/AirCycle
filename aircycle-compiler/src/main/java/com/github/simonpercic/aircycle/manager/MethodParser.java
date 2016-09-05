package com.github.simonpercic.aircycle.manager;

import com.github.simonpercic.aircycle.model.LifecycleMethod;
import com.github.simonpercic.aircycle.model.type.ActivityLifecycle;
import com.github.simonpercic.aircycle.utils.ElementValidator;
import com.github.simonpercic.collectionhelper.CollectionHelper;

import java.util.ArrayList;
import java.util.List;

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

    public List<LifecycleMethod> parseLifecycleMethods(TypeElement element) {
        List<LifecycleMethod> lifecycleMethods = new ArrayList<>();

        List<? extends Element> allMembers = elementUtils.getAllMembers(element);
        List<ExecutableElement> allMethods = ElementFilter.methodsIn(allMembers);

        for (ExecutableElement method : allMethods) {
            if (!isLifecycleMethod(method)) {
                continue;
            }

            LifecycleMethod lifecycleMethod = parseLifecycleMethod(method, element);
            if (lifecycleMethod == null) {
                return null;
            }

            lifecycleMethods.add(lifecycleMethod);
        }

        if (lifecycleMethods.size() == 0) {
            // TODO: 04/09/16 detailed error message
            String message = String.format("`%s` does not contain any Activity lifecycle methods.",
                    element.getQualifiedName());

            logger.w(message, element);
        }

        return lifecycleMethods;
    }

    private LifecycleMethod parseLifecycleMethod(ExecutableElement method, TypeElement enclosingElement) {
        if (method == null) {
            throw new IllegalArgumentException("method is null");
        }

        String methodName = method.getSimpleName().toString();
        ActivityLifecycle activityLifecycle = parseLifecycle(methodName);

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

        LifecycleMethod.Builder builder = LifecycleMethod.builder(activityLifecycle, methodName);

        List<? extends VariableElement> parameters = method.getParameters();

        if (!CollectionHelper.isEmpty(parameters)) {
            TypeMirror bundleType = elementUtils.getTypeElement("android.os.Bundle").asType();

            for (VariableElement parameter : parameters) {
                TypeMirror paramType = parameter.asType();

                if (typeUtils.isSameType(paramType, bundleType)) {
                    builder.addBundleArg();
                }
            }
        }

        return builder.build();
    }

    private static boolean isLifecycleMethod(ExecutableElement method) {
        if (method == null) {
            throw new IllegalArgumentException("method is null");
        }

        ActivityLifecycle activityLifecycle = parseLifecycle(method.getSimpleName().toString());
        return activityLifecycle != null;
    }

    private static ActivityLifecycle parseLifecycle(String methodName) {
        switch (methodName) {
            case "onCreate":
            case "onActivityCreated":
                return ActivityLifecycle.CREATE;
            case "onStart":
            case "onActivityStarted":
                return ActivityLifecycle.START;
            case "onResume":
            case "onActivityResumed":
                return ActivityLifecycle.RESUME;
            case "onPause":
            case "onActivityPaused":
                return ActivityLifecycle.PAUSE;
            case "onStop":
            case "onActivityStopped":
                return ActivityLifecycle.STOP;
            case "onSaveInstanceState":
            case "onActivitySaveInstanceState":
                return ActivityLifecycle.SAVE_INSTANCE_STATE;
            case "onDestroy":
            case "onActivityDestroyed":
                return ActivityLifecycle.DESTROY;
            default:
                return null;
        }
    }
}
