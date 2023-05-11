package com.example.restapi.infraestructure;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;


import org.springframework.stereotype.Repository;

import com.example.restapi.Domain.PizzaRepository;
import com.example.restapi.Domain.Entities.Pizza;

import an.awesome.pipelinr.Pipeline;

@Repository
// adaptador secundario
public class PizzaRepositoryImpl implements PizzaRepository {

    private final PizzaRepositoryJpa repository;

    public List<Pizza> pizzas = new ArrayList<>();
    private final Pipeline pipeline;

    public PizzaRepositoryImpl(Pipeline pipeline, PizzaRepositoryJpa repository) {
        this.pipeline = pipeline;
        this.repository = repository;
    }

    @Override
    public void add(Pizza pizza) {
        // antes de guadar

        var pizzaJpa = new PizzaJpa();
        pizzaJpa.id = pizza.getId();
        pizzaJpa.name = pizza.getName();

        for (var ev : pizza.events) {
            pipeline.send(ev);
        }

        repository.save(pizzaJpa);

        System.out.println("transaction terminada");

        // siempre antes por mantener transaccionalidad
        // no hacer así
        /*
         * for(var ev:pizza.events){
         * pipeline.send(ev);
         * }
         */
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
    public Stream<Pizza> getAll() {
        
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(p->new JpaMapPizza(p.id, p.name));         
    }

}
