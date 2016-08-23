package com.github.simonpercic.aircycle;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class App extends Application {

    @Override public void onCreate() {
        super.onCreate();

        // you should not plant the DebugTree in production builds in your apps
        Timber.plant(new DebugTree());

        LeakCanary.install(this);
    }
}
