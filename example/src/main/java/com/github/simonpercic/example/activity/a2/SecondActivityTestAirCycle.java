package com.github.simonpercic.example.activity.a2;

import android.os.Bundle;

import com.github.simonpercic.aircycle.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class SecondActivityTestAirCycle extends BaseAirCycle<SecondActivity> {

    protected SecondActivityTestAirCycle(SecondActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(SecondActivity activity, Bundle savedInstanceState) {
        if (activity.bundleAirCycleLogger != null) {
            activity.bundleAirCycleLogger.onCreate(savedInstanceState);
        }
    }

    @Override protected void notifyOnActivityStarted(SecondActivity activity) {
        if (activity.bundleAirCycleLogger != null) {
            activity.bundleAirCycleLogger.onStart();
        }
    }

    @Override protected void notifyOnActivityResumed(SecondActivity activity) {
        if (activity.bundleAirCycleLogger != null) {
            activity.bundleAirCycleLogger.onResume();
        }
    }

    @Override protected void notifyOnActivityPaused(SecondActivity activity) {
        if (activity.bundleAirCycleLogger != null) {
            activity.bundleAirCycleLogger.onPause();
        }
    }

    @Override protected void notifyOnActivityStopped(SecondActivity activity) {
        if (activity.bundleAirCycleLogger != null) {
            activity.bundleAirCycleLogger.onStop();
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(SecondActivity activity, Bundle outState) {
        if (activity.bundleAirCycleLogger != null) {
            activity.bundleAirCycleLogger.onSaveInstanceState(outState);
        }
    }

    @Override protected void notifyOnActivityDestroyed(SecondActivity activity) {
        if (activity.bundleAirCycleLogger != null) {
            activity.bundleAirCycleLogger.onDestroy();
        }
    }

    static void bind(SecondActivity activity) {
        new SecondActivityTestAirCycle(activity).registerCallbacks();
    }
}
