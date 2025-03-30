package de.computacenter.kfzkonfigurator.orders;

import de.computacenter.kfzkonfigurator.carconfigurations.CarConfigurationService;
import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfiguration;
import de.computacenter.kfzkonfigurator.carconfigurations.model.CarConfigurationTestInstances;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false) // disables websecurity filters for tests to run
class OrderControllerTest {
    @MockitoBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    String jsonRequest = """
    {
      "motorleistung": 150,
      "lackierung": "METALLIC",
      "felgen": "SPORT",
      "sonderausstattungen": ["Klimaanlage", "Soundsystem", "Fahrsicherheitssystem"]
    }
    """;

    String response = """
    {
      "order": {
        "id": 5,
        "carConfiguration": {
          "id": 32,
          "motorleistung": 150,
          "lackierung": "METALLIC",
          "felgen": "SPORT",
          "sonderausstattungen": ["Klimaanlage", "Soundsystem", "Fahrsicherheitssystem"],
          "price": 4350
        },
        "orderDate": "2025-03-25T12:00:00"
      },
      "urlPath": "/api/v1/car-configurations/32"
    }
    """;

    @Test
    void getsPriceOfConfigObject() throws Exception {
        CarConfiguration config = CarConfigurationTestInstances.carConfiguration2;
        config.setId(32L);
        Order order = new Order(config);
        order.setId(5L);
        order.setOrderDate(LocalDateTime.of(2025, Month.MARCH, 25, 12, 0, 0));
        Mockito.when(orderService.createOrder(Mockito.any(CarConfiguration.class))).thenReturn(order);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )
                .andExpect(content().json(response));
    }

}