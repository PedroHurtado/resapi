package com.example.restapi.infraestructure;

import org.springframework.data.repository.CrudRepository;

public interface PizzaRepositoryJpa extends CrudRepository<PizzaJpa,String>{
    
}
