package de.computacenter.kfzkonfigurator.carconfigurations.persistence;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;

public interface CarConfigurationRepository {
    CarConfiguration save(CarConfiguration config);
    CarConfiguration findById(Long id);
}
