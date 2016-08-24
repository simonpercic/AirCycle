package com.github.simonpercic.aircycle.activity.second;

import android.os.Bundle;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class TestSecondActivityAirCycle extends BaseAirCycle<SecondActivity> {

    protected TestSecondActivityAirCycle(SecondActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(SecondActivity activity, Bundle savedInstanceState) {
        activity.lifecycleLogger.onActivityCreated(activity, savedInstanceState);
    }

    @Override protected void notifyOnActivityStarted(SecondActivity activity) {
        activity.lifecycleLogger.onActivityStarted(activity);
    }

    @Override protected void notifyOnActivityResumed(SecondActivity activity) {
        activity.lifecycleLogger.onActivityResumed(activity);
    }

    @Override protected void notifyOnActivityPaused(SecondActivity activity) {
        activity.lifecycleLogger.onActivityPaused(activity);
    }

    @Override protected void notifyOnActivityStopped(SecondActivity activity) {
        activity.lifecycleLogger.onActivityStopped(activity);
    }

    @Override protected void notifyOnActivitySaveInstanceState(SecondActivity activity, Bundle outState) {
        activity.lifecycleLogger.onActivitySaveInstanceState(activity, outState);
    }

    @Override protected void notifyOnActivityDestroyed(SecondActivity activity) {
        activity.lifecycleLogger.onActivityDestroyed(activity);
    }

    static void bind(SecondActivity activity) {
        new TestSecondActivityAirCycle(activity).registerCallbacks();
    }
}
