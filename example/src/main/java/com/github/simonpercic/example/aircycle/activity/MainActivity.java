package com.github.simonpercic.example.aircycle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.simonpercic.example.aircycle.R;
import com.github.simonpercic.example.aircycle.activity.a01.ListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a02.BundleListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a03.PassListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a04.LifecycleCallbacksActivity;
import com.github.simonpercic.example.aircycle.activity.a05.NullListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a06.MultipleListenersActivity;
import com.github.simonpercic.example.aircycle.activity.a07.CustomListenerActivity;
import com.github.simonpercic.example.aircycle.activity.a08.BindConfigActivity;
import com.github.simonpercic.example.aircycle.activity.a09.DefaultConfigActivity;
import com.github.simonpercic.example.aircycle.activity.a10.IgnoreAnnotationActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main example app Activity.
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

    @OnClick(R.id.btn_open_bind_config) void onBtnOpenBindConfigClicked() {
        startActivity(BindConfigActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_default_config) void onBtnOpenDefaultConfigClicked() {
        startActivity(DefaultConfigActivity.getIntent(this));
    }

    @OnClick(R.id.btn_open_ignore_annotation) void onBtnOpenIgnoreAnnotationClicked() {
        startActivity(IgnoreAnnotationActivity.getIntent(this));
    }
}
