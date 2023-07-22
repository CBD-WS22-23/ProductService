package edu.timebandit.ProductService.port.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductWatchDTO {
    @NotBlank
    private String name;
    private String description;
    @Positive(message = "Price must be greater than 0")
    private double price;
    @NotNull(message = "General info cannot be null")
    private Map<String, String> generalInfo;
    @NotNull(message = "Housing info cannot be null")
    private Map<String, String> housingInfo;
    @NotNull(message = "features cannot be null")
    private List<String> features;
    @Positive(message = "Stock must be greater than 0")
    private int stock;
    @NotNull(message = "Image links cannot be null")
    private List<String> imageLinks;
    @NotNull(message = "Thumbnail cannot be null")
    private String thumbnail;
    @NotNull(message = "Brand cannot be null")
    private String brand;
}
