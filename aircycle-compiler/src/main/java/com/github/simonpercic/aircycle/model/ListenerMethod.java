package com.github.simonpercic.aircycle.model;

import com.github.simonpercic.aircycle.exception.MethodArgumentsException;
import com.github.simonpercic.aircycle.model.type.ActivityLifecycle;
import com.github.simonpercic.aircycle.model.type.ListenerArg;
import com.github.simonpercic.aircycle.utils.StringUtils;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class ListenerMethod {

    private final String methodName;
    private final List<ListenerArg> args;

    public ListenerMethod(String methodName, List<ListenerArg> args) {
        this.methodName = methodName;
        this.args = ImmutableList.copyOf(args);
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

        public Builder addBundleArg() throws MethodArgumentsException {
            if (args.contains(ListenerArg.BUNDLE)) {
                String message = String.format("`bundle` arg already present in `%s`", methodName);
                throw new MethodArgumentsException(message);
            }

            if (lifecycleType != ActivityLifecycle.CREATE && lifecycleType != ActivityLifecycle.SAVE_INSTANCE_STATE) {
                String message = String.format(
                        "`bundle` arg can only be added for `onCreate` or `onSaveInstanceState` in `%s`",
                        methodName);

                throw new MethodArgumentsException(message);
            }

            args.add(ListenerArg.BUNDLE);

            return this;
        }

        public Builder addActivityArg() throws MethodArgumentsException {
            if (args.contains(ListenerArg.ACTIVITY)) {
                String message = String.format("`activity` arg already present in `%s`", methodName);
                throw new MethodArgumentsException(message);
            }

            args.add(ListenerArg.ACTIVITY);

            return this;
        }

        public ListenerMethod build() {
            return new ListenerMethod(methodName, args);
        }
    }
}
