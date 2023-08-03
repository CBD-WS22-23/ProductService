package edu.timebandit.ProductService.port.user.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id) {
      super("Product with the ID: " + id + " could not be found.");
    }
}
