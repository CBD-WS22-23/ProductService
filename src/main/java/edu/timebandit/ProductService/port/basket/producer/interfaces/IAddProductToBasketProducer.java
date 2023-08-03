package edu.timebandit.ProductService.port.basket.producer.interfaces;

import edu.timebandit.ProductService.port.basket.dtos.AddToBasketDTO;
import jakarta.validation.Valid;

public interface IAddProductToBasketProducer {
    void sendAddProductToBasketMessage(@Valid AddToBasketDTO addToBasketDTO);
}
