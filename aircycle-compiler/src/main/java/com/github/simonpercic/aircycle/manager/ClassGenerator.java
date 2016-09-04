package com.github.simonpercic.aircycle.manager;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.aircycle.BaseAirCycle;
import com.github.simonpercic.aircycle.model.LifecycleMethod;
import com.github.simonpercic.aircycle.model.type.ActivityLifecycle;
import com.github.simonpercic.collectionhelper.CollectionHelper;
import com.github.simonpercic.collectionhelper.Predicate;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
public class ClassGenerator {

    private static final String CLASS_SUFFIX = AirCycle.class.getSimpleName();
    private static final String ACTIVITY_PARAM = "activity";

    private final Elements elementUtils;
    private final Types typeUtils;

    @Inject
    public ClassGenerator(Elements elementUtils, Types typeUtils) {
        this.elementUtils = elementUtils;
        this.typeUtils = typeUtils;
    }

    public TypeSpec generateClass(VariableElement field, List<LifecycleMethod> methods) {
        if (field == null) {
            throw new IllegalArgumentException("field is null");
        }

        if (methods == null) {
            throw new IllegalArgumentException("methods list is null");
        }

        TypeElement enclosingElement = (TypeElement) field.getEnclosingElement();
        ClassName enclosingActivityClass = ClassName.get(enclosingElement);
        String activityName = enclosingElement.getSimpleName().toString();

        ParameterizedTypeName superClass = ParameterizedTypeName.get(ClassName.get(BaseAirCycle.class),
                enclosingActivityClass);

        // TODO: 04/09/16 add comment to generated class

        TypeSpec.Builder builder = TypeSpec.classBuilder(activityName + CLASS_SUFFIX)
                .addModifiers(Modifier.PUBLIC)
                .superclass(superClass);

        MethodSpec constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PROTECTED)
                .addParameter(enclosingActivityClass, ACTIVITY_PARAM)
                .addStatement("super($N)", ACTIVITY_PARAM)
                .build();

        builder.addMethod(constructor);

        for (LifecycleMethod method : methods) {
            MethodSpec lifecycleMethod = createLifecycleMethod(method, enclosingElement, field);
            builder.addMethod(lifecycleMethod);
        }

        return builder.build();
    }

    private MethodSpec createLifecycleMethod(final LifecycleMethod method, TypeElement enclosingElement,
            VariableElement field) {

        if (method == null) {
            throw new IllegalArgumentException("method is null");
        }

        TypeElement typeElement = elementUtils.getTypeElement(BaseAirCycle.class.getCanonicalName());
        DeclaredType declaredType = typeUtils.getDeclaredType(typeElement, enclosingElement.asType());

        List<? extends Element> allMembers = elementUtils.getAllMembers((TypeElement) declaredType.asElement());
        List<ExecutableElement> allMethods = ElementFilter.methodsIn(allMembers);

        ExecutableElement baseMethod = CollectionHelper.first(allMethods, new Predicate<ExecutableElement>() {
            @Override public boolean apply(ExecutableElement object) {
                String methodName = getBaseAirCycleNotifyMethodName(method.getLifecycleType());
                return object.getSimpleName().toString().equals(methodName);
            }
        });

        String fieldName = field.getSimpleName().toString();

        // TODO: 04/09/16 add args to call

        MethodSpec.Builder methodBuilder = MethodSpec.overriding(baseMethod, declaredType, typeUtils);
        methodBuilder.beginControlFlow("if ($N.$N != null)", ACTIVITY_PARAM, fieldName);
        methodBuilder.addStatement("$N.$N.$N()", ACTIVITY_PARAM, fieldName, method.getMethodName());
        methodBuilder.endControlFlow();

        return methodBuilder.build();
    }

    private static String getBaseAirCycleNotifyMethodName(ActivityLifecycle lifecycleType) {
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
