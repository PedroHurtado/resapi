package com.example.restapi.Features.Pizzas;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.restapi.Domain.PizzaRepository;
import com.example.restapi.Domain.Entities.Pizza;

@Configuration
public class AddPizzas {
    
    public record RequestCreatePizza(String name){};

    public record ResponseCreatePizza(String id,String name){};

    @RestController
    @RequestMapping("/api/v1/pizzas")
    public class Controller{

        private final IHandler handler;
        public Controller(IHandler handler){
            this.handler = handler;
        }
        @PostMapping
        public ResponseEntity<?> add(@RequestBody RequestCreatePizza command){
            //adaptar
            
            var pizza = handler.add(command);
            var response = new ResponseCreatePizza(pizza.getId(), pizza.getName());
            return ResponseEntity.status(201).body(response);
        }
    }
   
    //puerto primario
    public interface IHandler{
        Pizza add(RequestCreatePizza command);
    }

    @Component
    public class Handler implements IHandler{

        private final PizzaRepository repository;
        public Handler(PizzaRepository repository){
            this.repository = repository;
        }
        @Override
        public Pizza add(RequestCreatePizza command) {   
            
            //Madiator Decorator
            //trasacciones
            //logger
            //validaciones

            var pizza = new Pizza(UUID.randomUUID().toString(), command.name);
            repository.add(pizza);
            return pizza;
        }

    }
}
