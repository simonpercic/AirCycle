package com.github.simonpercic.aircycle.logger;

import android.app.Activity;
import android.os.Bundle;

import com.github.simonpercic.aircycle.ActivityPassAirCycle;

import timber.log.Timber;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ActivityPassAirCycleLogger<T extends Activity> implements ActivityPassAirCycle<T> {

    @Override public void onCreate(T activity, Bundle savedInstanceState) {
        Timber.d("onCreate: %s | savedInstanceState: %s", activity, savedInstanceState);
    }

    @Override public void onStart(T activity) {
        Timber.d("onStart: %s", activity);
    }

    @Override public void onResume(T activity) {
        Timber.d("onResume: %s", activity);
    }

    @Override public void onPause(T activity) {
        Timber.d("onPause: %s", activity);
    }

    @Override public void onStop(T activity) {
        Timber.d("onStop: %s", activity);
    }

    @Override public void onSaveInstanceState(T activity, Bundle outState) {
        Timber.d("onSaveInstanceState: %s | outState: %s", activity, outState);
    }

    @Override public void onDestroy(T activity) {
        Timber.d("onDestroy: %s", activity);
    }
}
