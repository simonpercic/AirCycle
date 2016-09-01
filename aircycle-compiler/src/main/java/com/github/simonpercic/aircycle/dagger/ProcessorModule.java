package com.github.simonpercic.aircycle.dagger;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.inject.Singleton;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import dagger.Module;
import dagger.Provides;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Module
public class ProcessorModule {

    private final ProcessingEnvironment processingEnvironment;

    public ProcessorModule(ProcessingEnvironment processingEnvironment) {
        this.processingEnvironment = processingEnvironment;
    }

    @Provides @Singleton ProcessingEnvironment provideProcessingEnvironment() {
        return processingEnvironment;
    }

    @Provides @Singleton Messager provideMessager(ProcessingEnvironment environment) {
        return environment.getMessager();
    }

    @Provides @Singleton Elements provideElementUtils(ProcessingEnvironment environment) {
        return environment.getElementUtils();
    }

    @Provides @Singleton Types provideTypeUtils(ProcessingEnvironment environment) {
        return environment.getTypeUtils();
    }

    @Provides @Singleton Filer provideFiler(ProcessingEnvironment environment) {
        return environment.getFiler();
    }
}
