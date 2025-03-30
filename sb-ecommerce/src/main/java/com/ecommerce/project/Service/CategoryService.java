package com.ecommerce.project.Service;


import com.ecommerce.project.model.Category;
import com.ecommerce.project.payLoad.CategoryDTO;
import com.ecommerce.project.payLoad.CategoryResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*interface to promote loose coupling*/
public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer PageSize,String sortBy,String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(@PathVariable Long categoryID);
    CategoryDTO updateCategory(CategoryDTO categoryDTO,@PathVariable Long categoryID);
}
