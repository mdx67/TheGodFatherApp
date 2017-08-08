package com.br.god.father.model;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
public class TransactionItem {

    private String code;
    private String name;
    private Integer quantity;
    private Money price;
    private Map customFields;

}
