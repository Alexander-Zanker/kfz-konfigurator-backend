package de.computacenter.kfzkonfigurator.orders;

import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.carconfigurations.persistence.CarConfigurationRepository;
import de.computacenter.kfzkonfigurator.orders.persistence.OrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody CarConfiguration carConfiguration) {
        Order persistedOrder = orderService.createOrder(carConfiguration);
        String composedPath = "/api/v1/car-configurations/" + persistedOrder.getCarConfiguration().getId();
        OrderResponse orderResponse = new OrderResponse(persistedOrder, composedPath);
        return orderResponse;
    }
}
