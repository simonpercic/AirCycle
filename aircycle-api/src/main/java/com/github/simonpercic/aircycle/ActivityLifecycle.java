package com.github.simonpercic.aircycle;

/**
 * Activity lifecycle constants.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class ActivityLifecycle {

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
