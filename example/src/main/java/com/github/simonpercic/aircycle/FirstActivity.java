package com.github.simonpercic.aircycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class FirstActivity extends AppCompatActivity {

    private ActivityLifecycleLogger lifecycleLogger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        lifecycleLogger = new ActivityLifecycleLogger();
    }

    @OnClick(R.id.btn_open_second) void onBtnOpenSecondActivityClicked() {
        startActivity(SecondActivity.getIntent(this));
    }
}
