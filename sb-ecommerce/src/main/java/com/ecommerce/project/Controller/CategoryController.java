package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
     public ResponseEntity<String> createCategory(@RequestBody Category category){
         try {
             categoryService.createCategory(category);
             return new ResponseEntity<>("Category added successfully.", HttpStatus.OK);
         } catch (Exception e) {
             return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @DeleteMapping("/admin/categories/{categoryID}")
     public ResponseEntity<String> deleteCategory(@PathVariable Long categoryID) {
         try {
             String status = categoryService.deleteCategory(categoryID);
             return  ResponseEntity.ok(status);
         }catch(ResponseStatusException e){
             return new ResponseEntity<>(e.getReason(),e.getStatusCode());
         }
     }

    @PutMapping ("/admin/categories/{categoryID}")
    public ResponseEntity<String>  updateCategory(@RequestBody Category category,
                                                  @PathVariable Long categoryID) {
         try {
             Category savedCategory = categoryService.updateCategory(category,categoryID);
             return new ResponseEntity<>("Category ID: " + categoryID + "  Updated successfully"
                     , HttpStatus.OK);
         }catch(ResponseStatusException e)
         {
             return new ResponseEntity<>(e.getReason(),e.getStatusCode());
         }
    }

}
