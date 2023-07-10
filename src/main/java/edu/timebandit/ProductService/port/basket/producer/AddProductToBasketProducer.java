package edu.timebandit.ProductService.port.basket.producer;

import edu.timebandit.ProductService.port.basket.dtos.AddToBasketDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddProductToBasketProducer {


    @Value("product_exchange")
    private String exchange;

    @Value("add_product_to_basket_routing_key")
    private String addRoutingKey;


    private static final Logger logger = LoggerFactory.getLogger(AddProductToBasketProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public AddProductToBasketProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAddProductToBasketMessage(AddToBasketDTO addToBasketDTO) {

        logger.info("Sending message to add product to basket: {}", addToBasketDTO);
        rabbitTemplate.convertAndSend(exchange, addRoutingKey, addToBasketDTO);
    }
}