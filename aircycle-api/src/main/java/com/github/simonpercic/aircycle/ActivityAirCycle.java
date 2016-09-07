package com.github.simonpercic.aircycle;

/**
 * ActivityAirCycle listener interface.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public interface ActivityAirCycle {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState();

    void onDestroy();
}
