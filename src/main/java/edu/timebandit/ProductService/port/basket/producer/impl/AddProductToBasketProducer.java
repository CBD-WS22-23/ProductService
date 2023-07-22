package edu.timebandit.ProductService.port.basket.producer.impl;

import edu.timebandit.ProductService.port.basket.dtos.AddToBasketDTO;
import edu.timebandit.ProductService.port.basket.producer.interfaces.IAddProductToBasketProducer;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddProductToBasketProducer implements IAddProductToBasketProducer {


    @Value("product_exchange")
    private String exchange;

    @Value("add_product_to_basket_routing_key")
    private String addRoutingKey;


    private static final Logger logger = LoggerFactory.getLogger(AddProductToBasketProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public AddProductToBasketProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAddProductToBasketMessage(@Valid AddToBasketDTO addToBasketDTO) {

        logger.info("Sending message to add product to basket: {}", addToBasketDTO);
        rabbitTemplate.convertAndSend(exchange, addRoutingKey, addToBasketDTO);
    }
}