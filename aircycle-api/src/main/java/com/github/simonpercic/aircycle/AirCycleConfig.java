package com.github.simonpercic.aircycle;

/**
 * AirCycleConfig, used to set configuration values for binding via AirCycle.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class AirCycleConfig {

    private final boolean passIntentBundleOnCreate;

    private AirCycleConfig(boolean passIntentBundleOnCreate) {
        this.passIntentBundleOnCreate = passIntentBundleOnCreate;
    }

    boolean passIntentBundleOnCreate() {
        return passIntentBundleOnCreate;
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
         * Build the AirCycleConfig.
         *
         * @return built AirCycleConfig
         */
        public AirCycleConfig build() {
            return new AirCycleConfig(passIntentBundleOnCreate);
        }
    }
}
