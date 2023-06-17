package edu.timebandit.ProductService.port.user.controller;

import edu.timebandit.ProductService.core.domain.model.BasketWatchDTO;
import edu.timebandit.ProductService.core.domain.model.Watch;
import edu.timebandit.ProductService.core.domain.model.ProductWatchDTO;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.user.exception.ProductNotFoundException;
import edu.timebandit.ProductService.port.user.producer.ProductProducer;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductProducer productProducer;

    @Autowired
    @Qualifier("BasketModelMapper")
    private ModelMapper modelMapper;

    @Operation(summary = "Add a new watch to the store")
    @PostMapping(path = "/watches")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody ProductWatchDTO watch) {
        Watch createdWatch = productService.createProduct(watch);
        productProducer.sendCreateProductMessage(modelMapper.map(createdWatch, BasketWatchDTO.class));
        return createdWatch.getId().toString();
    }

    @Operation(summary = "Update a watch by id")
    @PutMapping(path = "/watches/{watchID}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@RequestBody ProductWatchDTO watch, @PathVariable String watchID) {
        if (productService.checkIfProductExists(watchID)) {
            BasketWatchDTO basketWatchDTO = modelMapper.map(watch, BasketWatchDTO.class);
            basketWatchDTO.setId(watchID);
            productProducer.sendUpdateProductMessage(basketWatchDTO);
            return productService.updateProduct(watch, watchID);
        }
        throw new ProductNotFoundException(watchID);
    }

    @Operation(summary = "Delete a watch by id")
    @DeleteMapping(path = "/watches/{watchID}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String watchID) {
        if (productService.checkIfProductExists(watchID)) {
            productProducer.sendDeleteProductMessage(new BasketWatchDTO(watchID, null, 0.0, 0, null));
            productService.deleteProduct(watchID);
            return "Product with id: " + watchID + " was deleted";

        }
        throw new ProductNotFoundException(watchID);
    }

    @Operation(summary = "Get all watches or optionally by id(s)")
    @GetMapping("/watches")
    public Iterable<Watch> getProducts(@RequestParam(required = false) Iterable<String> watchIDs) {
        if (watchIDs != null) {
            return productService.getProductsByIDs(watchIDs);
        }
        return productService.getAllProducts();
    }

    @Operation(summary = "Find a watch by id")
    @GetMapping("/watches/{watchID}")
    public Watch getProduct(@PathVariable String watchID) {
        Watch watch = productService.getProductByID(watchID);
        if (watch == null) {
            throw new ProductNotFoundException(watchID);
        }
        return watch;
    }

    @Operation(summary = "Get all watches by brand")
    @GetMapping("/watches/brand/{brand}")
    public Iterable<Watch> getProductsByBrand(@PathVariable String brand) {
        String formattedBrand = brand.substring(0, 1).toUpperCase() + brand.substring(1).toLowerCase();
        return productService.getProductsByBrand(formattedBrand);
    }

    @Operation(summary = "Get all watches by price range")
    @GetMapping("/watches/price")
    public Iterable<Watch> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max);
    }

}
