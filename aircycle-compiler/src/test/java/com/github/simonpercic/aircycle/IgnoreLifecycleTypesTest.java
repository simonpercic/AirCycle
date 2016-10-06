package com.github.simonpercic.aircycle;

import com.google.common.collect.ImmutableList;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import java.util.Locale;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

/**
 * Ignore Activity lifecycle callbacks test.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class IgnoreLifecycleTypesTest {

    @Test
    public void testIgnoreLifecycleSingleType() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "import com.github.simonpercic.aircycle.ActivityLifecycle;\n"
                + "\n"
                + "import listener.CustomListener;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle(ignore = ActivityLifecycle.START) CustomListener listener;\n"
                + "}");

        JavaFileObject listener = JavaFileObjects.forSourceString("listener.CustomListener", "package listener;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "public class CustomListener {\n"
                + "\n"
                + "    public void onStart(Activity act) {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onStart() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onResume() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onActivityStopped() {\n"
                + "\n"
                + "    }\n"
                + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("activity.SampleActivityAirCycle",
                "package activity;\n"
                        + "\n"
                        + "import com.github.simonpercic.aircycle.AirCycleConfig;\n"
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "import java.lang.Override;\n"
                        + "\n"
                        + "class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity, AirCycleConfig config) {\n"
                        + "        super(activity, config);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityResumed(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onResume();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStopped(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onActivityStopped();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity, null).registerCallbacks();\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity, AirCycleConfig config) {\n"
                        + "        new SampleActivityAirCycle(activity, config).registerCallbacks();\n"
                        + "    }\n"
                        + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input, listener))
                .processedWith(new AirCycleProcessor())
                .compilesWithoutWarnings()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testIgnoreLifecycleMultipleTypes() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "import com.github.simonpercic.aircycle.ActivityLifecycle;\n"
                + "\n"
                + "import listener.CustomListener;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle(ignore = {ActivityLifecycle.START, ActivityLifecycle.RESUME})\n"
                + "    CustomListener listener;\n"
                + "}");

        JavaFileObject listener = JavaFileObjects.forSourceString("listener.CustomListener", "package listener;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "public class CustomListener {\n"
                + "\n"
                + "    public void onStart(Activity act) {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onStart() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onResume() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onActivityStopped() {\n"
                + "\n"
                + "    }\n"
                + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("activity.SampleActivityAirCycle",
                "package activity;\n"
                        + "\n"
                        + "import com.github.simonpercic.aircycle.AirCycleConfig;\n"
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "import java.lang.Override;\n"
                        + "\n"
                        + "class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity, AirCycleConfig config) {\n"
                        + "        super(activity, config);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStopped(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onActivityStopped();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity, null).registerCallbacks();\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity, AirCycleConfig config) {\n"
                        + "        new SampleActivityAirCycle(activity, config).registerCallbacks();\n"
                        + "    }\n"
                        + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input, listener))
                .processedWith(new AirCycleProcessor())
                .compilesWithoutWarnings()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testIgnoreLifecycleSingleInvalidType() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "import com.github.simonpercic.aircycle.ActivityLifecycle;\n"
                + "\n"
                + "import listener.CustomListener;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle(ignore = 12) CustomListener listener;\n"
                + "}");

        JavaFileObject listener = JavaFileObjects.forSourceString("listener.CustomListener", "package listener;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "public class CustomListener {\n"
                + "\n"
                + "    public void onStart(Activity act) {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onStart() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onResume() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onActivityStopped() {\n"
                + "\n"
                + "    }\n"
                + "}");

        String message = String.format(Locale.US, "Field `%s` in `%s` has an invalid lifecycle type `%d`. ",
                "listener",
                "activity.SampleActivity",
                12);

        assertAbout(javaSources())
                .that(ImmutableList.of(input, listener))
                .processedWith(new AirCycleProcessor())
                .failsToCompile()
                .withErrorContaining(message);
    }

    @Test
    public void testIgnoreLifecycleMixedInvalidTypes() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "import com.github.simonpercic.aircycle.ActivityLifecycle;\n"
                + "\n"
                + "import listener.CustomListener;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle(ignore = {ActivityLifecycle.START, 12})\n"
                + "    CustomListener listener;\n"
                + "}");

        JavaFileObject listener = JavaFileObjects.forSourceString("listener.CustomListener", "package listener;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "public class CustomListener {\n"
                + "\n"
                + "    public void onStart(Activity act) {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onStart() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onResume() {\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    public void onActivityStopped() {\n"
                + "\n"
                + "    }\n"
                + "}");

        String message = String.format(Locale.US, "Field `%s` in `%s` has an invalid lifecycle type `%d`. ",
                "listener",
                "activity.SampleActivity",
                12);

        assertAbout(javaSources())
                .that(ImmutableList.of(input, listener))
                .processedWith(new AirCycleProcessor())
                .failsToCompile()
                .withErrorContaining(message);
    }
}
