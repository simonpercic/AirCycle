package com.github.simonpercic.aircycle.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.simonpercic.aircycle.logger.ActivityLifecycleLogger;
import com.github.simonpercic.aircycle.R;
import com.github.simonpercic.aircycle.second.SecondActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FirstActivity extends AppCompatActivity {

    ActivityLifecycleLogger lifecycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TestFirstActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        lifecycleLogger = new ActivityLifecycleLogger();
    }

    @OnClick(R.id.btn_open_second) void onBtnOpenSecondActivityClicked() {
        startActivity(SecondActivity.getIntent(this));
    }
}
