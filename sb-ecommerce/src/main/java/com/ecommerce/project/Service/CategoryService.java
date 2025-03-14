package com.ecommerce.project.Service;


import com.ecommerce.project.model.Category;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*interface to promote loose coupling*/
public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(@PathVariable Long categoryID);
}
