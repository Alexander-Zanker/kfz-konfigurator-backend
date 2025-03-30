package de.computacenter.kfzkonfigurator.carconfigurations;

import de.computacenter.kfzkonfigurator.carconfigurations.model.*;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Klimaanlage;
import de.computacenter.kfzkonfigurator.carconfigurations.persistence.CarConfigurationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CarConfigurationServiceTest {
    private CarConfigurationRepository repository = Mockito.mock(CarConfigurationRepository.class);
    private CarConfigurationService testee = new CarConfigurationService(repository);

    private Long id = 1L;
    private CarConfiguration exampleConfig = CarConfigurationTestInstances.carConfiguration1;

    @Test
    void getsConfigurationById() {
        exampleConfig.setId(id);
        when(repository.findById(id)).thenReturn(exampleConfig);

        CarConfiguration actual = testee.getById(id);
        assertThat(actual).isEqualTo(exampleConfig);
    }
}