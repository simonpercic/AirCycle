package com.github.simonpercic.aircycle.manager;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Class type spec Java file writer.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
public class ClassFileWriter {

    private final ProcessorLogger logger;
    private final Filer filer;
    private final Elements elementUtils;

    @Inject
    public ClassFileWriter(ProcessorLogger logger, Filer filer, Elements elementUtils) {
        this.logger = logger;
        this.filer = filer;
        this.elementUtils = elementUtils;
    }

    public boolean writeClass(TypeSpec typeSpec, TypeElement enclosingElement) {
        if (typeSpec == null) {
            throw new IllegalArgumentException("typeSpec is null");
        }

        if (enclosingElement == null) {
            throw new IllegalArgumentException("enclosingElement is null");
        }

        String packageName = getPackageName(enclosingElement);

        if (packageName == null) {
            String message = String.format("`%s` has no package name", enclosingElement.getQualifiedName());
            logger.e(message, enclosingElement);
            return false;
        }

        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();

        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            logger.e(String.format("Failed to write file: %s", e.getMessage()), enclosingElement);
            return false;
        }

        return true;
    }

    private String getPackageName(TypeElement element) {
        PackageElement typePackage = elementUtils.getPackageOf(element);

        if (typePackage.isUnnamed()) {
            return null;
        }

        return typePackage.getQualifiedName().toString();
    }
}
