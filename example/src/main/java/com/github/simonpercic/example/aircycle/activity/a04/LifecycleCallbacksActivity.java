package com.github.simonpercic.example.aircycle.activity.a04;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityLifecycleCallbacksLogger;

/**
 * Example Activity showing usage of Android's built-in ActivityLifecycleCallbacks listener.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class LifecycleCallbacksActivity extends AppCompatActivity {

    @AirCycle ActivityLifecycleCallbacksLogger lifecycleCallbacksLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LifecycleCallbacksActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        lifecycleCallbacksLogger = new ActivityLifecycleCallbacksLogger();
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, LifecycleCallbacksActivity.class);
    }
}
