package com.br.god.father.model;

import java.util.Set;

import lombok.Data;

@Data
public class Offer {

    private String catalogOfferId;
    private String catalogOfferType;
    private Integer validity;
    private Set<OfferItem> offerItems;


}
