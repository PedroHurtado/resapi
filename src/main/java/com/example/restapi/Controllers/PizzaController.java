package com.example.restapi.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.common.exceptions.NotfoundException;

@RestController
@RequestMapping("/api/v1/pizzas")
public class PizzaController {

    public record Pizza(String id, String name) {
    }

    private final List<Pizza> pizzas = new ArrayList<>() {
        {
            add(new Pizza("1", "carbonara"));
            add(new Pizza("2", "mediterranea"));
            add(new Pizza("3", "cuatro quesos"));
        }
    };

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Pizza pizza) {
        pizzas.add(pizza);
        return ResponseEntity.status(201).body(pizza);
    }

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
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(pizzas);
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
