package com.example.restapi.Domain;

import java.util.List;

import com.example.restapi.Domain.Entities.Pizza;

//puerto secundario
public interface PizzaRepository {
    void add(Pizza pizza);
    void update(Pizza pizza);
    void remove(Pizza pizza);
    Pizza get(String id);
    List<Pizza> getAll();
}
