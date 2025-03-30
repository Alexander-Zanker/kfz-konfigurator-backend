package de.computacenter.kfzkonfigurator.carconfigurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.computacenter.kfzkonfigurator.carconfigurations.model.*;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Fahrsicherheitssystem;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Klimaanlage;
import de.computacenter.kfzkonfigurator.carconfigurations.model.sonderausstattungen.Soundsystem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CarConfigurationController.class)
class CarConfigurationControllerTest {
    @MockitoBean
    private CarConfigurationService carConfigurationService;

    @Autowired
    private MockMvc mockMvc;

    String jsonPayload = """
    {
      "motorleistung": 150,
      "lackierung": "METALLIC",
      "felgen": "SPORT",
      "sonderausstattungen": ["Klimaanlage", "Soundsystem", "Fahrsicherheitssystem"]
    }
    """;
    String response = """
    {
      "id": 32,
      "motorleistung": 150,
      "lackierung": "METALLIC",
      "felgen": "SPORT",
      "sonderausstattungen": ["Klimaanlage", "Soundsystem", "Fahrsicherheitssystem"],
      "price": 4350
    }
    """;
    CarConfiguration exampleConfig = CarConfigurationTestInstances.carConfiguration2;

    @Test
    void getsPriceOfConfigObject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/car-configurations/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload)
        )
                .andExpect(content().string("4350"));
    }

    @Test
    void getConfigById() throws Exception {
        exampleConfig.setId(32L);
        when(carConfigurationService.getById(32L)).thenReturn(exampleConfig);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/car-configurations/32"))
                .andExpect(content().json(response));
    }
}