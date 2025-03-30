package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;


public class Fahrsicherheitssystem extends Sonderausstattung {
    public Fahrsicherheitssystem() {
        super("Fahrsicherheitssystem");
    }

    @Override
    public int getPrice() {
        return 900;
    }
}
