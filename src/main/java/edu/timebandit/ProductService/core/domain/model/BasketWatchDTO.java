package edu.timebandit.ProductService.core.domain.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BasketWatchDTO {
    private String id;
    private String name;
    private double price;
    private int stock;
    private String thumbnail;
}
