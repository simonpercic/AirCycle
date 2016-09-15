package com.github.simonpercic.mvp.example.aircycle.ui.countdown;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public interface CountdownMvp {

    interface View {

        void showStart();

        void showValue(String value);

        void showComplete();

        void showError(String message);

        void showCloseButton();

        void hideCloseButton();
    }

    interface Presenter {

        void onCreate();

        void onStart();

        void onStop();
    }
}
