package org.iesbelen.videoclub.controller;

import org.iesbelen.videoclub.exception.ActorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ActorNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ActorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String actorNotFoundHandler(ActorNotFoundException actorNotFoundException) {
        return actorNotFoundException.getMessage();
    }
}
