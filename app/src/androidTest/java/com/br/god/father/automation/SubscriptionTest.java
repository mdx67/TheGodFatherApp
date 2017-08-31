package com.br.god.father.automation;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.br.god.father.R;
import com.br.god.father.automation.utils.ToastMatcher;
import com.br.god.father.ui.activity.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SubscriptionTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    private final String planSuccessSave = "Status retornado:202";

    public static Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    public void registePlan() throws InterruptedException {
        onView(navigationIconMatcher()).perform(click());
        onView(withText("Plano")).perform(click());

        onView(ViewMatchers.withId(R.id.bt_plan_two)).perform(click());

        Thread.sleep(3000);

        onView(withText(planSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(planSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(withText(planSuccessSave)));
    }
}
