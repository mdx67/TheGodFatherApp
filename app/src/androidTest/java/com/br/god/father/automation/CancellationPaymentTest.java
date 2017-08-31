package com.br.god.father.automation;

import android.support.test.rule.ActivityTestRule;

import com.br.god.father.R;
import com.br.god.father.automation.utils.ToastMatcher;
import com.br.god.father.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CancellationPaymentTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    private final String successStatus = "Status retornado:202";

    @Test
    public void cancellationSuccess() throws InterruptedException {
        onView(withId(R.id.bt_authorization_cancel_refund)).perform(click());

        Thread.sleep(500);

        onView(withId(R.id.bt_cancel)).perform(click());

        Thread.sleep(3000);

        onView(withText(successStatus))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(successStatus))
                .inRoot(new ToastMatcher())
                .check(matches(withText(successStatus)));
    }
}

