package com.github.simonpercic.aircycle.activity.a1;

import android.os.Bundle;

import com.github.simonpercic.aircycle.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FirstActivityTestAirCycle extends BaseAirCycle<FirstActivity> {

    protected FirstActivityTestAirCycle(FirstActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(FirstActivity activity, Bundle savedInstanceState) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onCreate();
        }
    }

    @Override protected void notifyOnActivityStarted(FirstActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onStart();
        }
    }

    @Override protected void notifyOnActivityResumed(FirstActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onResume();
        }
    }

    @Override protected void notifyOnActivityPaused(FirstActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onPause();
        }
    }

    @Override protected void notifyOnActivityStopped(FirstActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onStop();
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(FirstActivity activity, Bundle outState) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onSaveInstanceState();
        }
    }

    @Override protected void notifyOnActivityDestroyed(FirstActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onDestroy();
        }
    }

    static void bind(FirstActivity activity) {
        new FirstActivityTestAirCycle(activity).registerCallbacks();
    }
}
