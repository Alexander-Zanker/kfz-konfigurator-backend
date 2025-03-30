package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;

public abstract class Sonderausstattung {
    private Long id;
    private String bezeichnung;

    public Sonderausstattung() {}

    public Sonderausstattung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public abstract int getPrice();
}
