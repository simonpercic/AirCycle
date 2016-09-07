package com.github.simonpercic.aircycle.dagger;

import com.github.simonpercic.aircycle.AirCycleProcessor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Annotation processor Dagger component.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
@Component(modules = ProcessorModule.class)
public interface ProcessorComponent {
    void inject(AirCycleProcessor processor);
}
