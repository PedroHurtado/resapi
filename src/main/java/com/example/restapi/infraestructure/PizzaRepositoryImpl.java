package com.example.restapi.infraestructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.restapi.Domain.PizzaRepository;
import com.example.restapi.Domain.Entities.Pizza;

@Component
//adaptador secundario
public class PizzaRepositoryImpl implements PizzaRepository {

    public List<Pizza> pizzas = new ArrayList<>();
    @Override
    public void add(Pizza pizza) {
        pizzas.add(pizza);
    }

    @Override
    public void update(Pizza pizza) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(Pizza pizza) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public Pizza get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public List<Pizza> getAll() {
       return pizzas;
    }
    
}
