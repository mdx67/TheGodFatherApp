package com.br.god.father.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Offer {

    private String catalogOfferId;
    private String catalogOfferType;
    private Integer validity;
    private List<OfferItem> offerItems;

}
