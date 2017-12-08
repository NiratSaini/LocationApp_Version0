package edu.umd.mindlab.androidservicetest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.SHORTCUT_SERVICE;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static edu.umd.mindlab.androidservicetest.CASLoginActivity.TERMS_ACCEPT;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SendMailTest {

    @Rule
    public ActivityTestRule<SendEmail> rule  = new  ActivityTestRule<>(SendEmail.class);

    @Test
    public void ensureEmail() throws Exception {
        SendEmail activity=rule.getActivity();
        EditText email = (EditText) activity.findViewById(R.id.emailEdit);
        Button sendEmail = (Button) activity.findViewById(R.id.sendConfirmation);
        Button continueButton = (Button) activity.findViewById(R.id.emailContinueButton);
        onView(withId(R.id.emailEdit)).perform(replaceText("abc@gmail.com"));
        onView(withId(R.id.sendConfirmation)).perform(click());
        onView(withId(R.id.emailContinueButton)).perform(click());
        if(sendEmail.isEnabled()) {
            onPageFinishedRejectingTerms();
        }
        if(sendEmail.isEnabled()) {
            onPageFinishedAcceptingTerms();
        }

    }
    Intent intent;
    SharedPreferences.Editor preferencesEditor;

    @Rule
    public ActivityTestRule<SendEmail> mActivityRule = new ActivityTestRule<>(
            SendEmail.class,
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
        mActivityRule.launchActivity(intent);

        //onView(withId(R.id.textview_account_username)).check(matches(isDisplayed())).check(matches(withText(testUsername)));
    }
    @Test
    public void onPageFinishedAcceptingTerms() {
        String testUsername = "test";

        // Set SharedPreferences data
        preferencesEditor.putString("edu.umd.mindlab.androidservicetest", String.valueOf(MODE_PRIVATE));
        preferencesEditor.putBoolean(TERMS_ACCEPT, true);

        preferencesEditor.putBoolean(SHORTCUT_SERVICE, false);
        preferencesEditor.commit();

        // Launch activity
        mActivityRule.launchActivity(intent);

        //onView(withId(R.id.textview_account_username)).check(matches(isDisplayed())).check(matches(withText(testUsername)));
    }

    @Test
    public void pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }

}