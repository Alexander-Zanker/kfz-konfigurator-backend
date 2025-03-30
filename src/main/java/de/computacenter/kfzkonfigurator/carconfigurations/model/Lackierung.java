package de.computacenter.kfzkonfigurator.carconfigurations.model;

public enum Lackierung {
    STANDARD(500),
    METALLIC(800);

    private final int price;

    Lackierung(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
