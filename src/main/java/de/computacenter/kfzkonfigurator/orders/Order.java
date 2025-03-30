package de.computacenter.kfzkonfigurator.orders;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private Long id;
    private CarConfiguration carConfiguration;
    private LocalDateTime orderDate;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(CarConfiguration carConfiguration) {
        this.carConfiguration = carConfiguration;
        this.orderDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public CarConfiguration getCarConfiguration() {
        return carConfiguration;
    }
    public void setCarConfiguration(CarConfiguration carConfiguration) {
        this.carConfiguration = carConfiguration;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
