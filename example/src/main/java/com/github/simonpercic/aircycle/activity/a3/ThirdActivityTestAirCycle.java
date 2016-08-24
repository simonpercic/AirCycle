package com.github.simonpercic.aircycle.activity.a3;

import android.os.Bundle;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ThirdActivityTestAirCycle extends BaseAirCycle<ThirdActivity> {

    protected ThirdActivityTestAirCycle(ThirdActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(ThirdActivity activity, Bundle savedInstanceState) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onCreate(activity, savedInstanceState);
        }
    }

    @Override protected void notifyOnActivityStarted(ThirdActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onStart(activity);
        }
    }

    @Override protected void notifyOnActivityResumed(ThirdActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onResume(activity);
        }
    }

    @Override protected void notifyOnActivityPaused(ThirdActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onPause(activity);
        }
    }

    @Override protected void notifyOnActivityStopped(ThirdActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onStop(activity);
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(ThirdActivity activity, Bundle outState) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onSaveInstanceState(activity, outState);
        }
    }

    @Override protected void notifyOnActivityDestroyed(ThirdActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onDestroy(activity);
        }
    }

    static void bind(ThirdActivity activity) {
        new ThirdActivityTestAirCycle(activity).registerCallbacks();
    }
}
