package com.github.simonpercic.example.aircycle.activity.a7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class SeventhActivity extends AppCompatActivity {

    @AirCycle final CustomListener bundleAirCycleLogger = new CustomListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeventhActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SeventhActivity.class);
    }
}
