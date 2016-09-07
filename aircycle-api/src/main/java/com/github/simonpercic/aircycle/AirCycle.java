package com.github.simonpercic.aircycle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Bind an Activity lifecycle callback method to a listener.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = ElementType.FIELD)
public @interface AirCycle {
}
