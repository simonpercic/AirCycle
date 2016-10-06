package com.github.simonpercic.aircycle;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Activity lifecycle constants.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class ActivityLifecycle {

    @IntDef({CREATE, START, RESUME, PAUSE, STOP, SAVE_INSTANCE_STATE, DESTROY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ActivityLifecycleEvent {
    }

    public static final int CREATE = 1;
    public static final int START = 2;
    public static final int RESUME = 3;
    public static final int PAUSE = 4;
    public static final int STOP = 5;
    public static final int SAVE_INSTANCE_STATE = 6;
    public static final int DESTROY = 7;

    private ActivityLifecycle() {
        // no instance
    }
}
