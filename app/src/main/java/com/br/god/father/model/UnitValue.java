package com.br.god.father.model;

public class UnitValue {
    private Integer value;
    private Integer scale;
    private String unit;

    public UnitValue() {
    }

    public UnitValue(Integer value, Integer scale, String unit) {
        this.value = value;
        this.scale = scale;
        this.unit = unit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
