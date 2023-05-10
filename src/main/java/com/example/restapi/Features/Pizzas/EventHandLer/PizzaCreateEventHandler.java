package com.example.restapi.Features.Pizzas.EventHandLer;

import org.springframework.stereotype.Component;

import com.example.restapi.Domain.Events.EventCreatePizza;

import an.awesome.pipelinr.Notification;

@Component
public class PizzaCreateEventHandler implements Notification.Handler<EventCreatePizza> {

    @Override
    public void handle(EventCreatePizza event) {        
        System.out.println("event create pizza 1");
    }
    
}
