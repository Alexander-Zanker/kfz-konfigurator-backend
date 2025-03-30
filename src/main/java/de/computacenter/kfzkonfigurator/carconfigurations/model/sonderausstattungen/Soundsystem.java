package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;

public class Soundsystem extends Sonderausstattung {
    public Soundsystem() {
        super("Soundsystem");
    }

    @Override
    public int getPrice() {
        return 400;
    }
}
