package com.github.simonpercic.aircycle;

import java.util.HashSet;
import java.util.Set;

/**
 * AirCycleConfig, used to set configuration values for binding via AirCycle.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class AirCycleConfig {

    private final boolean passIntentBundleOnCreate;
    private final Set<Integer> ignoredLifecycleCallbacks;

    private AirCycleConfig(boolean passIntentBundleOnCreate, Set<Integer> ignoredLifecycleCallbacks) {
        this.passIntentBundleOnCreate = passIntentBundleOnCreate;
        this.ignoredLifecycleCallbacks = ignoredLifecycleCallbacks;
    }

    boolean passIntentBundleOnCreate() {
        return passIntentBundleOnCreate;
    }

    boolean ignoreLifecycleCallback(int lifecycle) {
        return ignoredLifecycleCallbacks != null
                && ignoredLifecycleCallbacks.size() > 0
                && ignoredLifecycleCallbacks.contains(lifecycle);
    }

    /**
     * Get a builder for the AirCycleConfig.
     *
     * @return AirCycleConfig builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * AirCycleConfig's builder class.
     */
    public static final class Builder {

        private boolean passIntentBundleOnCreate;
        private Set<Integer> ignoredLifecycleCallbacks;

        private Builder() {

        }

        /**
         * Whether to pass the Activity's starting Intent Extras Bundle if its savedInstanceState is null in onCreate().
         *
         * @param passIntentBundleOnCreate if true, it passes the Activity's starting Intent Extras Bundle only if its
         * savedInstanceState is null in onCreate()
         * @return this Builder, for chaining calls
         */
        public Builder passIntentBundleOnCreate(boolean passIntentBundleOnCreate) {
            this.passIntentBundleOnCreate = passIntentBundleOnCreate;
            return this;
        }

        /**
         * Disable passing of an Activity lifecycle callback from {@link ActivityLifecycle}'s constants.
         *
         * @param lifecycleCallback lifecycle callback to ignore
         * @return this Builder, for chaining calls
         */
        public Builder ignoreLifecycleCallback(int lifecycleCallback) {
            switch (lifecycleCallback) {
                case ActivityLifecycle.CREATE:
                case ActivityLifecycle.START:
                case ActivityLifecycle.RESUME:
                case ActivityLifecycle.PAUSE:
                case ActivityLifecycle.STOP:
                case ActivityLifecycle.SAVE_INSTANCE_STATE:
                case ActivityLifecycle.DESTROY:
                    if (ignoredLifecycleCallbacks == null) {
                        ignoredLifecycleCallbacks = new HashSet<>();
                    }

                    ignoredLifecycleCallbacks.add(lifecycleCallback);
                    return this;
                default:
                    throw new IllegalArgumentException("unknown lifecycle type");
            }
        }

        /**
         * Build the AirCycleConfig.
         *
         * @return built AirCycleConfig
         */
        public AirCycleConfig build() {
            return new AirCycleConfig(passIntentBundleOnCreate, ignoredLifecycleCallbacks);
        }
    }
}
