package com.github.simonpercic.example.aircycle.activity.a3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.logger.ActivityPassAirCycleLogger;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ThirdActivity extends AppCompatActivity {

    @AirCycle ActivityPassAirCycleLogger<ThirdActivity> passAirCycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThirdActivityAirCycle.bind(this);
        passAirCycleLogger = new ActivityPassAirCycleLogger<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, ThirdActivity.class);
    }
}
