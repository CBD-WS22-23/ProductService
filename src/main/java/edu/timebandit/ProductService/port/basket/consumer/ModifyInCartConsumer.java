package edu.timebandit.ProductService.port.basket.consumer;

import edu.timebandit.ProductService.port.basket.controller.BasketProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifyInCartConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModifyInCartConsumer.class);

    @Autowired
    private BasketProductController basketProductController;

    @RabbitListener(queues = "product_added_to_basket_queue")
    public void receiveProductAddedToBasketMessage(String watchId) {
        LOGGER.info("Received message that product was added to a basket: {}", watchId);

        basketProductController.increaseInCart(watchId);
    }

    @RabbitListener(queues = "product_removed_from_basket_queue")
    public void receiveProductRemovedFromBasketMessage(String watchId) {
        LOGGER.info("Received message that product was removed from a basket: {}", watchId);

        basketProductController.decreaseInCart(watchId);
    }

}
