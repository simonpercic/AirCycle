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
public class CustomListenersTest {

    @Test
    public void testCustomListenerClass() throws Exception {
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
                + "\n"
                + "public class CustomListener {\n"
                + "    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {\n"
                + "    }\n"
                + "\n"
                + "    public void onStart(Activity act) {\n"
                + "    }\n"
                + "\n"
                + "    public void onResume() {\n"
                + "    }\n"
                + "\n"
                + "    public void onActivityStopped() {\n"
                + "    }\n"
                + "\n"
                + "    public void onSaveInstanceState(Bundle outState) {\n"
                + "    }\n"
                + "\n"
                + "    public void onActivityDestroyed(Activity activity) {\n"
                + "    }\n"
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
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onActivityCreated(activity, bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onStart(activity);\n"
                        + "        }\n"
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
                        + "    @Override\n"
                        + "    protected void notifyOnActivitySaveInstanceState(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onSaveInstanceState(bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityDestroyed(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onActivityDestroyed(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity).registerCallbacks();\n"
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
    public void testCustomListenerInterface() throws Exception {
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
                + "\n"
                + "public interface CustomListener {\n"
                + "    void onActivityCreated(Activity activity);\n"
                + "\n"
                + "    void onStart(Activity act);\n"
                + "\n"
                + "    void onResume();\n"
                + "\n"
                + "    void onActivityStopped();\n"
                + "\n"
                + "    void onActivitySaveInstanceState(Bundle outState);\n"
                + "\n"
                + "    void onDestroy();\n"
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
                        + "public class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity) {\n"
                        + "        super(activity);\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityCreated(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onActivityCreated(activity);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityStarted(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onStart(activity);\n"
                        + "        }\n"
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
                        + "    @Override\n"
                        + "    protected void notifyOnActivitySaveInstanceState(SampleActivity activity, Bundle bundle) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onActivitySaveInstanceState(bundle);\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    @Override\n"
                        + "    protected void notifyOnActivityDestroyed(SampleActivity activity) {\n"
                        + "        if (activity.listener != null) {\n"
                        + "            activity.listener.onDestroy();\n"
                        + "        }\n"
                        + "    }\n"
                        + "\n"
                        + "    static void bind(SampleActivity activity) {\n"
                        + "        new SampleActivityAirCycle(activity).registerCallbacks();\n"
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
