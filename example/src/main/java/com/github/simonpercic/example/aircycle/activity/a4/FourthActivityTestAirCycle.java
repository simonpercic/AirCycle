package com.github.simonpercic.example.aircycle.activity.a4;

import android.os.Bundle;

import com.github.simonpercic.aircycle.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FourthActivityTestAirCycle extends BaseAirCycle<FourthActivity> {

    protected FourthActivityTestAirCycle(FourthActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(FourthActivity activity, Bundle savedInstanceState) {
        if (activity.lifecycleCallbacksLogger != null) {
            activity.lifecycleCallbacksLogger.onActivityCreated(activity, savedInstanceState);
        }
    }

    @Override protected void notifyOnActivityStarted(FourthActivity activity) {
        if (activity.lifecycleCallbacksLogger != null) {
            activity.lifecycleCallbacksLogger.onActivityStarted(activity);
        }
    }

    @Override protected void notifyOnActivityResumed(FourthActivity activity) {
        if (activity.lifecycleCallbacksLogger != null) {
            activity.lifecycleCallbacksLogger.onActivityResumed(activity);
        }
    }

    @Override protected void notifyOnActivityPaused(FourthActivity activity) {
        if (activity.lifecycleCallbacksLogger != null) {
            activity.lifecycleCallbacksLogger.onActivityPaused(activity);
        }
    }

    @Override protected void notifyOnActivityStopped(FourthActivity activity) {
        if (activity.lifecycleCallbacksLogger != null) {
            activity.lifecycleCallbacksLogger.onActivityStopped(activity);
        }
    }

    @Override protected void notifyOnActivitySaveInstanceState(FourthActivity activity, Bundle outState) {
        if (activity.lifecycleCallbacksLogger != null) {
            activity.lifecycleCallbacksLogger.onActivitySaveInstanceState(activity, outState);
        }
    }

    @Override protected void notifyOnActivityDestroyed(FourthActivity activity) {
        if (activity.lifecycleCallbacksLogger != null) {
            activity.lifecycleCallbacksLogger.onActivityDestroyed(activity);
        }
    }

    static void bind(FourthActivity activity) {
        new FourthActivityTestAirCycle(activity).registerCallbacks();
    }
}
