package edu.umd.mindlab.androidservicetest;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

//import android.support.test.espresso.web.sugar.Web.*;
//import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
//import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
//import static android.support.test.espresso.web.sugar.Web.onWebView;

public class ConsentActivityTest {

    @Rule
    public ActivityTestRule<ConsentActivity> rule  = new  ActivityTestRule<>(ConsentActivity.class);

    @Test
    public void isViewOkay() throws Exception {
        ConsentActivity activity = rule.getActivity();

        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        assertThat(activity.getSupportActionBar(), notNullValue());

        //Content Provider


        PDFView pdfView = (PDFView)activity.findViewById(R.id.pdfView);
        Button agreeButton = (Button) activity.findViewById(R.id.agreeBtn);
        Button disAgreeButton = (Button) activity.findViewById(R.id.disagreeBtn);

        //onView(withId(R.id.pdfView)).perform(scrollTo());

       // onView(withId(R.id.agreeBtn)).perform(click());
       //onView(withId(R.id.agreeBtn)).perform(scrollTo(),click());

    }

//    @Test
//    public void pressBack() {
//        onView(isRoot()).perform(ViewActions.pressBack());
//    }
}