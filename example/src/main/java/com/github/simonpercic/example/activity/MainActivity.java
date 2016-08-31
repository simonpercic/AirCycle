package com.github.simonpercic.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.example.R;
import com.github.simonpercic.example.activity.a1.FirstActivity;
import com.github.simonpercic.example.activity.a2.SecondActivity;
import com.github.simonpercic.example.activity.a3.ThirdActivity;
import com.github.simonpercic.example.activity.a4.FourthActivity;
import com.github.simonpercic.example.activity.a5.FifthActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_open_first) void onBtnOpenFirstClicked() {
        startActivity(FirstActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_second) void onBtnOpenSecondClicked() {
        startActivity(SecondActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_third) void onBtnOpenThirdClicked() {
        startActivity(ThirdActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_fourth) void onBtnOpenFourthClicked() {
        startActivity(FourthActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_fifth) void onBtnOpenFifthClicked() {
        startActivity(FifthActivity.getIntent(this));
    }
}
