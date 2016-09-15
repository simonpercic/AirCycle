package com.github.simonpercic.mvp.example.aircycle.ui.countdown.manager;

import android.support.annotation.IntRange;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class CountdownManager {

    @Inject
    public CountdownManager() {
    }

    public Observable<Integer> countdownFrom(@IntRange(from = 1) int fromValue) {
        if (fromValue < 1) {
            throw new IllegalArgumentException("fromValue must be at least 1");
        }

        return Observable.interval(1, 1, TimeUnit.SECONDS)
                .map(value -> fromValue - value.intValue())
                .takeUntil(value -> value <= 0);
    }
}
