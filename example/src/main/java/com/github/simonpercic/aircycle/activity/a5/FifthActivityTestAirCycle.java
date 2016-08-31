package com.github.simonpercic.aircycle.activity.a5;

import android.os.Bundle;

import com.github.simonpercic.aircycle.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FifthActivityTestAirCycle extends BaseAirCycle<FifthActivity> {

    protected FifthActivityTestAirCycle(FifthActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(FifthActivity activity, Bundle savedInstanceState) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onCreate();
        }
    }

    @Override protected void notifyOnActivityStarted(FifthActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onStart();
        }
    }

    @Override protected void notifyOnActivityResumed(FifthActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onResume();
        }
    }

    @Override protected void notifyOnActivityPaused(FifthActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onPause();
        }
    }

    @Override protected void notifyOnActivityStopped(FifthActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onStop();
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(FifthActivity activity, Bundle outState) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onSaveInstanceState();
        }
    }

    @Override protected void notifyOnActivityDestroyed(FifthActivity activity) {
        if (activity.airCycleLogger != null) {
            activity.airCycleLogger.onDestroy();
        }
    }

    static void bind(FifthActivity activity) {
        new FifthActivityTestAirCycle(activity).registerCallbacks();
    }
}
