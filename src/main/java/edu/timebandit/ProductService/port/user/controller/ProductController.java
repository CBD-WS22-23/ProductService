package edu.timebandit.ProductService.port.user.controller;

import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.user.dtos.ProductWatchDTO;
import edu.timebandit.ProductService.port.user.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ps/api/v1")
@Validated
public class ProductController {

    @Autowired
    private IProductService productService;


    @Operation(summary = "Add a new watch to the store")
    @PostMapping(path = "/watch")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid ProductWatchDTO watch) {
        Watch createdWatch = productService.createProduct(watch);
        return createdWatch.getId().toString();
    }


    @Operation(summary = "Update a watch by id")
    @PutMapping(path = "/watch/{watchID}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@RequestBody ProductWatchDTO watch, @PathVariable String watchID) {
        if (productService.checkIfProductExists(watchID)) {
            return productService.updateProduct(watch, watchID);
        }
        throw new ProductNotFoundException(watchID);
    }

    @Operation(summary = "Update the stock of a watch by id")
    @PutMapping(path = "/watch/{watchID}/stock")
    @ResponseStatus(HttpStatus.OK)
    public void updateStock(@RequestParam int amount, @PathVariable String watchID) {
        if (!productService.checkIfProductExists(watchID)) {
            throw new ProductNotFoundException(watchID);
        }
        productService.updateProductStock(watchID, amount);
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

    @Operation(summary = "Get all watches or optionally by id(s)")
    @GetMapping("/watch")
    public Iterable<Watch> getProducts(@RequestParam(required = false) Iterable<String> watchIDs) {
        if (watchIDs != null) {
            return productService.getProductsByIDs(watchIDs);
        }
        return productService.getAllProducts();
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

    @Operation(summary = "Get all watches by brand")
    @GetMapping("/watch/brand/{brand}")
    public Iterable<Watch> getProductsByBrand(@PathVariable String brand) {
        String formattedBrand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        return productService.getProductsByBrand(formattedBrand);
    }

    @Operation(summary = "Get all watches by price range")
    @GetMapping("/watch/price")
    public Iterable<Watch> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max);
    }

}
