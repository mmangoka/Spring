package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

     @GetMapping("/api/public/categories")
     public List<Category> getAllCategories() {

         return categoryService.getAllCategories();
     }


     @PostMapping("/api/public/categories")
     public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "Category added successfully.";
     }

     @DeleteMapping("/api/admin/categories/{categoryID}")
     public String deleteCategory(@PathVariable Long categoryID) {
         String status = categoryService.deleteCategory(categoryID);
         return status;
     }
}
