package com.ecommerce.project.Service;

import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;

public interface ProductService {
    ProductDTO addProduct(Product product, Long categoryID);
}
