package com.github.simonpercic.example.aircycle.activity.a07;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.Ignore;

import timber.log.Timber;

/**
 * Custom listener logger.
 *
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

    @Ignore
    public void onStart() {
        Timber.d("onStart");
    }

    @Ignore public void onResume(CustomListenerActivity activity) {
        Timber.d("onResume activity: %s", activity);
    }

    public void onResume() {
        Timber.d("onResume");
    }

    public void onSaveInstanceState(AppCompatActivity activity, Bundle bundle) {
        Timber.d("onSaveInstanceState activity: %s | bundle: %s", activity, bundle);
    }

    public void onActivitySaveInstanceState(Bundle bundle, Activity activity) {
        Timber.d("onActivitySaveInstanceState bundle: %s | activity: %s", bundle, activity);
    }
}
