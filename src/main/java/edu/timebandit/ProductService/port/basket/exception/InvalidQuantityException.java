package edu.timebandit.ProductService.port.basket.exception;

public class InvalidQuantityException extends RuntimeException{

    public InvalidQuantityException() {
        super("Quantity must be greater than 0.");
    }
}
