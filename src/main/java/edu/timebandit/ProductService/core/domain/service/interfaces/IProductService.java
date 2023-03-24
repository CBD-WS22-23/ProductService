package edu.timebandit.ProductService.core.domain.service.interfaces;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.core.domain.model.WatchDTO;

public interface IProductService {

    String createProduct(WatchDTO watch);

    String updateProduct(WatchDTO watch, String watchID);

    void deleteProduct(String watchID);

    Watch getProductByID(String watchID);

    boolean checkIfProductExists(String watchID);

    Iterable<Watch> getAllProducts();
}
