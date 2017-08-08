package com.br.god.father.model;

import java.util.List;

import lombok.Data;

@Data
public class OfferItem {

    private String catalogOfferItemId;
    private Integer productId;
    private String component;
    private List<ItemAttributes> attributes;
    private Money price;
}
