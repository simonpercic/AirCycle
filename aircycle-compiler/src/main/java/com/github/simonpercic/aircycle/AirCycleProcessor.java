package com.github.simonpercic.aircycle;

import com.github.simonpercic.aircycle.dagger.DaggerProcessorComponent;
import com.github.simonpercic.aircycle.dagger.ProcessorModule;
import com.github.simonpercic.aircycle.manager.ActivityLifecycleConverter;
import com.github.simonpercic.aircycle.manager.ClassFileWriter;
import com.github.simonpercic.aircycle.manager.ClassGenerator;
import com.github.simonpercic.aircycle.manager.FieldValidator;
import com.github.simonpercic.aircycle.manager.MethodParser;
import com.github.simonpercic.aircycle.model.FieldListenerMethods;
import com.github.simonpercic.aircycle.model.ListenerMethod;
import com.github.simonpercic.aircycle.model.type.ActivityLifecycleType;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.inject.Inject;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;

/**
 * AirCycle annotation processor.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@AutoService(Processor.class)
public class AirCycleProcessor extends AbstractProcessor {

    @Inject FieldValidator fieldValidator;
    @Inject MethodParser methodParser;
    @Inject ActivityLifecycleConverter activityLifecycleConverter;
    @Inject ClassGenerator classGenerator;
    @Inject ClassFileWriter classFileWriter;

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
        Set<String> annotations = new HashSet<>();
        annotations.add(AirCycle.class.getCanonicalName());
        annotations.add(Ignore.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Map<TypeElement, List<VariableElement>> enclosingElements = new HashMap<>();

        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(AirCycle.class)) {
            VariableElement field = (VariableElement) annotatedElement;
            if (!fieldValidator.isFieldValid(field)) {
                return true;
            }

            TypeElement enclosingElement = (TypeElement) field.getEnclosingElement();

            List<VariableElement> fields = enclosingElements.get(enclosingElement);
            if (fields == null) {
                fields = new ArrayList<>();
                enclosingElements.put(enclosingElement, fields);
            }

            fields.add(field);
        }

        Map<TypeElement, List<ExecutableElement>> ignoredMethods = new HashMap<>();

        for (Element ignoredElement : roundEnv.getElementsAnnotatedWith(Ignore.class)) {
            ExecutableElement method = (ExecutableElement) ignoredElement;
            TypeElement enclosingElement = (TypeElement) method.getEnclosingElement();

            List<ExecutableElement> methods = ignoredMethods.get(enclosingElement);
            if (methods == null) {
                methods = new ArrayList<>();
                ignoredMethods.put(enclosingElement, methods);
            }

            methods.add(method);
        }

        for (TypeElement enclosingElement : enclosingElements.keySet()) {
            Map<ActivityLifecycleType, List<FieldListenerMethods>> lifecycleListenerMethods = new TreeMap<>();

            List<VariableElement> fields = enclosingElements.get(enclosingElement);
            for (VariableElement field : fields) {
                DeclaredType declaredType = (DeclaredType) field.asType();
                TypeElement element = (TypeElement) declaredType.asElement();

                List<ExecutableElement> typeIgnoredMethods = ignoredMethods.get(element);

                int[] ignoreLifecycleInts = field.getAnnotation(AirCycle.class).ignore();

                List<ActivityLifecycleType> ignoreLifecycleTypes = activityLifecycleConverter.fromInts(
                        ignoreLifecycleInts, field, enclosingElement);

                Map<ActivityLifecycleType, List<ListenerMethod>> methods =
                        methodParser.parseLifecycleMethods(element, typeIgnoredMethods, ignoreLifecycleTypes);

                if (methods == null) {
                    return true;
                }

                for (ActivityLifecycleType lifecycle : methods.keySet()) {
                    List<FieldListenerMethods> fieldListenerMethods = lifecycleListenerMethods.get(lifecycle);
                    if (fieldListenerMethods == null) {
                        fieldListenerMethods = new ArrayList<>();
                        lifecycleListenerMethods.put(lifecycle, fieldListenerMethods);
                    }

                    fieldListenerMethods.add(new FieldListenerMethods(field, methods.get(lifecycle)));
                }
            }

            TypeSpec typeSpec = classGenerator.generateClass(enclosingElement, lifecycleListenerMethods);

            boolean success = classFileWriter.writeClass(typeSpec, enclosingElement);
            if (!success) {
                return true;
            }
        }

        return true;
    }
}

