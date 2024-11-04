package org.factoriaf5.computers_shop.services;

import java.util.List;

import org.factoriaf5.computers_shop.models.Computer;
import org.factoriaf5.computers_shop.repositories.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ComputerService {
    @Autowired
    ComputerRepository repository;

    public List<Computer> getAll(){
        return repository.findAll();
    }

    public Computer create(Computer computer){
        try{
            return repository.save(computer);
        }catch(DataIntegrityViolationException e){
            return null;
        }
    }

    public List<Computer> findByBrand(String brand){
        return repository.findByBrand(brand);
    }

    public Integer deleteByBrand(String brand){
        List<Computer> computers = repository.findByBrand(brand);
        repository.deleteAll(computers);
        return computers.size();
    }
    
}
