package edu.timebandit.ProductService.core.domain.service.impl;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.core.domain.model.WatchDTO;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductRepository;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;


@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;

    ProductService(IProductRepository productRepository){

        this.productRepository = productRepository;
    }


    @Override
    public String createProduct(WatchDTO watch) {
        Watch newWatch = new Watch(UUID.randomUUID(), watch.getName(), watch.getDescription(), watch.getPrice(),
                watch.getGeneralInfo(), watch.getHousingInfo(), watch.getFeatures(), watch.getStock(), 0,
                watch.getImageLinks(), watch.getBrand());
        newWatch = productRepository.save(newWatch);
        return newWatch.getId().toString();
    }

    @Override
    public String updateProduct(WatchDTO watch, String watchID) {
        Watch oldWatch = productRepository.findById(UUID.fromString(watchID)).orElse(null);
        int cartAmount = 0;
        if(oldWatch != null){
            cartAmount = oldWatch.getInCart();
        }
        Watch updatedWatch = new Watch(UUID.fromString(watchID), watch.getName(), watch.getDescription(), watch.getPrice(),
                watch.getGeneralInfo(), watch.getHousingInfo(), watch.getFeatures(), watch.getStock(), cartAmount,
                watch.getImageLinks(), watch.getBrand());
        updatedWatch = productRepository.save(updatedWatch);
        return updatedWatch.getId().toString();
    }

    @Override
    public void deleteProduct(String watchID) {
        productRepository.deleteById(UUID.fromString(watchID));
    }

    @Override
    public Watch getProductByID(String watchID) {
        return productRepository.findById(UUID.fromString(watchID)).orElse(null);
    }

    @Override
    public Iterable<Watch> getProductsByBrand(String brand) {
        Iterable<Watch> allProducts = productRepository.findAll();
        ArrayList<Watch> productsByBrand = new ArrayList<>();
        for (Watch watch : allProducts) {
            if (watch.getBrand().equals(brand)) {
                productsByBrand.add(watch);
            }
        }
        return productsByBrand;
    }

    @Override
    public Iterable<Watch> getProductsByPriceRange(double min, double max) {
        Iterable<Watch> allProducts = productRepository.findAll();
        ArrayList<Watch> productsByPriceRange = new ArrayList<>();
        for (Watch watch : allProducts) {
            if (watch.getPrice() >= min && watch.getPrice() <= max) {
                productsByPriceRange.add(watch);
            }
        }
        return productsByPriceRange;
    }

    @Override
    public Iterable<Watch> getProductsByIDs(Iterable<String> watchIDs) {
        HashSet<UUID> watchIDSet = new HashSet<>();
        for (String watchID : watchIDs) {
            watchIDSet.add(UUID.fromString(watchID));
        }
        return productRepository.findAllById(watchIDSet);
    }

    @Override
    public boolean checkIfProductExists(String watchID) {
        return productRepository.existsById(UUID.fromString(watchID));
    }

    @Override
    public Iterable<Watch> getAllProducts() {
        return productRepository.findAll();
    }
}
