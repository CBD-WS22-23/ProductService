package edu.timebandit.ProductService.port.user.producer;

import edu.timebandit.ProductService.core.domain.model.BasketWatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {


    @Value("product_exchange")
    private String exchange;

    @Value("create_product_routing_key")
    private String createRoutingKey;

    @Value("update_product_routing_key")
    private String updateRoutingKey;

    @Value("delete_product_routing_key")
    private String deleteRoutingKey;

    private static final Logger logger = LoggerFactory.getLogger(ProductProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public ProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCreateProductMessage(BasketWatchDTO watch) {

        logger.info("Sending message to create product: {}", watch);
        rabbitTemplate.convertAndSend(exchange, createRoutingKey, watch);
    }

    public void sendUpdateProductMessage(BasketWatchDTO watch) {

        logger.info("Sending message to update product: {}", watch);
        rabbitTemplate.convertAndSend(exchange, updateRoutingKey, watch);
    }

    public void sendDeleteProductMessage(BasketWatchDTO watch) {

        logger.info("Sending message to delete product: {}", watch);
        rabbitTemplate.convertAndSend(exchange, deleteRoutingKey, watch);
    }
}