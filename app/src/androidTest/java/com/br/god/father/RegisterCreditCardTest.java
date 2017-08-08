package com.br.god.father;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

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

    private CreditCard creditCard;
    private final String CreditCardSuccessSave = "Cartão cadastrado com sucesso!";

    @Before
    public void setUp() {
        creditCard = new CreditCard("5555444455554444", "04/2020", "123");
    }

    public static Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    public void registeCreditCad() throws InterruptedException {
        onView(navigationIconMatcher()).perform(click());
        onView(withText("Cad. Cartão")).perform(click());

        onView(withId(R.id.et_credit_card_number))
                .perform(typeText(creditCard.getNumber()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_validation))
                .perform(typeText(creditCard.getValidationDate()), closeSoftKeyboard());
        onView(withId(R.id.et_credit_card_cvv))
                .perform(typeText(creditCard.getCvv()), closeSoftKeyboard());

        onView(withId(R.id.btCrediCardRegister)).perform(click());

        onView(withText(CreditCardSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(CreditCardSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(withText(CreditCardSuccessSave)));
    }
}
