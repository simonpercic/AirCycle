package com.github.simonpercic.example.aircycle.activity.a02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityBundleAirCycleLogger;

/**
 * Example Activity showing usage of AirCycle's provided ActivityBundleAirCycle listener.
 * In this example, the listener instance is final
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class BundleListenerActivity extends AppCompatActivity {

    @AirCycle final ActivityBundleAirCycleLogger bundleAirCycleLogger = new ActivityBundleAirCycleLogger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BundleListenerActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, BundleListenerActivity.class);
    }
}
