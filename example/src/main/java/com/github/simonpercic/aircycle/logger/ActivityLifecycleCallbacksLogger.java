package com.github.simonpercic.aircycle.logger;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

import timber.log.Timber;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ActivityLifecycleCallbacksLogger implements ActivityLifecycleCallbacks {

    @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.d("onActivityCreated: %s | savedInstanceState: %s", activity, savedInstanceState);
    }

    @Override public void onActivityStarted(Activity activity) {
        Timber.d("onActivityStarted: %s", activity);
    }

    @Override public void onActivityResumed(Activity activity) {
        Timber.d("onActivityResumed: %s", activity);
    }

    @Override public void onActivityPaused(Activity activity) {
        Timber.d("onActivityPaused: %s", activity);
    }

    @Override public void onActivityStopped(Activity activity) {
        Timber.d("onActivityStopped: %s", activity);
    }

    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.d("onActivitySaveInstanceState: %s | outState: %s", activity, outState);
    }

    @Override public void onActivityDestroyed(Activity activity) {
        Timber.d("onActivityDestroyed: %s", activity);
    }
}
