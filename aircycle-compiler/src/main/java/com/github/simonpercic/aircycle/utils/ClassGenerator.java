package com.github.simonpercic.aircycle.utils;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.aircycle.BaseAirCycle;
import com.github.simonpercic.aircycle.model.LifecycleMethod;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class ClassGenerator {

    private static final String CLASS_SUFFIX = AirCycle.class.getSimpleName();

    private ClassGenerator() {
        // no instance
    }

    public static TypeSpec generateClass(VariableElement field, List<LifecycleMethod> methods) {
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
                .addParameter(enclosingActivityClass, "activity")
                .addStatement("super($N)", "activity")
                .build();

        builder.addMethod(constructor);

        return builder.build();
    }
}
