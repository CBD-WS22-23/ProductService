package edu.timebandit.ProductService.port.user.controller;

import edu.timebandit.ProductService.core.domain.model.BasketWatchDTO;
import edu.timebandit.ProductService.port.user.producer.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ProductProducer productProducer;

    @RequestMapping("/product-queue")
    public void triggerProductQueue() {
        productProducer.sendCreateProductMessage(new BasketWatchDTO("0", "Test Watch", 0.0, 1, "Thumbnail"));
    }
}
