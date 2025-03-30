package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payLoad.CategoryDTO;
import com.ecommerce.project.payLoad.CategoryResponse;
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
     public ResponseEntity<CategoryResponse> getAllCategories() {
         CategoryResponse categoryResponse =  categoryService.getAllCategories();

         return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
     }


     @PostMapping("/admin/categories")
     public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
             CategoryDTO createcategoryDTO = categoryService.createCategory(categoryDTO);
             return new ResponseEntity<>(createcategoryDTO, HttpStatus.OK);

     }

     @DeleteMapping("/admin/categories/{categoryID}")
     public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryID) {
             CategoryDTO categoryDTO = categoryService.deleteCategory(categoryID);
             return  new ResponseEntity<>(categoryDTO, HttpStatus.OK);

     }

    @PutMapping ("/admin/categories/{categoryID}")
    public ResponseEntity<CategoryDTO>  updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                  @PathVariable Long categoryID) {

             CategoryDTO savedCategoryDB = categoryService.updateCategory(categoryDTO,categoryID);
             return new ResponseEntity<>(savedCategoryDB    , HttpStatus.OK);

    }

}
