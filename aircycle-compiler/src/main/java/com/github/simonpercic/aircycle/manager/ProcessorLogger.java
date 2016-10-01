package com.github.simonpercic.aircycle.manager;

import javax.annotation.processing.Messager;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic.Kind;

/**
 * Annotation processor logger.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
public class ProcessorLogger {

    private final Messager messager;

    @Inject ProcessorLogger(Messager messager) {
        this.messager = messager;
    }

    public void w(CharSequence message, Element element) {
        messager.printMessage(Kind.WARNING, message, element);
    }

    public void e(CharSequence message) {
        messager.printMessage(Kind.ERROR, message);
    }

    public void e(CharSequence message, Element element) {
        messager.printMessage(Kind.ERROR, message, element);
    }
}
