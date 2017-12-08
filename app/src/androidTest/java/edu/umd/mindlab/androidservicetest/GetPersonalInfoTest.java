package edu.umd.mindlab.androidservicetest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static android.content.Context.MODE_PRIVATE;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class GetPersonalInfoTest {

    @Rule
    public ActivityTestRule<GetPersonalInfo> rule  = new  ActivityTestRule<>(GetPersonalInfo.class);

    @Test
    public void checkInvalidData() throws Exception {
        GetPersonalInfo activity = rule.getActivity();
        // activity.getContentView(R.layout.activity_get_personal_info);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        assertThat(activity.getSupportActionBar(), notNullValue());

//        Layout layout = activity.getL(R.layout.activity_get_personal_info);
//        assertThat(layout,notNullValue());
        //assertEquals(toolbar,activity.getSupportActionBar());

        EditText fname = (EditText) activity.findViewById(R.id.firstNameEdit);
        EditText lname = (EditText) activity.findViewById(R.id.lastNameEdit);
        EditText dob = (EditText) activity.findViewById(R.id.dobEdit);
        EditText uid = (EditText) activity.findViewById(R.id.uidEdit);
        Button infoSubmit = (Button) activity.findViewById(R.id.submitPersInfo);

        onView(withId(R.id.submitPersInfo)).perform(click());
        if (infoSubmit.isEnabled()) {
            String first_name = fname.getText().toString();
            assertThat(first_name, notNullValue());
            String last_name = lname.getText().toString();
            assertThat(last_name, notNullValue());
            String birth_date = dob.getText().toString();
            assertThat(birth_date, notNullValue());
            String UID = uid.getText().toString();
            assertThat(UID, notNullValue());
        }


    }

    @Test
    public void checkValidData() throws Exception {
        GetPersonalInfo activity = rule.getActivity();
        // activity.getContentView(R.layout.activity_get_personal_info);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        assertThat(activity.getSupportActionBar(), notNullValue());

        Button infoSubmit = (Button) activity.findViewById(R.id.submitPersInfo);
        EditText fname = (EditText) activity.findViewById(R.id.firstNameEdit);
        EditText lname = (EditText) activity.findViewById(R.id.lastNameEdit);
        EditText dob = (EditText) activity.findViewById(R.id.dobEdit);
        EditText uid = (EditText) activity.findViewById(R.id.uidEdit);
        onView(withId(R.id.firstNameEdit)).perform(replaceText("Nirat"));
        onView(withId(R.id.lastNameEdit)).perform(replaceText("Saini"));
        onView(withId(R.id.dobEdit)).perform(replaceText("06/16"));
        onView(withId(R.id.uidEdit)).perform(replaceText("115528024"));
        onView(withId(R.id.submitPersInfo)).perform(click());


    }

    @Test
    public void checkValidDataWrongBday() throws Exception {
        GetPersonalInfo activity = rule.getActivity();
        // activity.getContentView(R.layout.activity_get_personal_info);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        assertThat(activity.getSupportActionBar(), notNullValue());

        Button infoSubmit = (Button) activity.findViewById(R.id.submitPersInfo);
        EditText fname = (EditText) activity.findViewById(R.id.firstNameEdit);
        EditText lname = (EditText) activity.findViewById(R.id.lastNameEdit);
        EditText dob = (EditText) activity.findViewById(R.id.dobEdit);
        EditText uid = (EditText) activity.findViewById(R.id.uidEdit);
        onView(withId(R.id.firstNameEdit)).perform(replaceText("Nirat"));
        onView(withId(R.id.lastNameEdit)).perform(replaceText("Saini"));
        onView(withId(R.id.dobEdit)).perform(replaceText("0616"));
        onView(withId(R.id.uidEdit)).perform(replaceText("115528024"));
        onView(withId(R.id.submitPersInfo)).perform(click());


    }



    Intent intent;
    SharedPreferences.Editor preferencesEditor;

    @Rule
    public ActivityTestRule<GetPersonalInfo> mActivityRule = new ActivityTestRule<>(
            GetPersonalInfo.class,
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
    public void storeUIDTest() {
        String testUsername = "test";

        // Set SharedPreferences data
        preferencesEditor.putString("edu.umd.mindlab.androidservicetest", String.valueOf(MODE_PRIVATE));
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