package com.example.restapi.common.decorators;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import an.awesome.pipelinr.Command;

@Component
@Order(2)
class TxMiddleware implements Command.Middleware {

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        
        System.out.println("start tx"); 

        R response = next.invoke();     

        System.out.println("end tx"); 
        
        return response;
    }
}
