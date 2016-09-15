package com.github.simonpercic.mvp.example.aircycle.ui.countdown;

import com.github.simonpercic.mvp.example.aircycle.ui.base.scope.PerActivity;

import dagger.Component;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@PerActivity
@Component(modules = CountdownModule.class)
public interface CountdownComponent {
    void inject(CountdownActivity activity);
}
