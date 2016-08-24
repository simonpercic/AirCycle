package com.github.simonpercic.aircycle.activity.a1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.R;
import com.github.simonpercic.aircycle.logger.ActivityAirCycleLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FirstActivity extends AppCompatActivity {

    ActivityAirCycleLogger lifecycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TestFirstActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        lifecycleLogger = new ActivityAirCycleLogger();
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, FirstActivity.class);
    }
}
