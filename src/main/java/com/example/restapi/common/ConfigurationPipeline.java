package com.example.restapi.common;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Notification;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;

@Configuration
public class ConfigurationPipeline {

    @Bean
    Pipeline pipeline (
         ObjectProvider<Command.Handler> commandHandlers, 
         ObjectProvider<Command.Middleware> middlewares,
         ObjectProvider<Notification.Handler> notificationHandlers
    ) {
        return new Pipelinr()
          .with(commandHandlers::stream)          
          .with(middlewares::orderedStream)
          .with(notificationHandlers::stream);
    }
}
