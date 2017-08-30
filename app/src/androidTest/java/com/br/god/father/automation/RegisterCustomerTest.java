package com.br.god.father.automation;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.br.god.father.R;
import com.br.god.father.model.Customer;

import org.hamcrest.Matcher;
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
public class RegisterCustomerTest extends AbstractAutomationTest {

    private final String customerSuccessSave = "Status retornado:201";

    public Matcher<View> navigationIconMatcher() {
        return allOf(
                isAssignableFrom(ImageButton.class),
                withParent(isAssignableFrom(Toolbar.class)));
    }

    @Test
    public void registerCustomerTest() throws Exception {
        onView(navigationIconMatcher()).perform(click());
        onView(withText("Cad. Cliente")).perform(click());

        Customer customer = AbstractAutomationMock.getCustomer();

        onView(ViewMatchers.withId(R.id.et_name))
                .perform(typeText(customer.getFullName()), closeSoftKeyboard());
        onView(withId(R.id.et_register_customer_email))
                .perform(typeText(customer.getContacts().get(0).getContent().getEmail()), closeSoftKeyboard());
        onView(withId(R.id.et_document_number))
                .perform(typeText(customer.getDocuments().get(0).getNumber()), closeSoftKeyboard());
        onView(withId(R.id.et_address_street))
                .perform(typeText(customer.getAddresses().get(0).getStreet()), closeSoftKeyboard());
        onView(withId(R.id.et_address_number))
                .perform(typeText(customer.getAddresses().get(0).getNumber()), closeSoftKeyboard());
        onView(withId(R.id.et_address_district))
                .perform(typeText(customer.getAddresses().get(0).getDistrict()), closeSoftKeyboard());
        onView(withId(R.id.et_address_postal_code))
                .perform(typeText(customer.getAddresses().get(0).getZipCode()), closeSoftKeyboard());

        onView(withId(R.id.bt_register_customer)).perform(click());

        Thread.sleep(2000);

        onView(withText(customerSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withText(customerSuccessSave))
                .inRoot(new ToastMatcher())
                .check(matches(withText(customerSuccessSave)));
    }
}
