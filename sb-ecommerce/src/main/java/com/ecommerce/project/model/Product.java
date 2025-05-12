package com.ecommerce.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    //can add validations here


    @NotBlank
    @Size(min = 3,message= "ProductName must contain atleast 3 characters.")
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
