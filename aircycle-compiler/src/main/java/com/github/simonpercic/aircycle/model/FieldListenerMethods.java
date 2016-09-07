package com.github.simonpercic.aircycle.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

import javax.lang.model.element.VariableElement;

/**
 * Listener methods for a listener field.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FieldListenerMethods {

    private final VariableElement field;
    private final List<ListenerMethod> listenerMethods;

    public FieldListenerMethods(VariableElement field, List<ListenerMethod> listenerMethods) {
        this.field = field;
        this.listenerMethods = ImmutableList.copyOf(listenerMethods);
    }

    public VariableElement getField() {
        return field;
    }

    public List<ListenerMethod> getListenerMethods() {
        return listenerMethods;
    }
}
