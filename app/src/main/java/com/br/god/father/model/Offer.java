package com.br.god.father.model;

import java.util.List;

public class Offer {

    private String catalogOfferId;
    private String catalogOfferType;
    private Validity validity;
    private List<OfferItem> offerItems;
    private Money price;

    public String getCatalogOfferId() {
        return catalogOfferId;
    }

    public void setCatalogOfferId(String catalogOfferId) {
        this.catalogOfferId = catalogOfferId;
    }

    public String getCatalogOfferType() {
        return catalogOfferType;
    }

    public void setCatalogOfferType(String catalogOfferType) {
        this.catalogOfferType = catalogOfferType;
    }

    public Validity getValidity() {
        return validity;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }

    public List<OfferItem> getOfferItems() {
        return offerItems;
    }

    public void setOfferItems(List<OfferItem> offerItems) {
        this.offerItems = offerItems;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
