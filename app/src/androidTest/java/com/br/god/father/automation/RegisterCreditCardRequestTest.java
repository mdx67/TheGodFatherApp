package com.br.god.father.automation;

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
import com.br.god.father.model.CreditCardRequest;
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
public class RegisterCreditCardRequestTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    private final String creditCardSuccessSave = "Status retornado:202";

    public static Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    public void registerCreditCad() throws InterruptedException {
        CreditCardRequest creditCardRequest = AbstractAutomationMock.getCreditCard();

        onView(navigationIconMatcher()).perform(click());
        onView(withText("Cad. Cartão")).perform(click());

        onView(ViewMatchers.withId(R.id.et_credit_card_holder))
                .perform(typeText(creditCardRequest.getHolder()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_bin))
                .perform(typeText(creditCardRequest.getBin()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_last_digits))
                .perform(typeText(creditCardRequest.getLastDigits()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_expiration_date))
                .perform(typeText(creditCardRequest.getExpirationDate()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_brand))
                .perform(typeText(creditCardRequest.getBrand()), closeSoftKeyboard());

        onView(withId(R.id.et_credit_card_external_token))
                .perform(typeText(creditCardRequest.getExternalToken()), closeSoftKeyboard());

        onView(withId(R.id.bt_credit_card_register)).perform(click());

        Thread.sleep(2000);

        onView(withText(creditCardSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(creditCardSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(withText(creditCardSuccessSave)));
    }
}
