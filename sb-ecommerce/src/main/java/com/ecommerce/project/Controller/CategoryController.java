package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.model.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/public/categories")
   // @RequestMapping(value = "/api/public/categories" ,method = RequestMethod.GET)
     public ResponseEntity<List<Category>> getAllCategories() {
         List<Category> categories =  categoryService.getAllCategories();
         return new ResponseEntity<>(categories,HttpStatus.OK);
     }


     @PostMapping("/admin/categories")
     public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
             categoryService.createCategory(category);
             return new ResponseEntity<>("Category added successfully.", HttpStatus.OK);

     }

     @DeleteMapping("/admin/categories/{categoryID}")
     public ResponseEntity<String> deleteCategory(@PathVariable Long categoryID) {
             String status = categoryService.deleteCategory(categoryID);
             return  new ResponseEntity<>("Category deleted successfully.", HttpStatus.OK);

     }

    @PutMapping ("/admin/categories/{categoryID}")
    public ResponseEntity<String>  updateCategory(@Valid @RequestBody Category category,
                                                  @PathVariable Long categoryID) {

             Category savedCategory = categoryService.updateCategory(category,categoryID);
             return new ResponseEntity<>("Category ID: " + categoryID + "  Updated successfully"
                     , HttpStatus.OK);

    }

}
