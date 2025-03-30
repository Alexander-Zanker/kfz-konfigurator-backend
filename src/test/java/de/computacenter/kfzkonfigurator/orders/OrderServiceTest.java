package de.computacenter.kfzkonfigurator.orders;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfigurationTestInstances;
import de.computacenter.kfzkonfigurator.carconfigurations.persistence.CarConfigurationRepository;
import de.computacenter.kfzkonfigurator.orders.persistence.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private CarConfigurationRepository carConfigurationRepository = Mockito.mock(CarConfigurationRepository.class);
    private OrderService testee = new OrderService(
            carConfigurationRepository, orderRepository
    );
    private CarConfiguration config = CarConfigurationTestInstances.carConfiguration1;

    @Test
    void persistsConfigAndOrderUponCreation() {
        CarConfiguration configWithId = CarConfigurationTestInstances.carConfiguration1;
        configWithId.setId(2L);
        Order orderWithId = new Order(configWithId);
        orderWithId.setId(12L);

        Mockito.when(carConfigurationRepository.save(config)).thenReturn(configWithId);
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(orderWithId);

        Order actual = testee.createOrder(config);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("orderDate")
                .isEqualTo(orderWithId);
    }
}