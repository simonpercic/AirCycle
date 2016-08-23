package com.github.simonpercic.aircycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

import timber.log.Timber;

public class ActivityLifecycleLogger implements ActivityLifecycleCallbacks {

    @Override public void onActivityCreated(Activity activity, Bundle bundle) {
        Timber.d("onActivityCreated: %s", activity);
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

    @Override public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Timber.d("onActivitySaveInstanceState: %s", activity);
    }

    @Override public void onActivityDestroyed(Activity activity) {
        Timber.d("onActivityDestroyed: %s", activity);
    }
}
