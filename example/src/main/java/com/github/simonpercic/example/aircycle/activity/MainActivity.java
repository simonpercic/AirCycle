package com.github.simonpercic.example.aircycle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.activity.a1.ListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a2.BundleListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a3.PassListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a4.LifecycleCallbacksActivity;
import com.github.simonpercic.example.aircycle.activity.a5.NullListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a6.MultipleListenersActivity;
import com.github.simonpercic.example.aircycle.activity.a7.CustomListenerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_open_listener) void onBtnOpenListenerClicked() {
        startActivity(ListenerActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_bundle_listener) void onBtnOpenBundleListenerClicked() {
        startActivity(BundleListenerActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_pass_listener) void onBtnOpenPassListenerClicked() {
        startActivity(PassListenerActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_lifecycle_callbacks) void onBtnOpenLifecycleCallbacksClicked() {
        startActivity(LifecycleCallbacksActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_null_listener) void onBtnOpenNullListenerClicked() {
        startActivity(NullListenerActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_multiple_listeners) void onBtnOpenMultipleListenersClicked() {
        startActivity(MultipleListenersActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_custom_listener) void onBtnOpenCustomListenerClicked() {
        startActivity(CustomListenerActivity.getIntent(this));
    }
}
