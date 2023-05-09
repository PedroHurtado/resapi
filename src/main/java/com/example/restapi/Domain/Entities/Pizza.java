package com.example.restapi.Domain.Entities;


public class Pizza {
    private final String id;
    private String name;
    public Pizza(String id, String name){
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
}
