package de.computacenter.kfzkonfigurator.orders;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.carconfigurations.persistence.CarConfigurationRepository;
import de.computacenter.kfzkonfigurator.orders.persistence.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private CarConfigurationRepository carConfigurationRepository;
    private OrderRepository orderRepository;

    public OrderService( CarConfigurationRepository carConfigurationRepository, OrderRepository orderRepository) {
        this.carConfigurationRepository = carConfigurationRepository;
        this.orderRepository = orderRepository;
    }

    protected Order createOrder(CarConfiguration carConfiguration) {
        if (carConfiguration != null) {
            CarConfiguration persistedConfig = carConfigurationRepository.save(carConfiguration);
            Order order = new Order(persistedConfig);
            Order persistedOrder = orderRepository.save(order);
            return persistedOrder;
        }
        return null;
    }
}
