package com.github.simonpercic.aircycle.manager;

import com.github.simonpercic.aircycle.ActivityLifecycle;
import com.github.simonpercic.aircycle.model.type.ActivityLifecycleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/**
 * Activity lifecycle converter manager.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@Singleton
public class ActivityLifecycleConverter {

    private final ProcessorLogger logger;

    @Inject
    public ActivityLifecycleConverter(ProcessorLogger logger) {
        this.logger = logger;
    }

    public List<ActivityLifecycleType> fromInts(int[] values, VariableElement field, TypeElement enclosingElement) {
        if (values == null || values.length == 0) {
            return Collections.emptyList();
        }

        List<ActivityLifecycleType> result = new ArrayList<>();

        for (int value : values) {
            ActivityLifecycleType activityLifecycle = fromInt(value);
            if (activityLifecycle == null) {
                String message = String.format(Locale.US, "Field `%s` in `%s` has an invalid lifecycle type `%d`. "
                                + "Valid values are: \n"
                                + "ActivityLifecycle.CREATE (1), \n"
                                + "ActivityLifecycle.START (2), \n"
                                + "ActivityLifecycle.RESUME (3), \n"
                                + "ActivityLifecycle.PAUSE (4), \n"
                                + "ActivityLifecycle.STOP (5), \n"
                                + "ActivityLifecycle.SAVE_INSTANCE_STATE (6) or \n"
                                + "ActivityLifecycle.DESTROY (7).",
                        field.getSimpleName(),
                        enclosingElement.getQualifiedName(),
                        value);

                logger.e(message, enclosingElement);
                return null;
            }

            result.add(activityLifecycle);
        }

        return result;
    }

    private static ActivityLifecycleType fromInt(int value) {
        switch (value) {
            case ActivityLifecycle.CREATE:
                return ActivityLifecycleType.CREATE;
            case ActivityLifecycle.START:
                return ActivityLifecycleType.START;
            case ActivityLifecycle.RESUME:
                return ActivityLifecycleType.RESUME;
            case ActivityLifecycle.PAUSE:
                return ActivityLifecycleType.PAUSE;
            case ActivityLifecycle.STOP:
                return ActivityLifecycleType.STOP;
            case ActivityLifecycle.SAVE_INSTANCE_STATE:
                return ActivityLifecycleType.SAVE_INSTANCE_STATE;
            case ActivityLifecycle.DESTROY:
                return ActivityLifecycleType.DESTROY;
            default:
                return null;
        }
    }
}
