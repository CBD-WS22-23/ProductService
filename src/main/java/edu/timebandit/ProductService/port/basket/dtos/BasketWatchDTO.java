package edu.timebandit.ProductService.port.basket.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketWatchDTO {
    private String id;
    private String name;
    private double price;
    private int stock;
    private String thumbnail;
}