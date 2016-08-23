package com.github.simonpercic.aircycle.second;

import android.app.Application.ActivityLifecycleCallbacks;

import com.github.simonpercic.aircycle.lib.BaseAirCycle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class TestSecondActivityAirCycle extends BaseAirCycle<SecondActivity> {

    protected TestSecondActivityAirCycle(SecondActivity tActivity) {
        super(tActivity);
    }

    @Override protected ActivityLifecycleCallbacks getCallbacks(SecondActivity activity) {
        return activity.lifecycleLogger;
    }

    static void bind(SecondActivity activity) {
        new TestSecondActivityAirCycle(activity).registerCallbacks();
    }
}
