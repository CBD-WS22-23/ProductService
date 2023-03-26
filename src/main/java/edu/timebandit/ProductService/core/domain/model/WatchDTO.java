package edu.timebandit.ProductService.core.domain.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WatchDTO {
    private String name;
    private String description;
    private double price;
    private Map<String, String> generalInfo;
    private Map<String, String> housingInfo;
    private List<String> features;
    private int stock;
    private List<String> imageLinks;
    private String brand;
}
