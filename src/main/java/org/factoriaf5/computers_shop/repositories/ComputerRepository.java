package org.factoriaf5.computers_shop.repositories;

import java.util.List;

import org.factoriaf5.computers_shop.models.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long>{
    List<Computer> findByBrand(String brand);
}
