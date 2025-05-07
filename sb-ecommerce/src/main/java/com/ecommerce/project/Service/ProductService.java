package com.ecommerce.project.Service;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.payLoad.ProductResponse;

public interface ProductService {
    ProductDTO addProduct(Product product, Long categoryID);

    ProductResponse getAllProducts();

    ProductResponse getProductsByCategory(Long categoryID);
}
