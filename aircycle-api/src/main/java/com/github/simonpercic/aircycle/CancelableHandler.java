package com.github.simonpercic.aircycle;

import android.os.Handler;
import android.os.Looper;

/**
 * Cancelable handler.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
class CancelableHandler {

    private final Handler handler;
    private boolean scheduled = false;

    CancelableHandler() {
        handler = new Handler(Looper.getMainLooper());
    }

    void post(final Runnable runnable) {
        scheduled = true;
        handler.post(new Runnable() {
            @Override public void run() {
                scheduled = false;
                runnable.run();
            }
        });
    }

    void cancel() {
        scheduled = false;
        handler.removeCallbacksAndMessages(null);
    }

    boolean isScheduled() {
        return scheduled;
    }
}
