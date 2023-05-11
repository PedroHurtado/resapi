package com.example.restapi.infraestructure;

import com.example.restapi.Domain.Entities.Pizza;

public class JpaMapPizza extends Pizza {

    protected JpaMapPizza(String id, String name) {
        super(id, name);        
    }
}
