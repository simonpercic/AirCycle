package com.github.simonpercic.aircycle;

import com.google.common.collect.ImmutableList;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

/**
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class BuiltInListenersTest {

    @Test
    public void testActivityAirCycle() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle ActivityAirCycle airCycle;\n"
                + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("activity.SampleActivityAirCycle",
                "package activity;\n"
                        + "\n"
                        + "import android.os.Bundle;\n"
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "import java.lang.Override;\n"
                        + "\n"
                        + "public class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity) {\n"
                        + "        super(activity);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityCreated(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onCreate();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onStart();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityResumed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onResume();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityPaused(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onPause();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStopped(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onStop();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivitySaveInstanceState(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onSaveInstanceState();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityDestroyed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onDestroy();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity).registerCallbacks();\n"
                        + "    }\n"
                        + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input))
                .processedWith(new AirCycleProcessor())
                .compilesWithoutWarnings()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testActivityBundleAirCycle() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityBundleAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle ActivityBundleAirCycle airCycle;\n"
                + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("activity.SampleActivityAirCycle",
                "package activity;\n"
                        + "\n"
                        + "import android.os.Bundle;\n"
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "import java.lang.Override;\n"
                        + "\n"
                        + "public class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity) {\n"
                        + "        super(activity);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityCreated(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onCreate(bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onStart();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityResumed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onResume();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityPaused(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onPause();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStopped(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onStop();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivitySaveInstanceState(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onSaveInstanceState(bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityDestroyed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onDestroy();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity).registerCallbacks();\n"
                        + "    }\n"
                        + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input))
                .processedWith(new AirCycleProcessor())
                .compilesWithoutWarnings()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testActivityPassAirCycle() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityPassAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle ActivityPassAirCycle<SampleActivity> airCycle;\n"
                + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("activity.SampleActivityAirCycle",
                "package activity;\n"
                        + "\n"
                        + "import android.os.Bundle;\n"
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "import java.lang.Override;\n"
                        + "\n"
                        + "public class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity) {\n"
                        + "        super(activity);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityCreated(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onCreate(activity, bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onStart(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityResumed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onResume(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityPaused(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onPause(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStopped(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onStop(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivitySaveInstanceState(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onSaveInstanceState(activity, bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityDestroyed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onDestroy(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity).registerCallbacks();\n"
                        + "    }\n"
                        + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input))
                .processedWith(new AirCycleProcessor())
                .compilesWithoutWarnings()
                .and()
                .generatesSources(expected);
    }

    @Test
    public void testActivityLifecycleCallbacks() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "import android.app.Application.ActivityLifecycleCallbacks;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle ActivityLifecycleCallbacks airCycle;\n"
                + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("activity.SampleActivityAirCycle",
                "package activity;\n"
                        + "\n"
                        + "import android.os.Bundle;\n"
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "import java.lang.Override;\n"
                        + "\n"
                        + "public class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity) {\n"
                        + "        super(activity);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityCreated(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onActivityCreated(activity, bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onActivityStarted(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityResumed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onActivityResumed(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityPaused(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onActivityPaused(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStopped(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onActivityStopped(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivitySaveInstanceState(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onActivitySaveInstanceState(activity, bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityDestroyed(SampleActivity activity) {\n"
                        + "        if (activity.airCycle != null) {\n"
                        + "            activity.airCycle.onActivityDestroyed(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity).registerCallbacks();\n"
                        + "    }\n"
                        + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input))
                .processedWith(new AirCycleProcessor())
                .compilesWithoutWarnings()
                .and()
                .generatesSources(expected);
    }
}
