package com.br.god.father.automation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.br.god.father.R;
import com.br.god.father.model.CreditCard;
import com.br.god.father.ui.activity.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
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
public class RegisterCreditCardTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    private final String creditCardSuccessSave = "Cartão cadastrado com sucesso!";

    @Before
    public void setUp() {

    }

    public static Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    public void registerCreditCad() throws InterruptedException {

        Activity activity = mainActivityRule.getActivity();
        CreditCard creditCard = AbstractAutomationMock.getCreditCard();

        onView(navigationIconMatcher()).perform(click());
        onView(withText("Cad. Cartão")).perform(click());

        onView(ViewMatchers.withId(R.id.et_credit_card_holder))
                .perform(typeText(creditCard.getCardHolderName()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_bin))
                .perform(typeText(creditCard.getBin()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_last_digits))
                .perform(typeText(creditCard.getLastDigits()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_expiration_date))
                .perform(typeText(creditCard.getExpirationDate()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_brand))
                .perform(typeText(creditCard.getBrand()), closeSoftKeyboard());

        SharedPreferences settings = activity.getSharedPreferences("config_god_father_app", 0);
        String custumerId = settings.getString("customerId", null);

        onView(withId(R.id.et_custumer_id))
                .perform(typeText(custumerId), closeSoftKeyboard());

        onView(withId(R.id.bt_credit_card_register)).perform(click());

        onView(withText(creditCardSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(creditCardSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(withText(creditCardSuccessSave)));
    }
}
