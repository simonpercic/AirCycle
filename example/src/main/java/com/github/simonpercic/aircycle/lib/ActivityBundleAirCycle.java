package com.github.simonpercic.aircycle.lib;

import android.os.Bundle;

public interface ActivityBundleAirCycle {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(Bundle outState);

    void onDestroy();
}
