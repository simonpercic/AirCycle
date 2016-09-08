package com.github.simonpercic.aircycle;

import com.google.common.collect.ImmutableList;
import com.google.testing.compile.JavaFileObjects;

import org.junit.Test;

import javax.tools.JavaFileObject;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

/**
 * Multiple listeners test.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class MultipleListenersTest {

    @Test
    public void testMultipleListeners() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityAirCycle;\n"
                + "import com.github.simonpercic.aircycle.ActivityBundleAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle ActivityAirCycle first;\n"
                + "    @AirCycle ActivityBundleAirCycle second;\n"
                + "}");

        JavaFileObject expected = JavaFileObjects.forSourceString("activity.SampleActivityAirCycle",
                "package activity;\n"
                        + "\n"
                        + "import android.os.Bundle;\n"
                        + "\n"
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "\n"
                        + "import java.lang.Override;\n"
                        + "\n"
                        + "class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity) {\n"
                        + "        super(activity);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityCreated(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.first != null) {\n"
                        + "            activity.first.onCreate();\n"
                        + "        }\n"
                        + "\n"
                        + "        if (activity.second != null) {\n"
                        + "            activity.second.onCreate(bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.first != null) {\n"
                        + "            activity.first.onStart();\n"
                        + "        }\n"
                        + "\n"
                        + "        if (activity.second != null) {\n"
                        + "            activity.second.onStart();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityResumed(SampleActivity activity) {\n"
                        + "        if (activity.first != null) {\n"
                        + "            activity.first.onResume();\n"
                        + "        }\n"
                        + "\n"
                        + "        if (activity.second != null) {\n"
                        + "            activity.second.onResume();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityPaused(SampleActivity activity) {\n"
                        + "        if (activity.first != null) {\n"
                        + "            activity.first.onPause();\n"
                        + "        }\n"
                        + "\n"
                        + "        if (activity.second != null) {\n"
                        + "            activity.second.onPause();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStopped(SampleActivity activity) {\n"
                        + "        if (activity.first != null) {\n"
                        + "            activity.first.onStop();\n"
                        + "        }\n"
                        + "\n"
                        + "        if (activity.second != null) {\n"
                        + "            activity.second.onStop();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivitySaveInstanceState(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.first != null) {\n"
                        + "            activity.first.onSaveInstanceState();\n"
                        + "        }\n"
                        + "\n"
                        + "        if (activity.second != null) {\n"
                        + "            activity.second.onSaveInstanceState(bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityDestroyed(SampleActivity activity) {\n"
                        + "        if (activity.first != null) {\n"
                        + "            activity.first.onDestroy();\n"
                        + "        }\n"
                        + "\n"
                        + "        if (activity.second != null) {\n"
                        + "            activity.second.onDestroy();\n"
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
