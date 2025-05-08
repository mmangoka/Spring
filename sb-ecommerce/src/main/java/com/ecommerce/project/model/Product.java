package com.ecommerce.project.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;


    private String productName;
    private String productDescription;
    private Integer quantity;
    private double price;
    private double discount;
    private double specialProductPrice;
    private String image;


    @ManyToOne
    @JoinColumn(name = "categoryID")
    private Category category;


}
