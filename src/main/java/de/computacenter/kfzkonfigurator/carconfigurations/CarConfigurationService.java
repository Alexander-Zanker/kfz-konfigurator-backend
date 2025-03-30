package de.computacenter.kfzkonfigurator.carconfigurations;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.carconfigurations.persistence.CarConfigurationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarConfigurationService {
    private final CarConfigurationRepository carConfigurationRepository;

    public CarConfigurationService(CarConfigurationRepository carConfigurationRepository) {
        this.carConfigurationRepository = carConfigurationRepository;
    }

    public CarConfiguration getById(Long id) {
        return this.carConfigurationRepository.findById(id);
    }
}
