package com.github.simonpercic.aircycle.utils;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class ElementValidator {

    private ElementValidator() {
        // no instance
    }

    public static boolean isPublic(Element element) {
        return hasModifier(element, Modifier.PUBLIC);
    }

    public static boolean isPrivate(Element element) {
        return hasModifier(element, Modifier.PRIVATE);
    }

    private static boolean hasModifier(Element element, Modifier modifier) {
        return element.getModifiers().contains(modifier);
    }
}
