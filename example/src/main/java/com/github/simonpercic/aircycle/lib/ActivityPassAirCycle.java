package com.github.simonpercic.aircycle.lib;

import android.app.Activity;
import android.os.Bundle;

public interface ActivityPassAirCycle<T extends Activity> {

    void onCreate(T activity, Bundle savedInstanceState);

    void onStart(T activity);

    void onResume(T activity);

    void onPause(T activity);

    void onStop(T activity);

    void onSaveInstanceState(T activity, Bundle outState);

    void onDestroy(T activity);
}
