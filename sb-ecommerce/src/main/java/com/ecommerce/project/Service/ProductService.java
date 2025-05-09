package com.ecommerce.project.Service;

import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.payLoad.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(ProductDTO product, Long categoryID);

    ProductResponse getAllProducts();


    ProductResponse getProductsByCategory(Long categoryId);

    ProductResponse searchProductByKeyword(String keyword);

    ProductDTO updateProduct(Long productID, ProductDTO product );

    ProductDTO deleteProduct(Long productID);
}
