package edu.timebandit.ProductService.port.basket.producer.interfaces;

import edu.timebandit.ProductService.port.basket.dtos.AddToBasketDTO;

public interface IAddProductToBasketProducer {
    void sendAddProductToBasketMessage(AddToBasketDTO addToBasketDTO);
}
