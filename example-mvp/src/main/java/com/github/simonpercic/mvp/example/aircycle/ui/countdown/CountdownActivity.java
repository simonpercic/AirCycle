package com.github.simonpercic.mvp.example.aircycle.ui.countdown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.simonpercic.aircycle.AirCycle;
import com.github.simonpercic.mvp.example.aircycle.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class CountdownActivity extends AppCompatActivity implements CountdownMvp.View {

    @AirCycle @Inject CountdownMvp.Presenter presenter;

    @BindView(R.id.tv_value) TextView tvValue;
    @BindView(R.id.btn_close) View btnClose;
    @BindView(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;

    // region Activity callbacks

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        CountdownActivityAirCycle.bind(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_countdown);
        ButterKnife.bind(this);

        DaggerCountdownComponent.builder()
                .countdownModule(new CountdownModule(this))
                .build()
                .inject(this);
    }

    // endregion Activity callbacks

    // region View impl

    @Override public void showStart() {
        tvValue.setText(R.string.countdown_state_start);
    }

    @Override public void showValue(String value) {
        tvValue.setText(value);
    }

    @Override public void showComplete() {
        tvValue.setText(R.string.countdown_state_complete);
    }

    @Override public void showError(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override public void showCloseButton() {
        btnClose.setVisibility(View.VISIBLE);
    }

    @Override public void hideCloseButton() {
        btnClose.setVisibility(View.GONE);
    }

    // endregion View impl

    // region Click listeners

    @OnClick(R.id.btn_close) void onBtnCloseClicked() {
        finish();
    }

    // endregion Click listeners

    @NonNull public static Intent getIntent(@NonNull Context context) {
        return new Intent(context, CountdownActivity.class);
    }
}
