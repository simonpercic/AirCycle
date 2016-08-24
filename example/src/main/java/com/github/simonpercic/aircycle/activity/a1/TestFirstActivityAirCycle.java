package com.github.simonpercic.aircycle.activity.a1;

import android.os.Bundle;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class TestFirstActivityAirCycle extends BaseAirCycle<FirstActivity> {

    protected TestFirstActivityAirCycle(FirstActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(FirstActivity activity, Bundle savedInstanceState) {
        activity.lifecycleLogger.onCreate();
    }

    @Override protected void notifyOnActivityStarted(FirstActivity activity) {
        activity.lifecycleLogger.onStart();
    }

    @Override protected void notifyOnActivityResumed(FirstActivity activity) {
        activity.lifecycleLogger.onResume();
    }

    @Override protected void notifyOnActivityPaused(FirstActivity activity) {
        activity.lifecycleLogger.onPause();
    }

    @Override protected void notifyOnActivityStopped(FirstActivity activity) {
        activity.lifecycleLogger.onStop();
    }

    @Override protected void notifyOnActivitySaveInstanceState(FirstActivity activity, Bundle outState) {
        activity.lifecycleLogger.onSaveInstanceState();
    }

    @Override protected void notifyOnActivityDestroyed(FirstActivity activity) {
        activity.lifecycleLogger.onDestroy();
    }

    static void bind(FirstActivity activity) {
        new TestFirstActivityAirCycle(activity).registerCallbacks();
    }
}
