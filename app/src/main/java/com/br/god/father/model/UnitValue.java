package com.br.god.father.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnitValue {
    private Integer value;
    private Integer scale;
    private String unit;

    public UnitValue(Integer value, Integer scale, String unit) {
        this.value = value;
        this.scale = scale;
        this.unit = unit;
    }
}
