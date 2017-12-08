package edu.umd.mindlab.androidservicetest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.text.Layout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.support.test.espresso.*;
import android.support.test.espresso.web.sugar.Web.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;


import static android.app.PendingIntent.getActivity;
import static android.content.Context.MODE_PRIVATE;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.v4.content.ContextCompat.startActivity;
import static edu.umd.mindlab.androidservicetest.CASLoginActivity.TERMS_ACCEPT;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CASLoginActivityTest {

    @Rule
    public ActivityTestRule<CASLoginActivity> rule  = new  ActivityTestRule<>(CASLoginActivity.class);

    @Test
    public void ensureViewIsPresent() throws Exception {
        CASLoginActivity activity = rule.getActivity();
        WebView viewById = (WebView) activity.findViewById(R.id.caswebview);
        assertThat(viewById,notNullValue());

        onWebView().check(webMatches(getCurrentUrl(), containsString("https://login.umd.edu/")));

        activity.finish(); // old activity instance is destroyed and shut down.
        activity = rule.getActivity();
        getInstrumentation().callActivityOnRestart(activity);

        Intents.init();

        rule.launchActivity(new Intent());

        intended(hasComponent(CASLoginActivity.class.getName()));

        Intents.release();
        LoggedIn log = LoggedIn.getLog();
        if (log.getLoggedIn()) {
            rule.launchActivity(new Intent());

            intended(hasComponent(MainActivity.class.getName()));
        }
        activity.destroyWebView();

    }
    Intent intent;
    SharedPreferences.Editor preferencesEditor;

    @Rule
    public ActivityTestRule<CASLoginActivity> mActivityRule = new ActivityTestRule<>(
            CASLoginActivity.class,
            true,
            false); // Activity is not launched immediately

    @Before
    public void setUp() {
        intent = new Intent();
        Context context = getInstrumentation().getTargetContext();

        // create a SharedPreferences editor
        preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
    }


    @Test
    public void onPageFinishedRejectingTerms() {
        String testUsername = "test";

        // Set SharedPreferences data
        preferencesEditor.putString("edu.umd.mindlab.androidservicetest", String.valueOf(MODE_PRIVATE));
        preferencesEditor.putBoolean(TERMS_ACCEPT, false);
        preferencesEditor.commit();

        // Launch activity
        CASLoginActivity activity = rule.getActivity();
        Intent infoIntent = new Intent(activity, GetPersonalInfo.class);
        mActivityRule.launchActivity(intent);

        //onView(withId(R.id.textview_account_username)).check(matches(isDisplayed())).check(matches(withText(testUsername)));
    }

    @Test
    public void onPageFinishedAcceptingTerms() {
        String testUsername = "test";

        // Set SharedPreferences data
        preferencesEditor.putString("edu.umd.mindlab.androidservicetest", String.valueOf(MODE_PRIVATE));
        preferencesEditor.putBoolean(TERMS_ACCEPT, true);
        preferencesEditor.commit();

        // Launch activity
        CASLoginActivity activity = rule.getActivity();
        Intent mainIntent = new Intent(activity, MainActivity.class);
        mActivityRule.launchActivity(intent);


        //onView(withId(R.id.textview_account_username)).check(matches(isDisplayed())).check(matches(withText(testUsername)));
    }

    @Test
    public void pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }

}