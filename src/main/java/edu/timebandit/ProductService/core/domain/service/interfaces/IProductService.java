package edu.timebandit.ProductService.core.domain.service.interfaces;

import edu.timebandit.ProductService.core.domain.model.Product;

import java.util.UUID;

public interface IProductService {

    String createProduct(Product product);

    String updateProduct(Product product);

    void deleteProduct(Product product);

    void deleteProduct(UUID id);

    Product getProductByWatchID(String id);

    Product getProduct(UUID id);

    Iterable<Product> getAllProducts();
}
