package com.github.simonpercic.aircycle.first;

import android.app.Application.ActivityLifecycleCallbacks;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class TestFirstActivityAirCycle extends BaseAirCycle<FirstActivity> {

    protected TestFirstActivityAirCycle(FirstActivity tActivity) {
        super(tActivity);
    }

    @Override protected ActivityLifecycleCallbacks getCallbacks(FirstActivity activity) {
        return activity.lifecycleLogger;
    }

    static void bind(FirstActivity activity) {
        new TestFirstActivityAirCycle(activity).registerCallbacks();
    }
}
