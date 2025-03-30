package de.computacenter.kfzkonfigurator.carconfigurations.persistence;

import de.computacenter.kfzkonfigurator.carconfigurations.model.*;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Klimaanlage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CarConfigurationRepositoryUsingSqlTest {

    @Autowired
    private CarConfigurationRepository carConfigurationRepository;

    @Test
    public void testSaveAndFindCarConfiguration() {
        CarConfiguration config = CarConfigurationTestInstances.carConfiguration1;

        // Save configuration
        CarConfiguration saved = carConfigurationRepository.save(config);
        assertThat(saved.getId()).isNotNull();

        // Retrieve configuration by ID
        CarConfiguration retrieved = carConfigurationRepository.findById(saved.getId());
        assertThat(retrieved).isNotNull();
        assertThat(retrieved.getMotorleistung()).isEqualTo(Motorleistung.LEISTUNG_100);
        assertThat(retrieved.getLackierung()).isEqualTo(Lackierung.METALLIC);
        assertThat(retrieved.getFelgen()).isEqualTo(Felgen.SPORT);
        assertThat(retrieved.getPrice()).isEqualByComparingTo(config.getPrice());
        assertThat(retrieved.getSonderausstattungen()).isNotEmpty();
        assertThat(retrieved.getSonderausstattungen().get(0).getBezeichnung()).isEqualTo("Klimaanlage");
    }
}
