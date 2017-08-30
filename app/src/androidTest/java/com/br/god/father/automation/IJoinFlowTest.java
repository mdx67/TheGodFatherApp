package com.br.god.father.automation;

import android.support.test.rule.ActivityTestRule;

import com.br.god.father.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class IJoinFlowTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void flowTest() throws Exception {

        new RegisterCustomerTest().registerCustomer();

        Thread.sleep(2000);

        new RegisterCreditCardRequestTest().registerCreditCad();

        Thread.sleep(2000);

        new PlanTest().registePlan();

        Thread.sleep(2000);

        new AuthorizationRequestTest().captureSuccess();

//        Activity activity = mainActivityRule.getActivity();
//
//        //Customer Register
//        onView(navigationIconMatcher()).perform(click());
//        onView(withText("Cad. Cliente")).perform(click());
//
//        Customer customer = AbstractAutomationMock.getCustomer();
//
//        onView(ViewMatchers.withId(R.id.et_name))
//                .perform(typeText(customer.getFullName()), closeSoftKeyboard());
//        onView(withId(R.id.et_register_customer_email))
//                .perform(typeText(customer.getZns().getUserId()), closeSoftKeyboard());
//        onView(withId(R.id.et_document_number))
//                .perform(typeText(customer.getDocuments().get(0).getNumber()), closeSoftKeyboard());
//        onView(withId(R.id.et_address_street))
//                .perform(typeText(customer.getAddresses().get(0).getStreet()), closeSoftKeyboard());
//        onView(withId(R.id.et_address_number))
//                .perform(typeText(customer.getAddresses().get(0).getNumber()), closeSoftKeyboard());
//        onView(withId(R.id.et_address_district))
//                .perform(typeText(customer.getAddresses().get(0).getDistrict()), closeSoftKeyboard());
//        onView(withId(R.id.et_address_postal_code))
//                .perform(typeText(customer.getAddresses().get(0).getZipCode()), closeSoftKeyboard());
//
//        onView(withId(R.id.bt_register_customer)).perform(click());
//
//        onView(withText(customerSuccessSave))
//                .inRoot(new ToastMatcher())
//                .check(matches(withText(customerSuccessSave)));
//
//        Thread.sleep(5000);
//
//        //CreditCardRequest Register
//
//        onView(navigationIconMatcher()).perform(click());
//        onView(withText("Cad. Cartão")).perform(click());
//
//        CreditCardRequest creditCardRequest = AbstractAutomationMock.getCreditCard();
//
//        onView(ViewMatchers.withId(R.id.et_credit_card_holder))
//                .perform(typeText(creditCardRequest.getHolder()), closeSoftKeyboard());
//        onView(withId(R.id.et_credit_card_bin))
//                .perform(typeText(creditCardRequest.getBin()), closeSoftKeyboard());
//        onView(withId(R.id.et_credit_card_last_digits))
//                .perform(typeText(creditCardRequest.getLastDigits()), closeSoftKeyboard());
//        onView(withId(R.id.et_credit_card_expiration_date))
//                .perform(typeText(creditCardRequest.getExpirationDate()), closeSoftKeyboard());
//        onView(withId(R.id.et_credit_card_brand))
//                .perform(typeText(creditCardRequest.getBrand()), closeSoftKeyboard());
//
//        SharedPreferences settings = activity.getSharedPreferences("config_god_father_app", 0);
//        String custumerId = settings.getString("customerId", null);
//
//        onView(withId(R.id.et_credit_card_external_token))
//                .perform(typeText(custumerId), closeSoftKeyboard());
//
//        onView(withId(R.id.bt_credit_card_register)).perform(click());
//
//        onView(withText(creditCardSuccessSave))
//                .inRoot(new ToastMatcher())
//                .check(matches(withText(creditCardSuccessSave)));
//
//        Thread.sleep(5000);
//
//        //Plan register
//
//        onView(navigationIconMatcher()).perform(click());
//        onView(withText("Plano")).perform(click());
//
//        onView(ViewMatchers.withId(R.id.bt_plan_two)).perform(click());
//
//        onView(withText(planSuccessSave))
//                .inRoot(new ToastMatcher())
//                .check(matches(withText(planSuccessSave)));
//
//        Thread.sleep(5000);
//
//        //AuthorizationRequest
//
//        onView(navigationIconMatcher()).perform(click());
//        onView(withText("Autorizar/Capturar")).perform(click());
//
//        AuthorizationRequest authorizationRequest = AbstractAutomationMock.getAuthorization();
//
//        onView(ViewMatchers.withId(R.id.switch_auth_capt)).check(matches(Matchers.not(isChecked())));
//        onView(withId(R.id.et_authorize_external_id))
//                .perform(typeText(authorizationRequest.getTransaction().getExternalId()), closeSoftKeyboard());
//        onView(withId(R.id.et_authorize_price))
//                .perform(typeText(authorizationRequest.getTransaction().getPrice().getAmount().toString()), closeSoftKeyboard());
//        onView(withId(R.id.et_authorize_item_code))
//                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getCode()), closeSoftKeyboard());
//        onView(withId(R.id.et_cancel_payment_id))
//                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getName()), closeSoftKeyboard());
//        onView(withId(R.id.et_authorize_item_quantity))
//                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getQuantity().toString()), closeSoftKeyboard());
//        onView(withId(R.id.et_authorize_item_price))
//                .perform(typeText(authorizationRequest.getTransaction().getItems().get(0).getPrice().getAmount().toString()), closeSoftKeyboard());
//
//        onView(withId(R.id.bt_authorize)).perform(click());
//
//        Thread.sleep(1000);
//
//        onView(withText(authorizationSuccessSave))
//                .inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
//
//        onView(withText(authorizationSuccessSave))
//                .inRoot(new ToastMatcher())
//                .check(matches(withText(authorizationSuccessSave)));
    }


}
