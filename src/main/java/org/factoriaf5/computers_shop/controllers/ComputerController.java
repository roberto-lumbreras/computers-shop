package org.factoriaf5.computers_shop.controllers;

import java.util.List;

import org.factoriaf5.computers_shop.models.Computer;
import org.factoriaf5.computers_shop.services.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/computers")
public class ComputerController {
    @Autowired
    ComputerService service;

    @PostMapping
    public ResponseEntity<Computer> create(@RequestBody Computer computer){
        Computer updatedComputer = service.create(computer);
        return updatedComputer!=null?
        ResponseEntity.ok(updatedComputer):
        ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByBrand(@RequestParam String brand){
        return service.deleteByBrand(brand)>0?
            ResponseEntity.noContent().build():
            ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Computer>> getComputers(@RequestParam(required=false) String brand){
        return brand != null?
        ResponseEntity.ok(service.findByBrand(brand)):
        ResponseEntity.ok(service.getAll());
    }

    
}
