package com.br.god.father.automation;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;

import com.br.god.father.model.Address;
import com.br.god.father.model.AuthorizationRequest;
import com.br.god.father.model.Contact;
import com.br.god.father.model.ContactContent;
import com.br.god.father.model.ContactType;
import com.br.god.father.model.CreditCardRequest;
import com.br.god.father.model.Customer;
import com.br.god.father.model.Document;
import com.br.god.father.model.Money;
import com.br.god.father.model.Transaction;
import com.br.god.father.model.TransactionItem;
import com.br.god.father.ui.activity.MainActivity;
import com.br.god.father.utils.CPFGenerator;

import org.junit.Rule;

import java.util.Arrays;
import java.util.Date;

public abstract class AbstractAutomationMock {

    @Rule
    public static ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    public static Customer getCustomer() throws Exception {
        Customer customer = new Customer();

        customer.setFullName("Fulano Beltrano");
        customer.setPersonType("F");
        customer.setBirthDate("1980-01-20");
        customer.setCountry("Brasil");
        customer.setMotherName("Julia Maria");
        customer.setGender("M");

        Document document = new Document();
        document.setNumber(CPFGenerator.geraCPF());
        document.setDocType("CPF");

        customer.setDocuments(Arrays.asList(document));

        Contact contact = new Contact(ContactType.EMAIL, new Date(), new ContactContent("MAIN", "meu@email2.com.ru"));

        customer.setContacts(Arrays.asList(contact));

        Address address = new Address();
        address.setName("Principal");
        address.setStreet("Rua um");
        address.setNumber("67");
        address.setDistrict("Centro");
        address.setCity("Joinville");
        address.setState("Santa Catarina");
        address.setZipCode("89220105");
        address.setCountry("Brasil");

        customer.setAddresses(Arrays.asList(address));


        return customer;
    }

    public static CreditCardRequest getCreditCard() {
        Activity activity = mainActivityRule.getActivity();
        SharedPreferences settings = activity.getSharedPreferences("config_god_father_app", 0);
        String paymentMethodId = settings.getString("paymentMethodId", null);


        return new CreditCardRequest("EXTERNAL_CREDIT_CARD",
                "Filipe",
                "123455",
                "1234",
                "0128",
                "AMEX",
                paymentMethodId);
    }

    public static AuthorizationRequest getAuthorization() {
        TransactionItem item = new TransactionItem();
        item.setCode("CODE-PRODUCT-001");
        item.setName("100 min outras operadoras");
        item.setPrice(new Money("BRL", 199, 2));
        item.setQuantity(1);

        Transaction transaction = new Transaction();
        transaction.setPrice(new Money("BRL", 199, 2));
        transaction.setExternalId("YOUR_TRANSACTION_ID");
        transaction.setItems(Arrays.asList(item));

        AuthorizationRequest authorizationRequest = new AuthorizationRequest();
        authorizationRequest.setTransaction(transaction);

        return authorizationRequest;
    }
}
