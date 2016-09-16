package com.github.simonpercic.aircycle;

/**
 * AirCycleDefaultConfig, use it to set app-wide configuration defaults.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class AirCycleDefaultConfig {

    private static AirCycleConfig defaultConfig;

    private AirCycleDefaultConfig() {
        // no instance
    }

    /**
     * Set the default config.
     *
     * @param defaultConfig AirCycleConfig used as the default
     */
    public static void setConfig(AirCycleConfig defaultConfig) {
        AirCycleDefaultConfig.defaultConfig = defaultConfig;
    }

    static AirCycleConfig getConfig() {
        return defaultConfig;
    }
}
