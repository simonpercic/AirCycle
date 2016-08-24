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
        activity.lifecycleLogger.onActivityCreated(activity, savedInstanceState);
    }

    @Override protected void notifyOnActivityStarted(FourthActivity activity) {
        activity.lifecycleLogger.onActivityStarted(activity);
    }

    @Override protected void notifyOnActivityResumed(FourthActivity activity) {
        activity.lifecycleLogger.onActivityResumed(activity);
    }

    @Override protected void notifyOnActivityPaused(FourthActivity activity) {
        activity.lifecycleLogger.onActivityPaused(activity);
    }

    @Override protected void notifyOnActivityStopped(FourthActivity activity) {
        activity.lifecycleLogger.onActivityStopped(activity);
    }

    @Override protected void notifyOnActivitySaveInstanceState(FourthActivity activity, Bundle outState) {
        activity.lifecycleLogger.onActivitySaveInstanceState(activity, outState);
    }

    @Override protected void notifyOnActivityDestroyed(FourthActivity activity) {
        activity.lifecycleLogger.onActivityDestroyed(activity);
    }

    static void bind(FourthActivity activity) {
        new FourthActivityTestAirCycle(activity).registerCallbacks();
    }
}
