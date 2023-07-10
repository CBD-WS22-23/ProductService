package edu.timebandit.ProductService.port.basket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToBasketDTO {

    private String basketId;
    private BasketWatchDTO watch;
    private int quantity;
}
