package edu.timebandit.ProductService.core.domain.service.interfaces;

import edu.timebandit.ProductService.core.domain.model.Product;

public interface IProductService {

    void createProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProduct(String id);

    Iterable<Product> getAllProducts();
}
