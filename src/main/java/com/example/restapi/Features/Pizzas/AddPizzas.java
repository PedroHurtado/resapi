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

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;

@Configuration
public class AddPizzas {
    
    public record RequestCreatePizza(String name) implements Command<Pizza>
    {

    };

    public record ResponseCreatePizza(String id,String name){};

    @RestController
    @RequestMapping("/api/v1/pizzas")
    public class Controller{

        private final Pipeline pipeline;
        public Controller(Pipeline pipeline){
            this.pipeline = pipeline;
        }
        @PostMapping
        public ResponseEntity<?> add(@RequestBody RequestCreatePizza command){
            //adaptar
            
            var pizza = pipeline.send(command);
            
            var response = new ResponseCreatePizza(pizza.getId(), pizza.getName());
            return ResponseEntity.status(201).body(response);
        }
    }
   
    @Component
    public class Handler implements Command.Handler<RequestCreatePizza,Pizza>{

        
        private final PizzaRepository repository;
        public Handler(PizzaRepository repository){
            this.repository = repository;
        }        
        @Override
        public Pizza handle(RequestCreatePizza command) {           
           
            //validaciones
            System.out.println("handler");
            var pizza = Pizza.create(UUID.randomUUID().toString(), command.name);
            repository.add(pizza);
            return pizza;
        }

    }

   
}
