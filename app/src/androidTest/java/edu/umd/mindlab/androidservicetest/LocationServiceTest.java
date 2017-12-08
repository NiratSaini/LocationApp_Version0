package edu.umd.mindlab.androidservicetest;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ServiceTestRule;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

//import android.support.test.espresso.web.sugar.Web.*;
//import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
//import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
//import static android.support.test.espresso.web.sugar.Web.onWebView;

public class LocationServiceTest {

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();


//
    // test for a service which is started with bindService
    public void newTest() throws Exception {

        Intent serviceIntent = new Intent(InstrumentationRegistry.getTargetContext(), LocationService.class);
    }
//        // Data can be passed to the service via the Intent.
//      // serviceIntent.putExtra(LocationService.SEED_KEY, 42L);
//
//        // Bind the service and grab a reference to the binder.
////        IBinder binder = mServiceRule.bindService(serviceIntent);
//
//        // Get the reference to the service, or you can call
//        // public methods on the binder directly.
////        LocationService service =
////                ((LocationService.LocalBinder) binder).getService();
////
////        // Verify that the service is working correctly.
////        assertThat(service.getRandomInt(), is(any(Integer.class)));
//
//       // LocationService service = ((LocationService.LocalBinder) binder).getService();
//       // assertTrue("True wasn't returned", service.doSomethingToReturnTrue());
//    }
}