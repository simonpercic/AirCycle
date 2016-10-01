package com.github.simonpercic.aircycle;

import com.google.common.collect.ImmutableList;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

/**
 * Ignore methods annotation test.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class IgnoreMethodsTest {

    @Test
    public void testIgnoreMethods() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "import listener.CustomListener;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle CustomListener listener;\n"
                + "}");

        JavaFileObject listener = JavaFileObjects.forSourceString("listener.CustomListener", "package listener;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "import android.os.Bundle;\n"
                + "import com.github.simonpercic.aircycle.Ignore;\n"
                + "\n"
                + "public class CustomListener {\n"
                + "    \n"
                + "    @Ignore public void onStart(Activity act) {\n"
                + "        \n"
                + "    }\n"
                + "    \n"
                + "    public void onStart() {\n"
                + "        \n"
                + "    }\n"
                + "\n"
                + "    @Ignore public void onResume() {\n"
                + "        \n"
                + "    }\n"
                + "\n"
                + "    public void onActivityStopped() {\n"
                + "        \n"
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
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onStart();\n"
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
}
