package com.github.simonpercic.example.aircycle.activity.a5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityAirCycleLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FifthActivity extends AppCompatActivity {

    ActivityAirCycleLogger airCycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FifthActivityTestAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, FifthActivity.class);
    }
}
