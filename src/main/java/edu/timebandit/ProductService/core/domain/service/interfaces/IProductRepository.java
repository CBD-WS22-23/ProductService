package edu.timebandit.ProductService.core.domain.service.interfaces;

import edu.timebandit.ProductService.core.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends CrudRepository<Product, String> {
}
