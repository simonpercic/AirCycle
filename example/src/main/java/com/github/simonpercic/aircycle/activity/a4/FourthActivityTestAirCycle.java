package com.github.simonpercic.aircycle.activity.a4;

import android.os.Bundle;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FourthActivityTestAirCycle extends BaseAirCycle<FourthActivity> {

    protected FourthActivityTestAirCycle(FourthActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(FourthActivity activity, Bundle savedInstanceState) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onActivityCreated(activity, savedInstanceState);
        }
    }

    @Override protected void notifyOnActivityStarted(FourthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onActivityStarted(activity);
        }
    }

    @Override protected void notifyOnActivityResumed(FourthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onActivityResumed(activity);
        }
    }

    @Override protected void notifyOnActivityPaused(FourthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onActivityPaused(activity);
        }
    }

    @Override protected void notifyOnActivityStopped(FourthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onActivityStopped(activity);
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(FourthActivity activity, Bundle outState) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onActivitySaveInstanceState(activity, outState);
        }
    }

    @Override protected void notifyOnActivityDestroyed(FourthActivity activity) {
        if (activity.lifecycleLogger != null) {
            activity.lifecycleLogger.onActivityDestroyed(activity);
        }
    }

    static void bind(FourthActivity activity) {
        new FourthActivityTestAirCycle(activity).registerCallbacks();
    }
}
