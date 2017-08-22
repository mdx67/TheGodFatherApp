package com.br.god.father.model;

import java.util.List;

public class Offer {

    private String catalogOfferId;
    private String catalogOfferType;
    private Integer validity;
    private List<OfferItem> offerItems;

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

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public List<OfferItem> getOfferItems() {
        return offerItems;
    }

    public void setOfferItems(List<OfferItem> offerItems) {
        this.offerItems = offerItems;
    }
}
