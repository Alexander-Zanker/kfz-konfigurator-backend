package de.computacenter.kfzkonfigurator.orders.persistence;

import de.computacenter.kfzkonfigurator.orders.Order;

public interface OrderRepository {
    Order save(Order order);
    Order findById(Long id);
}
