package edu.timebandit.ProductService.core.domain.service.interfaces;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.port.user.dtos.ProductWatchDTO;

public interface IProductService {

    Watch createProduct(ProductWatchDTO watch);

    String updateProduct(ProductWatchDTO watch, String watchID);

    void updateProductStock(String watchID, int amount);

    void increaseProductInCart(String watchID);

    void decreaseProductInCart(String watchID);

    void deleteProduct(String watchID);

    Watch getProductByID(String watchID);

    Iterable<Watch> getProductsByBrand(String brand);

    Iterable<Watch> getProductsByPriceRange(double min, double max);

    Iterable<Watch> getProductsByIDs(Iterable<String> watchIDs);

    boolean checkIfProductExists(String watchID);

    Iterable<Watch> getAllProducts();
}
