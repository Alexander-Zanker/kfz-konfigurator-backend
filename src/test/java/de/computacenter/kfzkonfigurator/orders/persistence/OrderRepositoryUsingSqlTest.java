package de.computacenter.kfzkonfigurator.orders.persistence;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfigurationTestInstances;
import de.computacenter.kfzkonfigurator.carconfigurations.persistence.CarConfigurationRepository;
import de.computacenter.kfzkonfigurator.orders.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class OrderRepositoryUsingSqlTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CarConfigurationRepository carConfigurationRepository;

    @Test
    public void testSaveAndFindOrder() {
        // First, create and save a CarConfiguration
        CarConfiguration config = CarConfigurationTestInstances.carConfiguration1;
        CarConfiguration savedConfig = carConfigurationRepository.save(config);

        // Then, create and save an Order referencing the configuration
        Order order = new Order();
        order.setCarConfiguration(savedConfig);
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        assertThat(savedOrder.getId()).isNotNull();

        // Retrieve the order and verify
        Order retrievedOrder = orderRepository.findById(savedOrder.getId());
        assertThat(retrievedOrder).isNotNull();
        assertThat(retrievedOrder.getCarConfiguration().getId()).isEqualTo(savedConfig.getId());
        assertThat(retrievedOrder.getCarConfiguration().getPrice().compareTo(savedConfig.getPrice())).isEqualTo(0);
    }
}
