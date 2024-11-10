package org.factoriaf5.computers_shop.services;

import java.util.List;

import org.factoriaf5.computers_shop.models.Computer;
import org.factoriaf5.computers_shop.repositories.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerService {
    @Autowired
    ComputerRepository repository;

    public List<Computer> getAll(){
        return repository.findAll();
    }

    public Computer create(Computer computer){
        return repository.save(computer);
    }

    public List<Computer> findByBrand(String brand){
        if(brand == null){
            throw new IllegalArgumentException();
        }
        return repository.findByBrand(brand);
    }

    public Integer deleteByBrand(String brand){
        if(brand == null){
            throw new IllegalArgumentException();
        }
        List<Computer> computers = repository.findByBrand(brand);
        if(!computers.isEmpty()){
            repository.deleteAll(computers);
        }
        return computers.size();
    }
    
}
