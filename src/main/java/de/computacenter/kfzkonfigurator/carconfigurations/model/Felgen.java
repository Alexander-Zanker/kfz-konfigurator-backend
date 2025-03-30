package de.computacenter.kfzkonfigurator.carconfigurations.model;

public enum Felgen {
    STANDARD(200),
    SPORT(400);

    private final int price;

    Felgen(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
