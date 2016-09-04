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
public class AirCycleTest {

    @Test
    public void testPrivateField() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleActivity extends Activity {\n"
                + "    @AirCycle private ActivityAirCycle airCycle;\n"
                + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input))
                .processedWith(new AirCycleProcessor())
                .failsToCompile()
                .withErrorContaining("Fields annotated with @AirCycle must not be private.");
    }

    @Test
    public void testNoEnclosingClass() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleObject", "package activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleObject {\n"
                + "    @AirCycle ActivityAirCycle airCycle;\n"
                + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(input))
                .processedWith(new AirCycleProcessor())
                .failsToCompile()
                .withErrorContaining("Class `activity.SampleObject` enclosing the field "
                        + "`airCycle` is not a subtype of an Activity.");
    }

    @Test
    public void testEnclosingClassNotActivity() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleObject", "package activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleObject extends BaseObject {\n"
                + "    @AirCycle ActivityAirCycle airCycle;\n"
                + "}");

        JavaFileObject base = JavaFileObjects.forSourceString("activity.BaseObject", "package activity;\n"
                + "\n"
                + "public class BaseObject {\n"
                + "    \n"
                + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(base, input))
                .processedWith(new AirCycleProcessor())
                .failsToCompile()
                .withErrorContaining("Class `activity.SampleObject` enclosing the field "
                        + "`airCycle` is not a subtype of an Activity.");
    }

    @Test
    public void testEnclosingClassActivity() throws Exception {
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
                        + "import com.github.simonpercic.aircycle.BaseAirCycle;\n"
                        + "\n"
                        + "public class SampleActivityAirCycle extends BaseAirCycle<SampleActivity> {\n"
                        + "    protected SampleActivityAirCycle(SampleActivity activity) {\n"
                        + "        super(activity);\n"
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
    public void testEnclosingClassExtendedActivity() throws Exception {
        JavaFileObject input = JavaFileObjects.forSourceString("activity.SampleActivity", "package activity;\n"
                + "\n"
                + "import com.github.simonpercic.aircycle.ActivityAirCycle;\n"
                + "import com.github.simonpercic.aircycle.AirCycle;\n"
                + "\n"
                + "public class SampleActivity extends BaseActivity {\n"
                + "    @AirCycle ActivityAirCycle airCycle;\n"
                + "}");

        JavaFileObject baseActivity = JavaFileObjects.forSourceString("activity.BaseActivity", "package activity;\n"
                + "\n"
                + "import android.app.Activity;\n"
                + "\n"
                + "public class BaseActivity extends Activity {\n"
                + "    \n"
                + "}");

        assertAbout(javaSources())
                .that(ImmutableList.of(baseActivity, input))
                .processedWith(new AirCycleProcessor())
                .compilesWithoutWarnings();
    }
}
