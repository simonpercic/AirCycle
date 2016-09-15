package com.github.simonpercic.mvp.example.aircycle.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.simonpercic.mvp.example.aircycle.R;
import com.github.simonpercic.mvp.example.aircycle.ui.countdown.CountdownActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main MVP example app Activity.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_open_countdown) void onBtnOpenCountdownClicked() {
        startActivity(CountdownActivity.getIntent(this));
    }
}
