package com.br.god.father.automation;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.br.god.father.R;
import com.br.god.father.automation.mock.AbstractAutomationMock;
import com.br.god.father.automation.utils.ToastMatcher;
import com.br.god.father.model.AuthorizationRequest;
import com.br.god.father.ui.activity.MainActivity;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AuthorizationPaymentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    private final String successStatus = "Status retornado:202";

    public static Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    @Ignore
    public void myTest() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());

        onView(withText(R.string.action_customer_settings)).perform(click());

//        onView(withText(R.string.tittle_authorize_capture)).perform(click());
    }

    @Test
    public void authorizationSuccess() throws InterruptedException {
        onView(navigationIconMatcher()).perform(click());
        onView(withText(R.string.tittle_authorize_capture)).perform(click());

        AuthorizationRequest authorizationRequest = AbstractAutomationMock.getAuthorization();

        onView(ViewMatchers.withId(R.id.switch_auth_capt)).check(matches(Matchers.not(isChecked())));
        onView(withId(R.id.et_authorize_external_id))
                .perform(typeText(authorizationRequest.getTransaction().getExternalId()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_price))
                .perform(typeText(authorizationRequest.getTransaction().getPrice().getAmount().toString()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_code))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getCode()), closeSoftKeyboard());
        onView(withId(R.id.et_cancel_payment_id))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getName()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_quantity))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getQuantity().toString()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_price))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getPrice().getAmount().toString()), closeSoftKeyboard());

        onView(withId(R.id.bt_authorize)).perform(click());

        Thread.sleep(5000);

        onView(withText(successStatus))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(successStatus))
                .inRoot(new ToastMatcher())
                .check(matches(withText(successStatus)));
    }

    @Test
    public void authorizationAndCancelSuccess() throws InterruptedException {
        authorizationSuccess();

        new CancellationPaymentTest().cancellationSuccess();
    }

    @Test
    public void captureSuccess() throws InterruptedException {
        onView(navigationIconMatcher()).perform(click());
        onView(withText(R.string.tittle_authorize_capture)).perform(click());
        onView(ViewMatchers.withId(R.id.switch_auth_capt)).perform(click());

        AuthorizationRequest authorizationRequest = AbstractAutomationMock.getAuthorization();

        onView(ViewMatchers.withId(R.id.switch_auth_capt)).check(matches(isChecked()));
        onView(withId(R.id.et_authorize_external_id))
                .perform(typeText(authorizationRequest.getTransaction().getExternalId()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_price))
                .perform(typeText(authorizationRequest.getTransaction().getPrice().getAmount().toString()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_code))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getCode()), closeSoftKeyboard());
        onView(withId(R.id.et_cancel_payment_id))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getName()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_quantity))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getQuantity().toString()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_price))
                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getPrice().getAmount().toString()), closeSoftKeyboard());

        onView(withId(R.id.bt_authorize)).perform(click());

        Thread.sleep(5000);

        onView(withText(successStatus))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(successStatus))
                .inRoot(new ToastMatcher())
                .check(matches(withText(successStatus)));
    }

//    Not used now!
//    private void selectCustomerForConfirmation() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
//
//        onView(withText(R.string.action_customer_settings)).perform(click());
//
//        onData(HasToString.hasToString("Customer 8323"))
//                .inAdapterView(withId(R.id.layout_list_customer))
//                .perform(click());
//
//        onView(withText("OK")).inRoot(RootMatchers.isDialog())
//                .check(matches(isDisplayed()))
//                .perform(click());
//    }

//    Not used now!
//    private void selectCustomerForAuthorization() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
//
//        onView(withText(R.string.action_customer_settings)).perform(click());
//
//        onData(HasToString.hasToString("Customer 4323"))
//                .inAdapterView(withId(R.id.layout_list_customer))
//                .perform(click());
//
//        onView(withText("OK")).inRoot(RootMatchers.isDialog())
//                .check(matches(isDisplayed()))
//                .perform(click());
//    }

}
