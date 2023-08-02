package edu.timebandit.ProductService.port.basket.exception;

public class QuantityLessThanStockException extends RuntimeException {

        public QuantityLessThanStockException() {
            super("Quantity must be less or equal to stock.");
        }
}
