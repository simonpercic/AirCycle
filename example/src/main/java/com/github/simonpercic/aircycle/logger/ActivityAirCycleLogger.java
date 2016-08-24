package com.github.simonpercic.aircycle.logger;

import com.github.simonpercic.aircycle.lib.ActivityAirCycle;

import timber.log.Timber;

public class ActivityAirCycleLogger implements ActivityAirCycle {

    @Override public void onCreate() {
        Timber.d("onCreate");
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

    @Override public void onSaveInstanceState() {
        Timber.d("onSaveInstanceState");
    }

    @Override public void onDestroy() {
        Timber.d("onDestroy");
    }
}
