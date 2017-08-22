package com.br.god.father.model;

import java.util.List;

public class OfferItem {

    private String catalogOfferItemId;
    private Integer productId;
    private String component;
    private List<ItemAttributes> attributes;
    private Money price;

    public String getCatalogOfferItemId() {
        return catalogOfferItemId;
    }

    public void setCatalogOfferItemId(String catalogOfferItemId) {
        this.catalogOfferItemId = catalogOfferItemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public List<ItemAttributes> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ItemAttributes> attributes) {
        this.attributes = attributes;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
