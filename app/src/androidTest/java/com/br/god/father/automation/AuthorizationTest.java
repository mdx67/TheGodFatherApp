package com.br.god.father.automation;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.br.god.father.R;
import com.br.god.father.model.Authorization;
import com.br.god.father.ui.activity.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AuthorizationTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    private final String authorizationSuccessSave = "Autorizado com sucesso!";

    public static Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    public void registerAuthorization() throws InterruptedException {
        onView(navigationIconMatcher()).perform(click());
        onView(withText("Autorização")).perform(click());

        Authorization authorization = AbstractAutomationMock.getAuthorization();

        onView(ViewMatchers.withId(R.id.et_authorize_intent))
                .perform(typeText(authorization.getIntent()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_external_id))
                .perform(typeText(authorization.getTransaction().getExternalId()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_price))
                .perform(typeText(authorization.getTransaction().getPrice().getAmount().toString()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_code))
                .perform(typeText(authorization.getTransaction().getItems().get(0).getCode()), closeSoftKeyboard());
        onView(withId(R.id.et_cancel_payment_id))
                .perform(typeText(authorization.getTransaction().getItems().get(0).getName()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_quantity))
                .perform(typeText(authorization.getTransaction().getItems().get(0).getQuantity().toString()), closeSoftKeyboard());
        onView(withId(R.id.et_authorize_item_price))
                .perform(typeText(authorization.getTransaction().getItems().get(0).getPrice().getAmount().toString()), closeSoftKeyboard());

        onView(withId(R.id.bt_authorize)).perform(click());

        onView(withText(authorizationSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(authorizationSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(withText(authorizationSuccessSave)));
    }
}
