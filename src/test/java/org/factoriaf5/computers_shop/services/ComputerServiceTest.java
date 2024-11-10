package org.factoriaf5.computers_shop.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.factoriaf5.computers_shop.models.Computer;
import org.factoriaf5.computers_shop.repositories.ComputerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComputerServiceTest {
    @Mock
    ComputerRepository computerRepository;
    @InjectMocks
    ComputerService computerService;
    Computer mockComputer = Computer.builder()
            .brand("MSI")
            .memorySizeGigabytes(500)
            .price(new BigDecimal(1099.00))
            .operativeSystem("Windows 11")
            .processor("Intel Core i9")
            .build();
    @Test
    void testCreate() {
        when(computerRepository.save(mockComputer)).thenReturn(mockComputer.withId(1L));
        assertTrue(computerService.create(mockComputer).withId(1L).equals(mockComputer.withId(1L)));
    }

    @Test
    void testDeleteByBrand_WhenBrandIsNull_ThrowsIllegalArgumentException() {
        String brand = null;
        assertThrows(IllegalArgumentException.class,() -> computerService.deleteByBrand(brand));
    }

    @Test
    void testDeleteByBrand_WhenThereAreNoCoincidences_Returns0() {
        String brand = mockComputer.getBrand();
        when(computerRepository.findByBrand(brand)).thenReturn(new ArrayList<>());
        assertTrue(0==computerService.deleteByBrand(brand));
    }

    @Test
    void testDeleteByBrand_WhenThereAreCoincidences_DoesDeletionAndReturnsNumberOfDeletedItems() {
        String brand = mockComputer.getBrand();
        when(computerRepository.findByBrand(brand)).thenReturn(List.of(mockComputer));
        assertTrue(List.of(mockComputer).size()==computerService.deleteByBrand(brand));
        verify(computerRepository).deleteAll(List.of(mockComputer));
    }

    @Test
    void testFindByBrand() {
        String brand = mockComputer.getBrand();
        when(computerRepository.findByBrand(brand)).thenReturn(List.of(mockComputer));
        assertTrue(computerService.findByBrand(brand).equals(List.of(mockComputer)));
    }

    @Test
    void testFindByBrand_WhenBrandIsNull_ThrowsIllegalArgumentException() {
        String brand = null;
        assertThrows(IllegalArgumentException.class,() -> computerService.findByBrand(brand));
    }

    @Test
    void testGetAll() {
        when(computerRepository.findAll()).thenReturn(List.of(mockComputer));
        assertTrue(List.of(mockComputer).equals(computerService.getAll()));
    }
}
