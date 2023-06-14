package edu.timebandit.ProductService.port.user.producer;

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
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(ProductProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public ProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCreateProductMessage(String message) {
        logger.info("Sending message to create product: {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}