package com.github.simonpercic.aircycle;

import android.app.Application;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        // you should not plant the DebugTree in production builds in your apps
        Timber.plant(new DebugTree());
    }
}
