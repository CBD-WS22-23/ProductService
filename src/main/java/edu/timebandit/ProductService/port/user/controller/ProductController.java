package edu.timebandit.ProductService.port.user.controller;

import edu.timebandit.ProductService.core.domain.model.Product;
import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.user.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping(path = "/product/")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void create(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable String id) {
        Product product = productService.getProductByWatchID(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return product;
    }

    @PutMapping(path = "/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String update(@RequestBody Product product, @PathVariable String id) {
        Product product1 = productService.getProductByWatchID(id);
        if (!product.getWatchID().equals(id) || product1 == null) {
            throw new ProductNotFoundException(id);
        }
        return productService.updateProduct(product);
    }

    @DeleteMapping(path = "/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String delete(@PathVariable String id) {
        Product product = productService.getProductByWatchID(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        productService.deleteProduct(product);
        return "Product with id: " + id + " was deleted";
    }

    @GetMapping("/products/")
    public @ResponseBody Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
}
