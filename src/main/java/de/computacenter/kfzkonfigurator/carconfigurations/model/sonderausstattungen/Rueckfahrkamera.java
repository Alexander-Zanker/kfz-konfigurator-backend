package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;


public class Rueckfahrkamera extends Sonderausstattung {
    public Rueckfahrkamera() {
        super("Rückfahrkamera");
    }

    @Override
    public int getPrice() {
        return 600;
    }
}
