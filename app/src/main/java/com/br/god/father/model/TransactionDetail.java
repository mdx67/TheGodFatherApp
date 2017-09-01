package com.br.god.father.model;

import java.util.List;

public class TransactionDetail {

    private Money discount;
    private Money tax;
    private Money handlingFee;
    private Money shippingFee;
    private Money shippingDiscount;
    private Money insurance;
    private List<Discount> discounts;

    public Money getDiscount() {
        return discount;
    }

    public void setDiscount(Money discount) {
        this.discount = discount;
    }

    public Money getTax() {
        return tax;
    }

    public void setTax(Money tax) {
        this.tax = tax;
    }

    public Money getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(Money handlingFee) {
        this.handlingFee = handlingFee;
    }

    public Money getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Money shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Money getShippingDiscount() {
        return shippingDiscount;
    }

    public void setShippingDiscount(Money shippingDiscount) {
        this.shippingDiscount = shippingDiscount;
    }

    public Money getInsurance() {
        return insurance;
    }

    public void setInsurance(Money insurance) {
        this.insurance = insurance;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
