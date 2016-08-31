package com.github.simonpercic.example.activity.a4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.example.R;
import com.github.simonpercic.example.logger.ActivityLifecycleCallbacksLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FourthActivity extends AppCompatActivity {

    ActivityLifecycleCallbacksLogger lifecycleCallbacksLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FourthActivityTestAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        lifecycleCallbacksLogger = new ActivityLifecycleCallbacksLogger();
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, FourthActivity.class);
    }
}
