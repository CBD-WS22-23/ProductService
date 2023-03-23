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

    @PostMapping(path = "/product")
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
    public @ResponseBody String update(@RequestBody Product product, @PathVariable String id) {
        return productService.updateProduct(product);
    }

    @DeleteMapping(path = "/product/{id}")
    public @ResponseBody String delete(@PathVariable String id) {
        return null;
    }

    @GetMapping("/products")
    public @ResponseBody Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
}
