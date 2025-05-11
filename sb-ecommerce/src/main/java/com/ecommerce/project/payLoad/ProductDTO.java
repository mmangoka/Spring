package com.ecommerce.project.payLoad;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productID;
    private String productName;
    private String productDecsription;
    private String image;
    private  Integer quantity;
    private double discount;
    private Double productPrice;
    private Double specialProductPrice;


}
