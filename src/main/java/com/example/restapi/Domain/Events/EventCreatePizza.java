package com.example.restapi.Domain.Events;

import an.awesome.pipelinr.Notification;

public record EventCreatePizza(String Id, String Name) implements Notification{
    
}
