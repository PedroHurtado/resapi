package com.example.restapi.infraestructure;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.restapi.Domain.PizzaRepository;
import com.example.restapi.Domain.Entities.Pizza;

import an.awesome.pipelinr.Pipeline;

@Component
//adaptador secundario
public class PizzaRepositoryImpl implements PizzaRepository {

    public List<Pizza> pizzas = new ArrayList<>();
    private final Pipeline pipeline;
    public PizzaRepositoryImpl(Pipeline pipeline){
        this.pipeline = pipeline;
    }
    @Override
    public void add(Pizza pizza) {
        //antes de guadar
        
        for(var ev:pizza.events){
            pipeline.send(ev);
        }
        
        pizzas.add(pizza);

        System.out.println("transaction terminada");

        //siempre antes por mantener transaccionalidad
        //no hacer así
        /*for(var ev:pizza.events){
            pipeline.send(ev);
        }*/
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
