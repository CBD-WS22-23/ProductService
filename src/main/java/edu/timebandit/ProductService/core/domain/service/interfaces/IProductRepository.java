package edu.timebandit.ProductService.core.domain.service.interfaces;

import edu.timebandit.ProductService.core.domain.model.Watch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProductRepository extends CrudRepository<Watch, UUID> {
}
