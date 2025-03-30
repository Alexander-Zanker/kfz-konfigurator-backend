package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;

public class Klimaanlage extends Sonderausstattung {
    public Klimaanlage() {
        super("Klimaanlage");
    }

    @Override
    public int getPrice() {
        return 350;
    }
}
