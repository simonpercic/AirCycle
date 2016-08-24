package com.github.simonpercic.aircycle.first;

import android.os.Bundle;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class TestFirstActivityAirCycle extends BaseAirCycle<FirstActivity> {

    protected TestFirstActivityAirCycle(FirstActivity tActivity) {
        super(tActivity);
    }

    @Override protected void notifyOnActivityCreated(FirstActivity activity, Bundle savedInstanceState) {
        activity.lifecycleLogger.onActivityCreated(activity, savedInstanceState);
    }

    @Override protected void notifyOnActivityStarted(FirstActivity activity) {
        activity.lifecycleLogger.onActivityStarted(activity);
    }

    @Override protected void notifyOnActivityResumed(FirstActivity activity) {
        activity.lifecycleLogger.onActivityResumed(activity);
    }

    @Override protected void notifyOnActivityPaused(FirstActivity activity) {
        activity.lifecycleLogger.onActivityPaused(activity);
    }

    @Override protected void notifyOnActivityStopped(FirstActivity activity) {
        activity.lifecycleLogger.onActivityStopped(activity);
    }

    @Override protected void notifyOnActivitySaveInstanceState(FirstActivity activity, Bundle outState) {
        activity.lifecycleLogger.onActivitySaveInstanceState(activity, outState);
    }

    @Override protected void notifyOnActivityDestroyed(FirstActivity activity) {
        activity.lifecycleLogger.onActivityDestroyed(activity);
    }

    static void bind(FirstActivity activity) {
        new TestFirstActivityAirCycle(activity).registerCallbacks();
    }
}
