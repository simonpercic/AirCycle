package com.github.simonpercic.aircycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class SecondActivity extends AppCompatActivity {

    private final ActivityLifecycleLogger lifecycleLogger = new ActivityLifecycleLogger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, SecondActivity.class);
    }
}
