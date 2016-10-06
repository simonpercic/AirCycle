package com.github.simonpercic.aircycle;

import android.support.annotation.Nullable;

/**
 * AirCycleDefaultConfig, use it to set app-wide configuration defaults.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public final class AirCycleDefaultConfig {

    @Nullable private static AirCycleConfig defaultConfig;

    private AirCycleDefaultConfig() {
        // no instance
    }

    /**
     * Set the default config.
     *
     * @param defaultConfig AirCycleConfig used as the default
     */
    public static void setConfig(@Nullable AirCycleConfig defaultConfig) {
        AirCycleDefaultConfig.defaultConfig = defaultConfig;
    }

    @Nullable static AirCycleConfig getConfig() {
        return defaultConfig;
    }
}
