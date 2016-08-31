package com.github.simonpercic.example.activity.a3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.example.R;
import com.github.simonpercic.example.logger.ActivityPassAirCycleLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ThirdActivity extends AppCompatActivity {

    ActivityPassAirCycleLogger<ThirdActivity> passAirCycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThirdActivityTestAirCycle.bind(this);
        passAirCycleLogger = new ActivityPassAirCycleLogger<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, ThirdActivity.class);
    }
}
