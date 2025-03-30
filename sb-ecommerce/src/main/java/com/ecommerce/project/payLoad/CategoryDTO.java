package com.ecommerce.project.payLoad;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*request object*/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    private Long categoryID;
    private String categoryName;


}
