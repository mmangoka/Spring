package com.ecommerce.project.Controller;


import com.ecommerce.project.Service.CategoryService;
import com.ecommerce.project.config.AppConfig;
import com.ecommerce.project.config.AppConstants;
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

    @GetMapping("/echo")
  public ResponseEntity<String>  echoMessage(@RequestParam(name = "message", defaultValue = "Hi World") String message){
      return new ResponseEntity<>("Echoed message:" +  message,HttpStatus.OK);
  }

    @GetMapping("/public/categories")
   // @RequestMapping(value = "/api/public/categories" ,method = RequestMethod.GET)
     public ResponseEntity<CategoryResponse> getAllCategories(
             @RequestParam(name = "pageNumber" ,defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
             @RequestParam(name = "pageSize" ,defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
             @RequestParam (name = "sortBy" ,defaultValue = AppConstants.SORT_CATEGORIES_BY,required = false) String sortBy,
             @RequestParam (name = "sortOrder",defaultValue = AppConstants.SORT_DIR,required = false) String sortOrder)
     {
         CategoryResponse categoryResponse =  categoryService.getAllCategories(pageNumber,pageSize,
                 sortBy,sortOrder);

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
