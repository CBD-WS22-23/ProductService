package edu.timebandit.ProductService.port.basket.consumer.impl;

import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.basket.consumer.interfaces.IModifyInCartConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyInCartConsumer implements IModifyInCartConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyInCartConsumer.class);

    @Autowired
    private IProductService productService;

    @RabbitListener(queues = "product_added_to_basket_queue")
    public void receiveProductAddedToBasketMessage(String watchId) {
        LOGGER.info("Received message that product was added to a basket: {}", watchId);

        productService.increaseProductInCart(watchId);
    }

    @RabbitListener(queues = "product_removed_from_basket_queue")
    public void receiveProductRemovedFromBasketMessage(String watchId) {
        LOGGER.info("Received message that product was removed from a basket: {}", watchId);

        productService.decreaseProductInCart(watchId);
    }

}
