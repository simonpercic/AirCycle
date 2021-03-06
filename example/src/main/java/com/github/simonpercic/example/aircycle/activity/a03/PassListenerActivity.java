package com.github.simonpercic.example.aircycle.activity.a03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityPassAirCycleLogger;

/**
 * Example Activity showing usage of AirCycle's provided ActivityPassAirCycle typed listener.
 * In this example, the listener instance is created immediately after bind in onCreate()
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class PassListenerActivity extends AppCompatActivity {

    @AirCycle ActivityPassAirCycleLogger<PassListenerActivity> passAirCycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PassListenerActivityAirCycle.bind(this);
        passAirCycleLogger = new ActivityPassAirCycleLogger<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, PassListenerActivity.class);
    }
}
