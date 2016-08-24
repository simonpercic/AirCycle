package com.github.simonpercic.aircycle.activity.a2;

import android.os.Bundle;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class SecondActivityTestAirCycle extends BaseAirCycle<SecondActivity> {

    protected SecondActivityTestAirCycle(SecondActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(SecondActivity activity, Bundle savedInstanceState) {
        activity.lifecycleLogger.onCreate(savedInstanceState);
    }

    @Override protected void notifyOnActivityStarted(SecondActivity activity) {
        activity.lifecycleLogger.onStart();
    }

    @Override protected void notifyOnActivityResumed(SecondActivity activity) {
        activity.lifecycleLogger.onResume();
    }

    @Override protected void notifyOnActivityPaused(SecondActivity activity) {
        activity.lifecycleLogger.onPause();
    }

    @Override protected void notifyOnActivityStopped(SecondActivity activity) {
        activity.lifecycleLogger.onStop();
    }

    @Override protected void notifyOnActivitySaveInstanceState(SecondActivity activity, Bundle outState) {
        activity.lifecycleLogger.onSaveInstanceState(outState);
    }

    @Override protected void notifyOnActivityDestroyed(SecondActivity activity) {
        activity.lifecycleLogger.onDestroy();
    }

    static void bind(SecondActivity activity) {
        new SecondActivityTestAirCycle(activity).registerCallbacks();
    }
}
