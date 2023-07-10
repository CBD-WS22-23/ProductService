package edu.timebandit.ProductService.port.user.controller;

import edu.timebandit.ProductService.port.basket.dtos.AddToBasketDTO;
import edu.timebandit.ProductService.port.basket.dtos.BasketWatchDTO;
import edu.timebandit.ProductService.port.basket.producer.AddProductToBasketProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    AddProductToBasketProducer addProductToBasketProducer;

    @RequestMapping("/product-queue")
    public void triggerProductQueue() {
        addProductToBasketProducer.sendAddProductToBasketMessage(new AddToBasketDTO("bdfc6420-2bda-4fbb-a445-700b49ea2cc1"
                , new BasketWatchDTO("58b74e3e-8e46-4419-bfc6-a8c43dfa694a", "Rolex Submariner", 12913.00, 25,
                "Thumbnail"), 1));

    }
}
