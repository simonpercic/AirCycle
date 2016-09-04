package com.github.simonpercic.aircycle;

import com.github.simonpercic.aircycle.dagger.DaggerProcessorComponent;
import com.github.simonpercic.aircycle.dagger.ProcessorModule;
import com.github.simonpercic.aircycle.manager.FieldValidator;
import com.github.simonpercic.aircycle.manager.MethodParser;
import com.github.simonpercic.aircycle.model.LifecycleMethod;
import com.google.auto.service.AutoService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.inject.Inject;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@AutoService(Processor.class)
public class AirCycleProcessor extends AbstractProcessor {

    public static final String ANNOTATION = "@" + AirCycle.class.getSimpleName();

    @Inject FieldValidator fieldValidator;
    @Inject MethodParser methodParser;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        DaggerProcessorComponent.builder()
                .processorModule(new ProcessorModule(processingEnv))
                .build()
                .inject(this);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(AirCycle.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(AirCycle.class)) {
            VariableElement field = (VariableElement) annotatedElement;
            if (!fieldValidator.isFieldValid(field)) {
                return true;
            }

            DeclaredType declaredType = (DeclaredType) field.asType();
            TypeElement element = (TypeElement) declaredType.asElement();

            List<LifecycleMethod> methods = methodParser.parseLifecycleMethods(element);
            if (methods == null) {
                return true;
            }

            // TODO: 04/09/16 impl
        }

        return true;
    }
}

