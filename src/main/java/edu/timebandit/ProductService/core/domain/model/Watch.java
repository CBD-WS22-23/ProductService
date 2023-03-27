package edu.timebandit.ProductService.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Builder
public class Watch {

    @Id
    @Column(nullable = false, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    @ElementCollection
    private Map<String, String> generalInfo;

    @ElementCollection
    private Map<String, String> housingInfo;

    @ElementCollection
    private List<String> features;

    @Column(nullable = false)
    private int stock;

    private int inCart;

    @ElementCollection
    private List<String> imageLinks;

    private String brand;

}