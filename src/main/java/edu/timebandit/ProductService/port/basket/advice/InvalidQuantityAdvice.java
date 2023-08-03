package edu.timebandit.ProductService.port.basket.advice;

import edu.timebandit.ProductService.port.basket.exception.InvalidQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidQuantityAdvice {
    @ResponseBody
    @ExceptionHandler(value = InvalidQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidQuantityHandler(InvalidQuantityException ex){
        return ex.getMessage();
    }
}
