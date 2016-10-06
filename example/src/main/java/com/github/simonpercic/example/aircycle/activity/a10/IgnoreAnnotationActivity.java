package com.github.simonpercic.example.aircycle.activity.a10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.ActivityLifecycle;
import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityAirCycleLogger;

/**
 * Example Activity showing usage of AirCycle's ignore option in annotation.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class IgnoreAnnotationActivity extends AppCompatActivity {

    @AirCycle(ignore = {ActivityLifecycle.RESUME, ActivityLifecycle.PAUSE}) ActivityAirCycleLogger airCycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IgnoreAnnotationActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        airCycleLogger = new ActivityAirCycleLogger();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, IgnoreAnnotationActivity.class);
    }
}
