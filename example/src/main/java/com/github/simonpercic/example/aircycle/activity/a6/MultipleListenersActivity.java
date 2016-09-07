package com.github.simonpercic.example.aircycle.activity.a6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityAirCycleLogger;
import com.github.simonpercic.example.aircycle.logger.ActivityBundleAirCycleLogger;

/**
 * Example Activity showing usage of multiple listeners annotated with AirCycle in the same Activity.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class MultipleListenersActivity extends AppCompatActivity {

    @AirCycle ActivityAirCycleLogger airCycleLogger;
    @AirCycle final ActivityBundleAirCycleLogger bundleAirCycleLogger = new ActivityBundleAirCycleLogger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MultipleListenersActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        airCycleLogger = new ActivityAirCycleLogger();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MultipleListenersActivity.class);
    }
}
