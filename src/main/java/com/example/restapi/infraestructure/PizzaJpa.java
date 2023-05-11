package com.example.restapi.infraestructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PizzaJpa {
    @Id    
    public String id;
    public String name;
}
