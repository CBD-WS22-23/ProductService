package edu.timebandit.ProductService.core.domain.service.impl;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.port.user.dtos.ProductWatchDTO;
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
    public Watch createProduct(ProductWatchDTO watch) {
        Watch newWatch = new Watch(UUID.randomUUID(), watch.getName(), watch.getDescription(), watch.getPrice(),
                watch.getGeneralInfo(), watch.getHousingInfo(), watch.getFeatures(), watch.getStock(), 0,
                watch.getImageLinks(), watch.getThumbnail(), watch.getBrand());
        newWatch = productRepository.save(newWatch);
        return newWatch;
    }

    @Override
    public String updateProduct(ProductWatchDTO watch, String watchID) {
        Watch oldWatch = productRepository.findById(UUID.fromString(watchID)).orElse(null);
        if(oldWatch == null){
            return null;
        }
        int cartAmount = oldWatch.getInCart();
        Watch updatedWatch = new Watch(UUID.fromString(watchID), watch.getName(), watch.getDescription(), watch.getPrice(),
                watch.getGeneralInfo(), watch.getHousingInfo(), watch.getFeatures(), watch.getStock(), cartAmount,
                watch.getImageLinks(), watch.getThumbnail(), watch.getBrand());
        updatedWatch = productRepository.save(updatedWatch);
        return updatedWatch.getId().toString();
    }

    @Override
    public void updateProductStock(String watchID, int amount) {
        Watch retrievedWatch = productRepository.findById(UUID.fromString(watchID)).orElse(null);
        if(retrievedWatch != null){
            retrievedWatch.setStock(Math.max(retrievedWatch.getStock() + amount, 0));
            productRepository.save(retrievedWatch);
        }
    }

    @Override
    public void increaseProductInCart(String watchID) {
        Watch retrievedWatch = productRepository.findById(UUID.fromString(watchID)).orElse(null);
        if(retrievedWatch != null){
            retrievedWatch.setInCart(retrievedWatch.getInCart() + 1);
            productRepository.save(retrievedWatch);
        }
    }

    @Override
    public void decreaseProductInCart(String watchID) {
        Watch retrievedWatch = productRepository.findById(UUID.fromString(watchID)).orElse(null);
        if(retrievedWatch != null){
            retrievedWatch.setInCart(Math.max(retrievedWatch.getInCart() - 1, 0));
            productRepository.save(retrievedWatch);
        }
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
