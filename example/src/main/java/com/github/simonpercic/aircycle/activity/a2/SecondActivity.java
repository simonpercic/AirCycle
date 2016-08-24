package com.github.simonpercic.aircycle.activity.a2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.R;
import com.github.simonpercic.aircycle.logger.ActivityBundleAirCycleLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class SecondActivity extends AppCompatActivity {

    final ActivityBundleAirCycleLogger lifecycleLogger = new ActivityBundleAirCycleLogger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SecondActivityTestAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SecondActivity.class);
    }
}
