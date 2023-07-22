package edu.timebandit.ProductService.port.basket.controller;

import edu.timebandit.ProductService.core.domain.service.interfaces.IProductService;
import edu.timebandit.ProductService.port.basket.dtos.AddToBasketDTO;
import edu.timebandit.ProductService.port.basket.dtos.BasketWatchDTO;
import edu.timebandit.ProductService.port.basket.exception.InvalidQuantityException;
import edu.timebandit.ProductService.port.basket.producer.interfaces.IAddProductToBasketProducer;
import edu.timebandit.ProductService.port.user.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BasketProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IAddProductToBasketProducer addProductToBasketProducer;

    @Qualifier("BasketModelMapper")
    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Add a watch to a basket")
    @PostMapping(path = "/basket/{basketID}/products/{watchID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addToBasket(@PathVariable String basketID, @PathVariable String watchID,
                            @RequestParam int q) {

        if (q <= 0) {
            throw new InvalidQuantityException();
        }
        if (!productService.checkIfProductExists(watchID)) {
            throw new ProductNotFoundException(watchID);
        }
        BasketWatchDTO basketWatchDTO = modelMapper.map(productService.getProductByID(watchID), BasketWatchDTO.class);

        addProductToBasketProducer.sendAddProductToBasketMessage(new AddToBasketDTO(basketID, basketWatchDTO, q));
    }
}
