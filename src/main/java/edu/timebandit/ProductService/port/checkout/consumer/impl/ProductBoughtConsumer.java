package edu.timebandit.ProductService.port.checkout.consumer.impl;

import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.checkout.consumer.interfaces.IProductBoughtConsumer;
import edu.timebandit.ProductService.port.checkout.dtos.ProductBoughtDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBoughtConsumer implements IProductBoughtConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBoughtConsumer.class);

    @Autowired
    private IProductService productService;

    @RabbitListener(queues = "product_bought_queue")
    public void receiveProductBoughtMessage(@Valid ProductBoughtDTO boughtProducts) {
        LOGGER.info("Received message that products were bought: {}", boughtProducts);

        for (String productID : boughtProducts.getBoughtProducts().keySet()) {
            productService.updateProductStock(productID,
                    Math.negateExact(boughtProducts.getBoughtProducts().get(productID)));
            productService.decreaseProductInCart(productID);
        }
    }
}
