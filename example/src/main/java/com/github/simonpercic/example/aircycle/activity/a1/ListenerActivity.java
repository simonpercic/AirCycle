package com.github.simonpercic.example.aircycle.activity.a1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityAirCycleLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ListenerActivity extends AppCompatActivity {

    @AirCycle ActivityAirCycleLogger airCycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListenerActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        airCycleLogger = new ActivityAirCycleLogger();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, ListenerActivity.class);
    }
}
