package edu.timebandit.ProductService.port.checkout.consumer.interfaces;

import edu.timebandit.ProductService.port.checkout.dtos.ProductBoughtDTO;

public interface IProductBoughtConsumer {
    void receiveProductBoughtMessage(ProductBoughtDTO boughtProducts);
}
