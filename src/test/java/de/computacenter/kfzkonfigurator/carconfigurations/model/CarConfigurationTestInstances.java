package de.computacenter.kfzkonfigurator.carconfigurations.model;

import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Fahrsicherheitssystem;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Klimaanlage;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Sonderausstattung;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Soundsystem;

import java.util.ArrayList;
import java.util.List;

public class CarConfigurationTestInstances {
    public static CarConfiguration carConfiguration1 = new CarConfiguration(
            Motorleistung.LEISTUNG_100,
            Lackierung.METALLIC,
            Felgen.SPORT,
            new ArrayList<Sonderausstattung>(List.of(new Klimaanlage(), new Soundsystem()))
    );

    public static CarConfiguration carConfiguration2 = new CarConfiguration(
            Motorleistung.LEISTUNG_150,
            Lackierung.METALLIC,
            Felgen.SPORT,
            List.of(new Klimaanlage(), new Soundsystem(), new Fahrsicherheitssystem())
    );
}
