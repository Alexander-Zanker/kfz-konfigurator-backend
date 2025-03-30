package de.computacenter.kfzkonfigurator.carconfigurations.model;

import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Sonderausstattung;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CarConfigurationTest {

    @Test
    void setsPriceCorrectlyAfterConstructing() {
        CarConfiguration testee = CarConfigurationTestInstances.carConfiguration1;
        assertThat(testee.getPrice()).isEqualTo(new BigDecimal("2950"));
    }

    @Test
    void updatesPriceAfterUpdatingProperties() {
        CarConfiguration testee = new CarConfiguration();
        testee.setMotorleistung(Motorleistung.LEISTUNG_200);
        testee.setLackierung(Lackierung.STANDARD);
        testee.setFelgen(Felgen.SPORT);
        testee.setSonderausstattungen(new ArrayList<Sonderausstattung>());
        assertThat(testee.getPrice()).isEqualTo(new BigDecimal("2900"));
    }
}