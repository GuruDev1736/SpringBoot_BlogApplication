package com.Guruprasad.Blog.Controller;


import com.Guruprasad.Blog.Model.Category;
import com.Guruprasad.Blog.PayLoad.CategoryDTO;
import com.Guruprasad.Blog.Service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService ;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // build add category rest api

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> addcategory(@RequestBody CategoryDTO categoryDTO )
    {
        CategoryDTO savecat = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(savecat, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getcategory (@PathVariable("id") Long categoryID)
    {
        CategoryDTO categoryDTO = categoryService.getcategory(categoryID);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getallcategory()
    {
        return new ResponseEntity<>(categoryService.getallcategory(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updatecategory (@RequestBody CategoryDTO categoryDTO ,@PathVariable("id") Long id)
    {
        CategoryDTO update = categoryService.updatecategory(categoryDTO,id);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>deletecategory( @PathVariable("id") Long id)
    {
        categoryService.deletecategory(id);
        return new ResponseEntity<>("The Following category with id :"+id+" has deleted",HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>deleteall()
    {
        categoryService.deleteall();
        return new ResponseEntity<>("The all categories has deleted",HttpStatus.OK);
    }

}
