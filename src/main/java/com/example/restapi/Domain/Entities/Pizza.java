package com.example.restapi.Domain.Entities;

import java.util.ArrayList;
import java.util.List;

import com.example.restapi.Domain.Events.EventCreatePizza;

import an.awesome.pipelinr.Notification;

public class Pizza {

    public final List<Notification> events = new ArrayList<>();

    private final String id;
    private String name;

    protected Pizza(String id, String name){
        this.id = id;
        this.name = name;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void update(String name){
        this.name = name;
    }
    public void ClearEvents(){
        events.clear();
    }
    public void addEvent(Notification event){
        events.add(event);
    }
    public static Pizza create(String id,String name){
        var pizza = new Pizza(id, name);
        pizza.addEvent( new EventCreatePizza(id, name));        
        return pizza;
    }
}
