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

        new RegisterCreditCardTest().registerCreditCad();

        Thread.sleep(2000);

        new PlanTest().registePlan();

        Thread.sleep(2000);

        new AuthorizationPaymentTest().captureSuccess();
    }
}
