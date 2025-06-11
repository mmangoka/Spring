package com.ecommerce.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;

    @NotBlank
    @Size(min = 5,message = "street name must be atleast 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5,message = "building name must be atleast 5 characters")
    private String buildingName;


    @NotBlank
    @Size(min = 4,message = "city name must be atleast 4 characters")
    private String city;


    @NotBlank
    @Size(min = 2,message = "Country name must be atleast 2 characters")
    private String country;


    @NotBlank
    @Size(min = 4,message = "state name must be atleast 4 characters")
    private String state;

    @NotBlank
    @Size(min = 6,message = "pincode  must be atleast 6 characters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> user  = new ArrayList<>();


    public Address(Long addressID, String street, String buildingName, String city, String country, String state, String pincode) {
        this.addressID = addressID;
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.country = country;
        this.state = state;
        this.pincode = pincode;
    }

}
