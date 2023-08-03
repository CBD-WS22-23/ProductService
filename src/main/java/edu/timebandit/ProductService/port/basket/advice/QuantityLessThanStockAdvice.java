package edu.timebandit.ProductService.port.basket.advice;

import edu.timebandit.ProductService.port.basket.exception.QuantityLessThanStockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuantityLessThanStockAdvice {

    @ResponseBody
    @ExceptionHandler(value = QuantityLessThanStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String quantityLessThanStockHandler(QuantityLessThanStockException ex){
        return ex.getMessage();
    }
}
