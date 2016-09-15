package com.github.simonpercic.mvp.example.aircycle.ui.countdown;

import com.github.simonpercic.mvp.example.aircycle.ui.base.scope.PerActivity;
import com.github.simonpercic.mvp.example.aircycle.ui.countdown.manager.CountdownManager;

import dagger.Module;
import dagger.Provides;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Module
public class CountdownModule {

    private final CountdownMvp.View view;

    public CountdownModule(CountdownMvp.View view) {
        this.view = view;
    }

    @Provides @PerActivity CountdownMvp.View provideView() {
        return view;
    }

    @Provides @PerActivity CountdownMvp.Presenter providePresenter(CountdownMvp.View view,
            CountdownManager countdownManager) {
        return new CountdownPresenter(view, countdownManager);
    }
}
