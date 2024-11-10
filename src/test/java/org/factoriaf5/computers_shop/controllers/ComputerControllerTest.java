package org.factoriaf5.computers_shop.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.factoriaf5.computers_shop.models.Computer;
import org.factoriaf5.computers_shop.services.ComputerService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ComputerController.class)
public class ComputerControllerTest {
    @MockBean
    ComputerService computerService;
    @Autowired
    MockMvc mockMvc;
    Computer mockComputer = Computer.builder()
            .brand("MSI")
            .memorySizeGigabytes(500)
            .price(new BigDecimal(1099.00))
            .operativeSystem("Windows 11")
            .processor("Intel Core i9")
            .build();
    @Test
    void testCreate() throws Exception{
        Computer createdMockComputer = mockComputer.withId(1L);

        when(computerService.create(mockComputer)).thenReturn(createdMockComputer);
        
        mockMvc.perform(post("/computers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mockComputer)))
            .andExpect(status().isCreated())
            .andExpect(content().json(new ObjectMapper().writeValueAsString(createdMockComputer))
        );
    }

    @Test
    void testDeleteByBrand_WhenSomethingIsDeleted_ReturnsHttpStatusNoContent() throws Exception{
        String brand = "HP";
        when(computerService.deleteByBrand(brand)).thenReturn(1);
        mockMvc.perform(delete("/computers")
                .param("brand", brand))
            .andExpect(status().isNoContent()
        );
    }

    @Test
    void testDeleteByBrand_WhenNothingIsDeleted_ReturnsHttpStatusNotFound() throws Exception{
        String brand = "HP";
        when(computerService.deleteByBrand(brand)).thenReturn(0);
        mockMvc.perform(delete("/computers")
                .param("brand", brand))
            .andExpect(status().isNotFound()
        );
    }

    @Test
    void testGetComputers() throws Exception{
        when(computerService.getAll()).thenReturn(List.of(mockComputer));
        mockMvc.perform(get("/computers"))
            .andExpect(status().isOk())
            .andExpect(content().json(new ObjectMapper().writeValueAsString(List.of(mockComputer))));
        
    }

    @Test
    void testGetComputersWithBrand() throws Exception{
        String brand = "MSI";
        when(computerService.findByBrand(brand)).thenReturn(List.of(mockComputer));
        mockMvc.perform(get("/computers?brand="+brand))
            .andExpect(status().isOk())
            .andExpect(content().json(new ObjectMapper().writeValueAsString(List.of(mockComputer))));
    }
}
