package com.github.simonpercic.aircycle.lib;

public interface ActivityAirCycle {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState();

    void onDestroy();
}
