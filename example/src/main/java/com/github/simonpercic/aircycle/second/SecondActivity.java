package com.github.simonpercic.aircycle.second;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.logger.ActivityLifecycleLogger;
import com.github.simonpercic.aircycle.R;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class SecondActivity extends AppCompatActivity {

    final ActivityLifecycleLogger lifecycleLogger = new ActivityLifecycleLogger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TestSecondActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SecondActivity.class);
    }
}
