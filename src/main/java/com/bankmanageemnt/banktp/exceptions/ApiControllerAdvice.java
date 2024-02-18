package com.bankmanageemnt.banktp.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@ControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public  @ResponseBody  Response entityNotFoundException(EntityNotFoundException e){
        return new Response(e.getMessage(),404);
    }
    record Response(String message,int code){ }
}
