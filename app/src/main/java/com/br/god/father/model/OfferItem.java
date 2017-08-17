package com.br.god.father.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OfferItem {

    private String catalogOfferItemId;
    private Integer productId;
    private String component;
    @JsonIgnore
    private List<ItemAttributes> attributes;
    @JsonIgnore
    private Money price;

}
