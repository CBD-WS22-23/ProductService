package edu.timebandit.ProductService.port.checkout.consumer;

import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.checkout.dtos.ProductBoughtDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductBoughtConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductBoughtConsumer.class);

    @Autowired
    private IProductService productService;

    @RabbitListener(queues = "product_bought_queue")
    public void receiveProductBoughtMessage(ProductBoughtDTO boughtProducts) {
        LOGGER.info("Received message that products were bought: {}", boughtProducts);

        for (String productID : boughtProducts.getBoughtProducts().keySet()) {
            productService.updateProductStock(productID,
                    Math.negateExact(boughtProducts.getBoughtProducts().get(productID)));
        }
    }
}
