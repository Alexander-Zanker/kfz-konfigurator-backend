package de.computacenter.kfzkonfigurator.carconfigurations;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/car-configurations")
public class CarConfigurationController {
    private CarConfigurationService carConfigurationService;

    public CarConfigurationController(CarConfigurationService carConfigurationService) {
        this.carConfigurationService = carConfigurationService;
    }

    @PostMapping("/price")
    public BigDecimal calculatePrice(@RequestBody CarConfiguration config) {
        return config.getPrice();
    }

    @GetMapping("/{id}")
    public CarConfiguration getConfigurationById(@PathVariable Long id) {
        return carConfigurationService.getById(id);
    }
}
