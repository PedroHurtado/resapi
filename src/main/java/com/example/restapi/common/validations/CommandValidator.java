package com.example.restapi.common.validations;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.repack.com.google.common.reflect.TypeToken;

public interface CommandValidator<C extends Command<R>, R> {
    void validateCommand(C command);

    default boolean matches(C command) {
        TypeToken<C> typeToken = new TypeToken<C>(getClass()) { // available in Guava 12+.
        };

        return typeToken.isSupertypeOf(command.getClass());
    }
}
