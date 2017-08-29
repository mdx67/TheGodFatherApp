package com.br.god.father.mock;

import com.br.god.father.model.ItemAttributes;
import com.br.god.father.model.Money;
import com.br.god.father.model.Offer;
import com.br.god.father.model.OfferItem;
import com.br.god.father.model.Payment;
import com.br.god.father.model.Recurring;
import com.br.god.father.model.SubscriptionRequest;
import com.br.god.father.model.UnitValue;
import com.br.god.father.model.Validity;

import java.util.Arrays;

public class SubscriptionMock {

    public static SubscriptionRequest buildSubscription() {
        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();

        subscriptionRequest.setExternalId("PLAN-02_99209918");
        subscriptionRequest.setOriginApp("MGM");
        subscriptionRequest.setDescription("Plano 2 - COMBO:  internet + dados moveis");
        subscriptionRequest.setType("STANDARD");
//        subscriptionRequest.setCallback("http://127.0.0.1:8834/notifications");

        subscriptionRequest.setRecurring(new Recurring("2017-10-01", 1, "MONTH", 6));

        subscriptionRequest.setPayment(new Payment("CREDIT_CARD", "CRC-21be8fa4-a29b-410c-9f63-1d49cab63027"));

        Offer offer = new Offer();
        offer.setCatalogOfferId("41530168-12dd-4510-be2b-7add0e7d14e9");
        offer.setCatalogOfferType("PLAN");
        offer.setValidity(new Validity("DAY", 30, false));
        offer.setPrice(new Money("BRL", 3990, 2));

        OfferItem item = new OfferItem();
        item.setProductId(1);
        item.setCatalogOfferItemId("1f0ef0f7-3a46-4b9f-8143-6490a046ff5b");
        item.setProductTypeName("MOBILE_DATA_OFFER");
        item.setProductTypeId("d497bdc4-57e1-4652-86d2-1f8e32435aba");
        item.setComponent("INTERNET_MOBILE");
        item.setPrice(new Money("BRL", 3990, 2));

        ItemAttributes attributes = new ItemAttributes();
        attributes.setName("volume");
        attributes.setValue("4999");
        attributes.setUnitValue(new UnitValue(3, 0, "GB"));

        item.setAttributes(Arrays.asList(attributes));

        offer.setOfferItems(Arrays.asList(item));

        subscriptionRequest.setOffer(offer);

        return subscriptionRequest;
    }
}
