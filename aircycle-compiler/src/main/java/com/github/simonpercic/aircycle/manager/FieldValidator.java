package com.github.simonpercic.aircycle.manager;

import android.app.Activity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.aircycle.utils.ElementValidator;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Listener field validator.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
public class FieldValidator {

    private final ProcessorLogger logger;
    private final Elements elementUtils;
    private final Types typeUtils;

    @Inject
    public FieldValidator(ProcessorLogger logger, Elements elementUtils, Types typeUtils) {
        this.logger = logger;
        this.elementUtils = elementUtils;
        this.typeUtils = typeUtils;
    }

    public boolean isFieldValid(VariableElement field) {
        if (ElementValidator.isPrivate(field)) {
            String message = String.format("Fields annotated with @%s must not be private.",
                    AirCycle.class.getSimpleName());

            logger.e(message, field);
            return false;
        }

        TypeElement fieldEnclosingElement = (TypeElement) field.getEnclosingElement();
        TypeMirror enclosingType = fieldEnclosingElement.asType();
        TypeMirror activityType = elementUtils.getTypeElement(Activity.class.getCanonicalName()).asType();

        if (!typeUtils.isSubtype(enclosingType, activityType)) {
            String message = String.format("Class `%s` enclosing the field `%s` is not a subtype of an Activity.",
                    fieldEnclosingElement.getQualifiedName(),
                    field.getSimpleName());

            logger.e(message, fieldEnclosingElement);
            return false;
        }

        TypeMirror typeMirror = field.asType();
        if (typeMirror.getKind() != TypeKind.DECLARED || !(typeMirror instanceof DeclaredType)) {
            String message = String.format("Field `%s` must be declared.", field.getSimpleName());
            logger.e(message, fieldEnclosingElement);
            return false;
        }

        DeclaredType declaredType = (DeclaredType) typeMirror;
        if (!(declaredType.asElement() instanceof TypeElement)) {
            String message = String.format("Field `%s` is of invalid type.", field.getSimpleName());
            logger.e(message, fieldEnclosingElement);
            return false;
        }

        return true;
    }
}
