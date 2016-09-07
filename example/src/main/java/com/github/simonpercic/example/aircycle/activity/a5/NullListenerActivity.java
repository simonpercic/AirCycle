package com.github.simonpercic.example.aircycle.activity.a5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityAirCycleLogger;

/**
 * Example Activity with a null listener instance.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class NullListenerActivity extends AppCompatActivity {

    @AirCycle ActivityAirCycleLogger airCycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NullListenerActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, NullListenerActivity.class);
    }
}
