package com.github.simonpercic.aircycle.lib;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public abstract class BaseAirCycle<T extends Activity> implements ActivityLifecycleCallbacks {

    private final T tActivity;
    private final CancelableHandler handler;

    protected BaseAirCycle(T tActivity) {
        this.tActivity = tActivity;
        this.handler = new CancelableHandler();
    }

    @Override public void onActivityCreated(Activity activity, final Bundle bundle) {
        if (activity != tActivity) {
            return;
        }

        if (getCallbacks(tActivity) == null) {
            handler.post(new Runnable() {
                @Override public void run() {
                    if (getCallbacks(tActivity) != null) {
                        getCallbacks(tActivity).onActivityCreated(tActivity, bundle);
                    }
                }
            });
        } else {
            if (getCallbacks(tActivity) != null) {
                getCallbacks(tActivity).onActivityCreated(tActivity, bundle);
            }
        }
    }

    @Override public void onActivityStarted(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        if (handler.isScheduled()) {
            handler.post(new Runnable() {
                @Override public void run() {
                    if (getCallbacks(tActivity) != null) {
                        getCallbacks(tActivity).onActivityStarted(tActivity);
                    }
                }
            });
        } else {
            if (getCallbacks(tActivity) != null) {
                getCallbacks(tActivity).onActivityStarted(tActivity);
            }
        }
    }

    @Override public void onActivityResumed(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        if (handler.isScheduled()) {
            handler.post(new Runnable() {
                @Override public void run() {
                    if (getCallbacks(tActivity) != null) {
                        getCallbacks(tActivity).onActivityResumed(tActivity);
                    }
                }
            });
        } else {
            if (getCallbacks(tActivity) != null) {
                getCallbacks(tActivity).onActivityResumed(tActivity);
            }
        }
    }

    @Override public void onActivityPaused(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        if (getCallbacks(tActivity) != null) {
            getCallbacks(tActivity).onActivityPaused(tActivity);
        }
    }

    @Override public void onActivityStopped(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        if (getCallbacks(tActivity) != null) {
            getCallbacks(tActivity).onActivityStopped(tActivity);
        }
    }

    @Override public void onActivitySaveInstanceState(Activity activity, final Bundle bundle) {
        if (activity != tActivity) {
            return;
        }

        if (getCallbacks(tActivity) != null) {
            getCallbacks(tActivity).onActivitySaveInstanceState(tActivity, bundle);
        }
    }

    @Override public void onActivityDestroyed(Activity activity) {
        if (activity != tActivity) {
            return;
        }

        if (getCallbacks(tActivity) != null) {
            getCallbacks(tActivity).onActivityDestroyed(tActivity);
        }

        if (tActivity.isFinishing()) {
            tActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            handler.cancel();
        }
    }

    protected abstract ActivityLifecycleCallbacks getCallbacks(T activity);

    protected void registerCallbacks() {
        tActivity.getApplication().registerActivityLifecycleCallbacks(this);
    }
}
