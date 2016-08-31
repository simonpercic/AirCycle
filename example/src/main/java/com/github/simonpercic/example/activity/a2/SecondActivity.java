package com.github.simonpercic.example.activity.a2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.example.R;
import com.github.simonpercic.example.logger.ActivityBundleAirCycleLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class SecondActivity extends AppCompatActivity {

    final ActivityBundleAirCycleLogger bundleAirCycleLogger = new ActivityBundleAirCycleLogger();

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
