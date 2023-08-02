package edu.timebandit.ProductService.port.checkout.consumer.interfaces;

import edu.timebandit.ProductService.port.checkout.dtos.ProductBoughtDTO;
import jakarta.validation.Valid;

public interface IProductBoughtConsumer {
    void receiveProductBoughtMessage(@Valid ProductBoughtDTO boughtProducts);
}
