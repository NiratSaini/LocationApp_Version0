package edu.umd.mindlab.androidservicetest;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.test.AndroidTestCase;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static java.security.AccessController.getContext;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class SnoozeTest {

    @Rule
    public ActivityTestRule<Snooze> rule  = new  ActivityTestRule<>(Snooze.class);

    @Test
    public void ensureViewIsPresent() throws Exception {
        Snooze activity = rule.getActivity();
        TextView counter = (TextView) activity.findViewById(R.id.countDown);
        Button cancelButton = (Button) activity.findViewById(R.id.cancelSnooze);
        onView(withId(R.id.cancelSnooze)).perform(click());
        LoggedIn log = LoggedIn.getLog();

        if (cancelButton.isEnabled()) {
            assertEquals(false, log.getSnoozed());
        }

    }
    @Test
    public void pressBack() {
        onView(isRoot()).perform(ViewActions.pressBack());
    }

//    public void finishedReceiverTest() {
//        {
//            public finishedReceiver mReceiver;
//
//            @Override
//            protected void setUp () throws Exception
//            {
//                super.setUp();
//
//                mReceiver = new finishedReceiver();
//            }
//
//        public void testStartActivity ()
//        {
//            Intent intent = new Intent(Snooze.this, SnoozeService.class);
//            //intent.putExtra(Intent.EXTRA_PHONE_NUMBER, "01234567890");
//
//            MainActivity.CountReceiver mReceiver;
//            mReceiver.onReceive(getContext(), intent);
//        }
//    }
    }

