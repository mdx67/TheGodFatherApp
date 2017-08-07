package com.br.god.father;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.br.god.father.model.CreditCard;
import com.br.god.father.model.Customer;
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
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterCustomerTest {

    private DrawerLayout drawer;
    private Customer customer;

    @Before
    public void setUp(){
        CreditCard creditCard = new CreditCard("4916218097964731", "07/08/2019", "850");

        customer = new Customer();
        customer.setName("Dorothy Walters");
        customer.setEmail("dorothy-85@example.com");
        customer.setPhone("(807) 288 2969");
        customer.setAddress("United States");
        customer.setCreditCard(creditCard);

    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    public static Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    public void changeTextFields() throws InterruptedException {

        onView(navigationIconMatcher()).perform(click());
        onView(withText("Cad. Cliente")).perform(click());

        onView(withId(R.id.etName))
                .perform(typeText(customer.getName()), closeSoftKeyboard());
        onView(withId(R.id.etPhone))
                .perform(typeText(customer.getPhone()), closeSoftKeyboard());
        onView(withId(R.id.etEmail))
                .perform(typeText(customer.getEmail()), closeSoftKeyboard());
        onView(withId(R.id.etAddress))
                .perform(typeText(customer.getAddress()), closeSoftKeyboard());

        onView(withId(R.id.btNext)).perform(click());

        onView(withId(R.id.etCreditCardNumber))
                .perform(typeText(customer.getCreditCard().getNumber()), closeSoftKeyboard());
        onView(withId(R.id.CreditCardValidate))
                .perform(typeText(customer.getCreditCard().getValidationDate()), closeSoftKeyboard());
        onView(withId(R.id.etCreditCardCVV))
                .perform(typeText(customer.getCreditCard().getCvv()), closeSoftKeyboard());

        onView(withId(R.id.btCrediCardRegister)).perform(click());

    }

}
