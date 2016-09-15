package com.github.simonpercic.mvp.example.aircycle.manager.utils.rx;

import android.support.annotation.Nullable;

import rx.Subscription;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class SubscriptionUtils {

    private SubscriptionUtils() {
        // no instance
    }

    public static void unsubscribe(@Nullable Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
