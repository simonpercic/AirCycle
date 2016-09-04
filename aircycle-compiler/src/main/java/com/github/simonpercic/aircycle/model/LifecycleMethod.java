package com.github.simonpercic.aircycle.model;

import com.github.simonpercic.aircycle.model.type.ActivityLifecycle;
import com.github.simonpercic.aircycle.model.type.ListenerArg;
import com.github.simonpercic.aircycle.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class LifecycleMethod {

    private final ActivityLifecycle lifecycleType;
    private final String methodName;
    private final List<ListenerArg> args;

    private LifecycleMethod(ActivityLifecycle lifecycleType, String methodName, List<ListenerArg> args) {
        this.lifecycleType = lifecycleType;
        this.methodName = methodName;
        this.args = args;
    }

    public ActivityLifecycle getLifecycleType() {
        return lifecycleType;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<ListenerArg> getArgs() {
        return args;
    }

    public static Builder builder(ActivityLifecycle activityLifecycle, String methodName) {
        if (activityLifecycle == null) {
            throw new IllegalArgumentException("activityLifecycle is null");
        }

        if (StringUtils.isEmpty(methodName)) {
            throw new IllegalArgumentException("methodName is empty");
        }

        return new Builder(activityLifecycle, methodName);
    }

    public static class Builder {

        private final ActivityLifecycle lifecycleType;
        private final String methodName;
        private final List<ListenerArg> args;

        private Builder(ActivityLifecycle lifecycleType, String methodName) {
            this.lifecycleType = lifecycleType;
            this.methodName = methodName;
            this.args = new ArrayList<>(2);
        }

        public Builder addBundleArg() {
            if (args.contains(ListenerArg.BUNDLE)) {
                throw new IllegalArgumentException("`bundle` arg already present");
            }

            if (lifecycleType != ActivityLifecycle.CREATE && lifecycleType != ActivityLifecycle.SAVE_INSTANCE_STATE) {
                throw new IllegalArgumentException(
                        "`bundle` arg can only be added for `onCreate` or `onSaveInstanceState`");
            }

            args.add(ListenerArg.BUNDLE);

            return this;
        }

        public Builder addActivityArg() {
            if (args.contains(ListenerArg.ACTIVITY)) {
                throw new IllegalArgumentException("`activity` arg already present");
            }

            args.add(ListenerArg.ACTIVITY);

            return this;
        }

        public LifecycleMethod build() {
            return new LifecycleMethod(lifecycleType, methodName, args);
        }
    }
}
