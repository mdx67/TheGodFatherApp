package com.br.god.father.automation;

import com.br.god.father.model.Address;
import com.br.god.father.model.CreditCard;
import com.br.god.father.model.Customer;
import com.br.god.father.model.Document;
import com.br.god.father.model.Zns;

import java.util.Arrays;

public abstract class AbstractAutomationMock {

    public static Customer getCustomer() {
        Zns zns = new Zns("123");
        Document document = new Document();
        document.setNumber("000000000");

        Address address = new Address();
        address.setStreet("Max Colim");
        address.setNumber("470");
        address.setDistrict("America");
        address.setZipCode("89204040");

        Customer customer = new Customer();
        customer.setFullName("Fulano");
        customer.setZns(zns);
        customer.setDocuments(Arrays.asList(document));
        customer.setAddresses(Arrays.asList(address));

        return customer;
    }

    public static CreditCard getCreditCard() {
        return new CreditCard("EXTERNAL_CREDIT_CARD",
                "Filipe",
                "123455",
                "1234",
                "0128",
                "AMEX",
                "ASJASJDASDASJDLIAJSLIJDIAJDIASJPDASPDJA-FULL");
    }
}
