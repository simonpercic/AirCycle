package com.github.simonpercic.mvp.example.aircycle.ui.countdown;

import com.github.simonpercic.mvp.example.aircycle.manager.utils.rx.SubscriptionUtils;
import com.github.simonpercic.mvp.example.aircycle.ui.countdown.CountdownMvp.View;
import com.github.simonpercic.mvp.example.aircycle.ui.countdown.manager.CountdownManager;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
class CountdownPresenter implements CountdownMvp.Presenter {

    private final CountdownMvp.View view;
    private final CountdownManager countdownManager;

    private Subscription subscription;

    CountdownPresenter(View view, CountdownManager countdownManager) {
        this.view = view;
        this.countdownManager = countdownManager;
    }

    @Override public void onCreate() {
        view.hideCloseButton();
    }

    @Override public void onStart() {
        view.showStart();

        subscription = countdownManager.countdownFrom(5)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override public void onCompleted() {
                        view.showComplete();
                        view.showCloseButton();
                    }

                    @Override public void onError(Throwable e) {
                        view.showError(e.getMessage());
                        view.showCloseButton();
                    }

                    @Override public void onNext(Integer value) {
                        view.showValue(String.valueOf(value));
                    }
                });
    }

    @Override public void onStop() {
        SubscriptionUtils.unsubscribe(subscription);
    }
}
