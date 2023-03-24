package edu.timebandit.ProductService.port.user.controller;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.core.domain.model.WatchDTO;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.user.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Operation(summary = "Add a new watch to the store")
    @PostMapping(path = "/watch")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody WatchDTO watch) {
        return productService.createProduct(watch);
    }

    @Operation(summary = "Find a watch by id")
    @GetMapping("/watch/{watchID}")
    public Watch getProduct(@PathVariable String watchID) {
        Watch watch = productService.getProductByID(watchID);
        if (watch == null) {
            throw new ProductNotFoundException(watchID);
        }
        return watch;
    }

    @Operation(summary = "Update a watch by id")
    @PutMapping(path = "/watch/{watchID}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@RequestBody WatchDTO watch, @PathVariable String watchID) {
        if (productService.checkIfProductExists(watchID)) {
            return productService.updateProduct(watch, watchID);
        }
        throw new ProductNotFoundException(watchID);
    }

    @Operation(summary = "Delete a watch by id")
    @DeleteMapping(path = "/watch/{watchID}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String watchID) {
        if (productService.checkIfProductExists(watchID)) {
            productService.deleteProduct(watchID);
            return "Product with id: " + watchID + " was deleted";

        }
        throw new ProductNotFoundException(watchID);
    }

    @Operation(summary = "Get all watches")
    @GetMapping("/watches")
    public Iterable<Watch> getProducts() {
        return productService.getAllProducts();
    }
}
