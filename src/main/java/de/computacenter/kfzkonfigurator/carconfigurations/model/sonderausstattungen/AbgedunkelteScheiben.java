package de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen;

public class AbgedunkelteScheiben extends Sonderausstattung {
    public AbgedunkelteScheiben() {
        super("AbgedunkelteScheiben");
    }

    @Override
    public int getPrice() {
        return 300;
    }
}
