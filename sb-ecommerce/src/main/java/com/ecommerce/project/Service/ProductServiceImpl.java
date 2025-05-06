package com.ecommerce.project.Service;


import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payLoad.ProductDTO;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public ProductDTO addProduct(Product product, Long categoryID) {


        Category category = categoryRepository.findById(categoryID).
                orElseThrow(()-> new ResourceNotFoundException("Category","categoryID","Category not found"))   ;

        product.setCategory(category);
        double specialPrice = product.getProductPrice() -
                ((product.getDiscount() * 0.01) * product.getProductPrice());

        product.setImage("default.png");
        Product savedProduct = productRepository.save(product);
        product.setSpecialProductPrice(specialPrice);


        return mapper.map(savedProduct,ProductDTO.class);
    }
}
