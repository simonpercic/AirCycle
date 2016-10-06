package com.github.simonpercic.aircycle.manager;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.aircycle.AirCycleConfig;
import com.github.simonpercic.aircycle.BaseAirCycle;
import com.github.simonpercic.aircycle.model.FieldListenerMethods;
import com.github.simonpercic.aircycle.model.ListenerMethod;
import com.github.simonpercic.aircycle.model.type.ActivityLifecycleType;
import com.github.simonpercic.aircycle.model.type.ListenerArgType;
import com.github.simonpercic.collectionhelper.CollectionHelper;
import com.github.simonpercic.collectionhelper.Predicate;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * AirCycle listener class type spec generator.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
public class ClassGenerator {

    private static final String CLASS_SUFFIX = AirCycle.class.getSimpleName();
    private static final String ACTIVITY_PARAM = "activity";
    private static final String BUNDLE_PARAM = "bundle";
    private static final String CONFIG_PARAM = "config";
    private static final String REGISTER_CALLBACKS_METHOD = ".registerCallbacks()";

    private static final String CLASS_COMMENT = "Generated class from AirCycle, do not modify.\n"
            + "See: https://github.com/simonpercic/AirCycle\n"
            + "\n"
            + "@author Simon Percic <a href=\"https://github.com/simonpercic\">https://github.com/simonpercic</a>\n";

    private static final String BIND_JAVADOC = "Bind Activity's lifecycle callbacks to all of its AirCycles.\n"
            + "\n"
            + "@param $N the activity instance to bind\n";

    private static final String BIND_CONFIG_JAVADOC = BIND_JAVADOC
            + "@param $N the optional AirCycle config\n";

    private final Elements elementUtils;
    private final Types typeUtils;

    @Inject
    public ClassGenerator(Elements elementUtils, Types typeUtils) {
        this.elementUtils = elementUtils;
        this.typeUtils = typeUtils;
    }

    public TypeSpec generateClass(TypeElement enclosingElement,
            Map<ActivityLifecycleType, List<FieldListenerMethods>> methods) {
        if (enclosingElement == null) {
            throw new IllegalArgumentException("enclosingElement is null");
        }

        if (methods == null) {
            throw new IllegalArgumentException("methods list is null");
        }

        ClassName enclosingActivityClass = ClassName.get(enclosingElement);
        String activityName = enclosingElement.getSimpleName().toString();

        ParameterizedTypeName superClass = ParameterizedTypeName.get(ClassName.get(BaseAirCycle.class),
                enclosingActivityClass);

        String className = activityName + CLASS_SUFFIX;
        TypeSpec.Builder builder = TypeSpec.classBuilder(className)
                .superclass(superClass)
                .addJavadoc(CLASS_COMMENT);

        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .addParameter(enclosingActivityClass, ACTIVITY_PARAM)
                .addParameter(ClassName.get(AirCycleConfig.class), CONFIG_PARAM)
                .addStatement("super($N, $N)", ACTIVITY_PARAM, CONFIG_PARAM)
                .build();

        builder.addMethod(constructor);

        for (ActivityLifecycleType lifecycle : methods.keySet()) {
            List<FieldListenerMethods> fieldListenerMethods = methods.get(lifecycle);
            MethodSpec lifecycleMethod = createLifecycleMethod(lifecycle, fieldListenerMethods, enclosingElement);
            builder.addMethod(lifecycleMethod);
        }

        MethodSpec bind = bindMethodSpec(enclosingActivityClass)
                .addStatement("new $L($N, null)" + REGISTER_CALLBACKS_METHOD, className, ACTIVITY_PARAM)
                .addJavadoc(BIND_JAVADOC, ACTIVITY_PARAM)
                .build();

        builder.addMethod(bind);

        MethodSpec bindConfig = bindMethodSpec(enclosingActivityClass)
                .addParameter(ClassName.get(AirCycleConfig.class), CONFIG_PARAM)
                .addStatement("new $L($N, $N)" + REGISTER_CALLBACKS_METHOD, className, ACTIVITY_PARAM, CONFIG_PARAM)
                .addJavadoc(BIND_CONFIG_JAVADOC, ACTIVITY_PARAM, CONFIG_PARAM)
                .build();

        builder.addMethod(bindConfig);

        return builder.build();
    }

    private static Builder bindMethodSpec(ClassName enclosingActivityClass) {
        return MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.STATIC)
                .addParameter(enclosingActivityClass, ACTIVITY_PARAM);
    }

    private MethodSpec createLifecycleMethod(
            final ActivityLifecycleType lifecycle,
            List<FieldListenerMethods> fieldListenerMethods,
            TypeElement enclosingElement) {

        if (lifecycle == null) {
            throw new IllegalArgumentException("lifecycle is null");
        }

        if (CollectionHelper.isEmpty(fieldListenerMethods)) {
            throw new IllegalArgumentException("fieldListenerMethods is empty");
        }

        TypeElement typeElement = elementUtils.getTypeElement(BaseAirCycle.class.getCanonicalName());
        DeclaredType declaredType = typeUtils.getDeclaredType(typeElement, enclosingElement.asType());

        List<? extends Element> allMembers = elementUtils.getAllMembers((TypeElement) declaredType.asElement());
        List<ExecutableElement> allMethods = ElementFilter.methodsIn(allMembers);

        ExecutableElement baseMethod = CollectionHelper.first(allMethods, new Predicate<ExecutableElement>() {
            @Override public boolean apply(ExecutableElement object) {
                String methodName = getBaseAirCycleNotifyMethodName(lifecycle);
                return object.getSimpleName().toString().equals(methodName);
            }
        });

        MethodSpec.Builder methodBuilder = MethodSpec.overriding(baseMethod, declaredType, typeUtils);

        for (FieldListenerMethods listenerMethod : fieldListenerMethods) {
            String fieldName = listenerMethod.getField().getSimpleName().toString();

            methodBuilder.beginControlFlow("if ($N.$N != null)", ACTIVITY_PARAM, fieldName);

            for (ListenerMethod method : listenerMethod.getListenerMethods()) {
                String paramsCall = paramsCall(method.getArgs());
                methodBuilder.addStatement("$N.$N.$N$L", ACTIVITY_PARAM, fieldName, method.getMethodName(), paramsCall);
            }

            methodBuilder.endControlFlow();
        }


        return methodBuilder.build();
    }

    private static String paramsCall(List<ListenerArgType> methodArgs) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');

        if (!CollectionHelper.isEmpty(methodArgs)) {
            for (int i = 0; i < methodArgs.size(); i++) {
                ListenerArgType arg = methodArgs.get(i);

                if (i > 0) {
                    sb.append(", ");
                }

                String argName = argNameForListenerArg(arg);
                sb.append(argName);
            }
        }

        sb.append(')');
        return sb.toString();
    }

    private static String argNameForListenerArg(ListenerArgType arg) {
        switch (arg) {
            case BUNDLE:
                return BUNDLE_PARAM;
            case ACTIVITY:
                return ACTIVITY_PARAM;
            default:
                throw new IllegalArgumentException("invalid arg");
        }
    }

    private static String getBaseAirCycleNotifyMethodName(ActivityLifecycleType lifecycleType) {
        switch (lifecycleType) {
            case CREATE:
                return "notifyOnActivityCreated";
            case START:
                return "notifyOnActivityStarted";
            case RESUME:
                return "notifyOnActivityResumed";
            case PAUSE:
                return "notifyOnActivityPaused";
            case STOP:
                return "notifyOnActivityStopped";
            case SAVE_INSTANCE_STATE:
                return "notifyOnActivitySaveInstanceState";
            case DESTROY:
                return "notifyOnActivityDestroyed";
            default:
                throw new IllegalArgumentException("unknown lifecycle type");
        }
    }
}
