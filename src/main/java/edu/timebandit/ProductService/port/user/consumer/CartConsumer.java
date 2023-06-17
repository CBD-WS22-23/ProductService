package edu.timebandit.ProductService.port.user.consumer;

import edu.timebandit.ProductService.core.domain.model.BasketWatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CartConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartConsumer.class);
    @RabbitListener(queues = "create_product_queue")
    public void consumeCreateProductMessage(BasketWatchDTO watch) {
        LOGGER.info("Received message to create product: {}", watch);
    }

    @RabbitListener(queues = "update_product_queue")
    public void consumeUpdateProductMessage(BasketWatchDTO watch) {
        LOGGER.info("Received message to update product: {}", watch);
    }

    @RabbitListener(queues = "delete_product_queue")
    public void consumeDeleteProductMessage(BasketWatchDTO watch) {
        LOGGER.info("Received message to delete product: {}", watch);
    }

}
