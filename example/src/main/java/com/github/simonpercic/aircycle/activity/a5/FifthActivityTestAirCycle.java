package com.github.simonpercic.aircycle.activity.a5;

import android.os.Bundle;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FifthActivityTestAirCycle extends BaseAirCycle<FifthActivity> {

    protected FifthActivityTestAirCycle(FifthActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(FifthActivity activity, Bundle savedInstanceState) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onCreate();
        }
    }

    @Override protected void notifyOnActivityStarted(FifthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onStart();
        }
    }

    @Override protected void notifyOnActivityResumed(FifthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onResume();
        }
    }

    @Override protected void notifyOnActivityPaused(FifthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onPause();
        }
    }

    @Override protected void notifyOnActivityStopped(FifthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onStop();
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(FifthActivity activity, Bundle outState) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onSaveInstanceState();
        }
    }

    @Override protected void notifyOnActivityDestroyed(FifthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onDestroy();
        }
    }

    static void bind(FifthActivity activity) {
        new FifthActivityTestAirCycle(activity).registerCallbacks();
    }
}
