package de.computacenter.kfzkonfigurator.carconfigurations.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Motorleistung {
    LEISTUNG_100(100, 1000),
    LEISTUNG_150(150, 1500),
    LEISTUNG_200(200, 2000);

    private final int ps;
    private final int price;

    Motorleistung(int ps, int price) {
        this.ps = ps;
        this.price = price;
    }

    @JsonValue
    public int getPs() {
        return ps;
    }

    public int getPrice() {
        return price;
    }
}
