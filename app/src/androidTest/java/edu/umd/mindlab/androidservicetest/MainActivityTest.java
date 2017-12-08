package edu.umd.mindlab.androidservicetest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ServiceTestRule;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule  = new  ActivityTestRule<>(MainActivity.class);

    @Test
    public void toggleButtonTest() throws Exception {
        MainActivity activity = rule.getActivity();
        View view= activity.findViewById(R.layout.activity_main);




        TextView countDisplay = (TextView) activity.findViewById(R.id.dataView);
        ToggleButton toggleLoc = (ToggleButton) activity.findViewById(R.id.enableToggle);

        if(toggleLoc.isChecked()){
            onView(withId(R.id.textLocation)).check(matches(withText("Currently sharing your location")));
        }
        else{
            onView(withId(R.id.textLocation)).check(matches(withText("Not sharing your location")));
        }

        onView(withId(R.id.enableToggle)).perform(click());

        if(toggleLoc.isChecked()){
            onView(withId(R.id.textLocation)).check(matches(withText("Currently sharing your location")));
        }
        else{
            onView(withId(R.id.textLocation)).check(matches(withText("Not sharing your location")));
        }




    }


    @Test
    public void pdfButtonTest() throws Exception {
        MainActivity activity = rule.getActivity();
        LoggedIn log = LoggedIn.getLog();
        Button viewPDF = (Button) activity.findViewById(R.id.viewTermsButton);
        onView(withId(R.id.viewTermsButton)).perform(click());
        //onView(withId(R.id.place_holder2)).check();
        //onView(withId(R.id.place_holder2)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.place_holder2)).check(matches(isDisplayed()));

    }

    @Test
    public void snoozeButtonTestDefault() throws Exception {
        MainActivity activity = rule.getActivity();
        EditText hours = (EditText) activity.findViewById(R.id.hourEdit);
        EditText minutes = (EditText) activity.findViewById(R.id.minutesEdit);
        Button snoozeButton = (Button) activity.findViewById(R.id.snoozeButton);
        onView(withId(R.id.snoozeButton)).perform(click());
        LoggedIn log = LoggedIn.getLog();

        if (snoozeButton.isEnabled()) {
            assertEquals(false, log.getSending());
        }
    }

    @Test
    public void snoozeButtonTestWithValues() throws Exception {
        MainActivity activity = rule.getActivity();
        EditText hours = (EditText) activity.findViewById(R.id.hourEdit);
        EditText minutes = (EditText) activity.findViewById(R.id.minutesEdit);
        Button snoozeButton = (Button) activity.findViewById(R.id.snoozeButton);
        onView(withId(R.id.hourEdit)).perform(replaceText("1"));
        onView(withId(R.id.minutesEdit)).perform(replaceText("25"));
        onView(withId(R.id.snoozeButton)).perform(click());
        LoggedIn log = LoggedIn.getLog();

        if (snoozeButton.isEnabled()) {
            assertEquals(false, log.getSending());
        }
    }
    @Test
    public void snoozeButtonTestWithHourOnly() throws Exception {
        MainActivity activity = rule.getActivity();
        EditText hours = (EditText) activity.findViewById(R.id.hourEdit);
        EditText minutes = (EditText) activity.findViewById(R.id.minutesEdit);
        Button snoozeButton = (Button) activity.findViewById(R.id.snoozeButton);
        onView(withId(R.id.hourEdit)).perform(replaceText("-1"));
        onView(withId(R.id.snoozeButton)).perform(click());
        LoggedIn log = LoggedIn.getLog();

        if (snoozeButton.isEnabled()) {
            assertEquals(false, log.getSending());
        }
    }
    @Test
    public void snoozeButtonTestWithMinuteOnly() throws Exception {
        MainActivity activity = rule.getActivity();
        EditText hours = (EditText) activity.findViewById(R.id.hourEdit);
        EditText minutes = (EditText) activity.findViewById(R.id.minutesEdit);
        Button snoozeButton = (Button) activity.findViewById(R.id.snoozeButton);
        onView(withId(R.id.minutesEdit)).perform(replaceText("-1"));
        onView(withId(R.id.snoozeButton)).perform(click());
        LoggedIn log = LoggedIn.getLog();
    }
    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    // test for a service which is started with startService
    @Test
    public void testSnoozeService() throws TimeoutException {
        try {
            mServiceRule.
                    startService(new Intent(InstrumentationRegistry.getTargetContext(),
                            SnoozeService.class));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        // test code
    }

    @Test
    public void resetCountButtonTest() throws Exception {
        MainActivity activity = rule.getActivity();
        Button resetCounter = (Button) activity.findViewById(R.id.resetCountButton);
        //AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        onView(withId(R.id.resetCountButton)).perform(click());
    }

    @Test
    public void resetAppButtonTest() throws Exception {
        MainActivity activity = rule.getActivity();
        Button resetAppButton = (Button) activity.findViewById(R.id.testButton);
        //AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        onView(withId(R.id.testButton)).perform(click());
    }

    @Test
    public void logOutButtonTest() throws Exception {
        MainActivity activity = rule.getActivity();
        LoggedIn log = LoggedIn.getLog();
        assertTrue(log.getLoggedIn());

        Button logOutButton = (Button) activity.findViewById(R.id.enableToggle);
        onView(withId(R.id.logOutButton)).perform(click());

        if (logOutButton.isEnabled()) {
            assertEquals(false, log.getLoggedIn());
            assertEquals(false, log.getSending());
        }
    }



}