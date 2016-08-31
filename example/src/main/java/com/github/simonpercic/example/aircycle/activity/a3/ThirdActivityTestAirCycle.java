package com.github.simonpercic.example.aircycle.activity.a3;

import android.os.Bundle;

import com.github.simonpercic.aircycle.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ThirdActivityTestAirCycle extends BaseAirCycle<ThirdActivity> {

    protected ThirdActivityTestAirCycle(ThirdActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(ThirdActivity activity, Bundle savedInstanceState) {
        if (activity.passAirCycleLogger != null) {
            activity.passAirCycleLogger.onCreate(activity, savedInstanceState);
        }
    }

    @Override protected void notifyOnActivityStarted(ThirdActivity activity) {
        if (activity.passAirCycleLogger != null) {
            activity.passAirCycleLogger.onStart(activity);
        }
    }

    @Override protected void notifyOnActivityResumed(ThirdActivity activity) {
        if (activity.passAirCycleLogger != null) {
            activity.passAirCycleLogger.onResume(activity);
        }
    }

    @Override protected void notifyOnActivityPaused(ThirdActivity activity) {
        if (activity.passAirCycleLogger != null) {
            activity.passAirCycleLogger.onPause(activity);
        }
    }

    @Override protected void notifyOnActivityStopped(ThirdActivity activity) {
        if (activity.passAirCycleLogger != null) {
            activity.passAirCycleLogger.onStop(activity);
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(ThirdActivity activity, Bundle outState) {
        if (activity.passAirCycleLogger != null) {
            activity.passAirCycleLogger.onSaveInstanceState(activity, outState);
        }
    }

    @Override protected void notifyOnActivityDestroyed(ThirdActivity activity) {
        if (activity.passAirCycleLogger != null) {
            activity.passAirCycleLogger.onDestroy(activity);
        }
    }

    static void bind(ThirdActivity activity) {
        new ThirdActivityTestAirCycle(activity).registerCallbacks();
    }
}
