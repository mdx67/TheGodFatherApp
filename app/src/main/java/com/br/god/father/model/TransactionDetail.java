package com.br.god.father.model;

import lombok.Data;

@Data
public class TransactionDetail {

    private Money discount;
    private Money tax;
    private Money handlingFee;
    private Money shippingFee;
    private Money shippingDiscount;
    private Money insurance;

}
