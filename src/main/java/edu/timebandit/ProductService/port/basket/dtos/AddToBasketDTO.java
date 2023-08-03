package edu.timebandit.ProductService.port.basket.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToBasketDTO {
    @NotBlank(message = "Basket id cannot be blank")
    private String basketId;
    @Valid
    private BasketWatchDTO watch;
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}
