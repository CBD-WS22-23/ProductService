package edu.timebandit.ProductService.port.basket.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketWatchDTO {
    @NotBlank(message = "Watch id cannot be blank")
    private String id;
    @NotBlank(message = "Watch name cannot be blank")
    private String name;
    @PositiveOrZero(message = "Price must be greater than or equal to 0")
    private double price;
    @Positive(message = "Stock must be greater than 0")
    private int stock;
    @NotNull(message = "Thumbnail cannot be null")
    private String thumbnail;
}
