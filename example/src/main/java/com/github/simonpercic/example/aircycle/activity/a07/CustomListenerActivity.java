package com.github.simonpercic.example.aircycle.activity.a07;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.example.aircycle.R;

/**
 * Example Activity showing usage of a custom listener.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class CustomListenerActivity extends AppCompatActivity {

    @AirCycle final CustomListener customListener = new CustomListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CustomListenerActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, CustomListenerActivity.class);
    }
}
