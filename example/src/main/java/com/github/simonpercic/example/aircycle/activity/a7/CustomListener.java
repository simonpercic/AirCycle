package com.github.simonpercic.example.aircycle.activity.a7;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class CustomListener {

    public void onCreate() {
        Timber.d("onCreate");
    }

    public void onCreate(CustomListenerActivity activity) {
        Timber.d("onCreate activity: %s", activity);
    }

    public void onCreate(Bundle bundle) {
        Timber.d("onCreate bundle: %s", bundle);
    }

    public void onSaveInstanceState(AppCompatActivity activity, Bundle bundle) {
        Timber.d("onSaveInstanceState activity: %s | bundle: %s", activity, bundle);
    }

    public void onActivitySaveInstanceState(Bundle bundle, Activity activity) {
        Timber.d("onActivitySaveInstanceState bundle: %s | activity: %s", bundle, activity);
    }
}
