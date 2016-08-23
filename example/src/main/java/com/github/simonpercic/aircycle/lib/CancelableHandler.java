package com.github.simonpercic.aircycle.lib;

import android.os.Handler;
import android.os.Looper;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class CancelableHandler {

    private final Handler handler;
    private boolean scheduled = false;

    public CancelableHandler() {
        handler = new Handler(Looper.getMainLooper());
    }

    public void post(final Runnable runnable) {
        scheduled = true;
        handler.post(new Runnable() {
            @Override public void run() {
                scheduled = false;
                runnable.run();
            }
        });
    }

    public void cancel() {
        scheduled = false;
        handler.removeCallbacksAndMessages(null);
    }

    public boolean isScheduled() {
        return scheduled;
    }
}
