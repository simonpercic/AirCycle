package com.github.simonpercic.aircycle.activity.a4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.R;
import com.github.simonpercic.aircycle.logger.ActivityLifecycleCallbacksLogger;

public class FourthActivity extends AppCompatActivity {

    ActivityLifecycleCallbacksLogger lifecycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleLogger = new ActivityLifecycleCallbacksLogger();
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, FourthActivity.class);
    }
}
