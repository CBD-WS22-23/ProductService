package edu.timebandit.ProductService.core.domain.service.impl;

import edu.timebandit.ProductService.core.domain.model.Product;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductRepository;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    ProductService(IProductRepository productRepository){

        this.productRepository = productRepository;
    }


    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
