package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;

public abstract class Sonderausstattung {
    private String bezeichnung;

    public Sonderausstattung() {}

    public Sonderausstattung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public abstract int getPrice();
}
