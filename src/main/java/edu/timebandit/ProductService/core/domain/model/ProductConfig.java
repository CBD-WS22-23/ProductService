package edu.timebandit.ProductService.core.domain.model;

import edu.timebandit.ProductService.core.domain.service.interfaces.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ProductConfig {


    CommandLineRunner commandLineRunner(IProductRepository productRepository) {
        List<Product> newProducts = new ArrayList<>();
       //newProducts.add(
       //         new Product(UUID.randomUUID(), ""));

        return args -> productRepository.saveAll(newProducts);
    }
}
