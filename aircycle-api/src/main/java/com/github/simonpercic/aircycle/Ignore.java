package com.github.simonpercic.aircycle;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ignore an annotated listener method that would otherwise be bound to an Activity's lifecycle callback.
 * This enables you to skip a listener method that matches the specified callback naming policy, i.e.:
 * <p>
 * onCreate() / onActivityCreated(),
 * onStart() / onActivityStarted(),
 * onResume() / onActivityResumed(),
 * onPause() / onActivityPaused(),
 * onStop() / onActivityStopped(),
 * onSaveInstanceState() / onActivitySaveInstanceState(),
 * onDestroy() / onActivityDestroyed()
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Retention(RetentionPolicy.SOURCE)
@Target(value = ElementType.METHOD)
public @interface Ignore {
}
