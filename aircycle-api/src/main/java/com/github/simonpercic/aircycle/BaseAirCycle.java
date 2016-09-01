package com.github.simonpercic.aircycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public abstract class BaseAirCycle<T extends Activity> implements ActivityLifecycleCallbacks {

    private static final String UNUSED_PARAMETERS = "UnusedParameters";

    private final T tActivity;
    private CancelableHandler handler;

    protected BaseAirCycle(T tActivity) {
        this.tActivity = tActivity;
    }

    // region ActivityLifecycleCallbacks

    @Override public void onActivityCreated(Activity activity, final Bundle savedInstanceState) {
        if (activity != tActivity) {
            return;
        }

        if (handler == null) {
            handler = new CancelableHandler();
        }

        handler.post(new Runnable() {
            @Override public void run() {
                notifyOnActivityCreated(tActivity, savedInstanceState);
            }
        });
    }

    @Override public void onActivityStarted(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        if (handler != null && handler.isScheduled()) {
            handler.post(new Runnable() {
                @Override public void run() {
                    notifyOnActivityStarted(tActivity);
                }
            });
        } else {
            notifyOnActivityStarted(tActivity);
        }
    }

    @Override public void onActivityResumed(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        if (handler != null && handler.isScheduled()) {
            handler.post(new Runnable() {
                @Override public void run() {
                    notifyOnActivityResumed(tActivity);
                }
            });
        } else {
            notifyOnActivityResumed(tActivity);
        }
    }

    @Override public void onActivityPaused(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        cancelHandler();

        notifyOnActivityPaused(tActivity);
    }

    @Override public void onActivityStopped(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        cancelHandler();

        notifyOnActivityStopped(tActivity);
    }

    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if (activity != tActivity) {
            return;
        }

        cancelHandler();

        notifyOnActivitySaveInstanceState(tActivity, outState);
    }

    @Override public void onActivityDestroyed(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        cancelHandler();

        notifyOnActivityDestroyed(tActivity);

        tActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
    }

    // endregion ActivityLifecycleCallbacks

    @SuppressWarnings(UNUSED_PARAMETERS)
    protected void notifyOnActivityCreated(T activity, Bundle savedInstanceState) {

    }

    @SuppressWarnings(UNUSED_PARAMETERS)
    protected void notifyOnActivityStarted(T activity) {

    }

    @SuppressWarnings(UNUSED_PARAMETERS)
    protected void notifyOnActivityResumed(T activity) {

    }

    @SuppressWarnings(UNUSED_PARAMETERS)
    protected void notifyOnActivityPaused(T activity) {

    }

    @SuppressWarnings(UNUSED_PARAMETERS)
    protected void notifyOnActivityStopped(T activity) {

    }

    @SuppressWarnings(UNUSED_PARAMETERS)
    protected void notifyOnActivitySaveInstanceState(T activity, Bundle outState) {

    }

    @SuppressWarnings(UNUSED_PARAMETERS)
    protected void notifyOnActivityDestroyed(T activity) {

    }

    private void cancelHandler() {
        if (handler != null) {
            handler.cancel();
        }
    }

    protected void registerCallbacks() {
        tActivity.getApplication().registerActivityLifecycleCallbacks(this);
    }
}
