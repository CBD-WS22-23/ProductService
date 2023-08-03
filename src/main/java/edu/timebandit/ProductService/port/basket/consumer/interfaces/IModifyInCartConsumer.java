package edu.timebandit.ProductService.port.basket.consumer.interfaces;

public interface IModifyInCartConsumer {
    void receiveProductAddedToBasketMessage(String watchId);

    void receiveProductRemovedFromBasketMessage(String watchId);
}
