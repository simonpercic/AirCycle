package com.github.simonpercic.example.aircycle.listener;

import android.os.Bundle;

/**
 * Bundle listener.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class BundleListener {

    public static final String EXTRA_INT = BundleListener.class.getCanonicalName() + ".extraInt";

    private int extra;

    public void onCreate(Bundle bundle) {
        extra = bundle.getInt(EXTRA_INT);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(EXTRA_INT, extra);
    }
}
