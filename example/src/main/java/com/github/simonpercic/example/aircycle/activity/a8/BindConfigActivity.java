package com.github.simonpercic.example.aircycle.activity.a8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.aircycle.AirCycleConfig;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.listener.BundleListener;

/**
 * Example Activity showing usage of a custom AirCycleConfig passed while binding.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class BindConfigActivity extends AppCompatActivity {

    @AirCycle final BundleListener bundleListener = new BundleListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AirCycleConfig airCycleConfig = AirCycleConfig.builder()
                .passIntentBundleOnCreate(true)
                .build();

        BindConfigActivityAirCycle.bind(this, airCycleConfig);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, BindConfigActivity.class);
        intent.putExtra(BundleListener.EXTRA_INT, 7);
        return intent;
    }
}
