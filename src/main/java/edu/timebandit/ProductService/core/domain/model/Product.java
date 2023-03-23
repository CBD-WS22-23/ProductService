package edu.timebandit.ProductService.core.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UuidGenerator;

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
public class Product {

    @Id
    @Column(nullable = false, unique = true, columnDefinition = "uuid")
    @GeneratedValue (strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String watchID;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String price;

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