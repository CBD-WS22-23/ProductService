package edu.timebandit.ProductService.core.domain.service.impl;

import edu.timebandit.ProductService.core.domain.model.Product;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductRepository;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    ProductService(IProductRepository productRepository){

        this.productRepository = productRepository;
    }


    @Override
    public String createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct.getWatchID();
    }

    @Override
    public String updateProduct(Product product) {
        Product updatedProduct = productRepository.save(product);
        return updatedProduct.getWatchID();
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id.toString());
    }

    @Override
    public Product getProductByWatchID(String id) {
        Iterable<Product> products = productRepository.findAll();
        for (Product product : products) {
            if (product.getWatchID().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product getProduct(UUID id) {
        return productRepository.findById(id.toString()).orElse(null);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
