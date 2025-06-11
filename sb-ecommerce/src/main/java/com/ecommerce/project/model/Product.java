package com.ecommerce.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@ToString
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

    @ManyToOne //associate a product and a seller
    @JoinColumn(name = "seller_id")
    private User user;


}
