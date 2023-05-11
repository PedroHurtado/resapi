package com.example.restapi.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.Domain.PizzaRepository;
import com.example.restapi.common.exceptions.NotfoundException;

@RestController
@RequestMapping("/api/v1/pizzas")
//@CrossOrigin(origins = "*", maxAge = 86400 ,allowCredentials = "true")
//@CrossOrigin(origins = "*", maxAge = 86400)
public class PizzaController {

    public record PizzaRequest(String id, String name) {
    }
    public record Pizza(String id, String name) {
    }

    private final PizzaRepository repository;
    public PizzaController(PizzaRepository repository){
        this.repository = repository;
    }
    private final List<Pizza> pizzas = new ArrayList<>() {
        {
            add(new Pizza("1", "carbonara"));
            add(new Pizza("2", "mediterranea"));
            add(new Pizza("3", "cuatro quesos"));
        }
    };

    /*@PostMapping
    public ResponseEntity<?> post(@RequestBody Pizza pizza) {
        pizzas.add(pizza);
        return ResponseEntity.status(201).body(pizza);
    }*/

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Pizza pizza) {
        Pizza newPizza = new Pizza(id, pizza.name);
        Pizza result = getPizza(id);
        int index = pizzas.indexOf(result);
        pizzas.set(index, newPizza);
        return ResponseEntity.status(204).body("");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        Pizza result = getPizza(id);
        pizzas.remove(result);
        return ResponseEntity.status(204).body("");
    }

    @GetMapping
    public ResponseEntity<?> getAll(

    @RequestParam(required = false) String name,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size

    ) {
        //return ResponseEntity.ok(pizzas.stream().filter(p->p.name.equals(name)));
        return ResponseEntity.ok(
            repository.getAll().map(p->new PizzaRequest(p.getId(), p.getName()))
        
        );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        Pizza result = getPizza(id);
        return ResponseEntity.ok(result);
    }

    private Pizza getPizza(String id) {

        return pizzas
                .stream()
                .filter(p -> p.id.equals(id))
                .findAny()
                .orElseThrow(() -> {
                    throw new NotfoundException();
                });
    }

}
