package com.github.simonpercic.example.aircycle.listener;

import android.os.Bundle;

import com.github.simonpercic.example.aircycle.logger.ActivityBundleAirCycleLogger;

/**
 * Bundle listener.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class BundleListener extends ActivityBundleAirCycleLogger {

    public static final String EXTRA_INT = BundleListener.class.getCanonicalName() + ".extraInt";

    private int extra;

    @Override
    public void onCreate(Bundle bundle) {
        extra = bundle.getInt(EXTRA_INT);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_INT, extra);
    }
}
