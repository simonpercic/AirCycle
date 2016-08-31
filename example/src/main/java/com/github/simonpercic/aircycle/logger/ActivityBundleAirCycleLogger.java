package com.github.simonpercic.aircycle.logger;

import android.os.Bundle;

import com.github.simonpercic.aircycle.ActivityBundleAirCycle;

import timber.log.Timber;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ActivityBundleAirCycleLogger implements ActivityBundleAirCycle {

    @Override public void onCreate(Bundle savedInstanceState) {
        Timber.d("onCreate savedInstanceState: %s", savedInstanceState);
    }

    @Override public void onStart() {
        Timber.d("onStart");
    }

    @Override public void onResume() {
        Timber.d("onResume");
    }

    @Override public void onPause() {
        Timber.d("onPause");
    }

    @Override public void onStop() {
        Timber.d("onStop");
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        Timber.d("onSaveInstanceState outState: %s", outState);
    }

    @Override public void onDestroy() {
        Timber.d("onDestroy");
    }
}
