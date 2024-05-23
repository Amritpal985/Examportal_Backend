package com.exam.examserver.controller;

import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?>addCategory(@RequestBody Category category){
        Category category2=this.categoryService.addCategory(category);
        return ResponseEntity.ok(category2);
    }
    // get category
    @GetMapping("/{cId}")
    public Category getCategory(@PathVariable("cId") Long cId)
    {
        return this.categoryService.getCategory(cId);
    } 

    @GetMapping("/")
    public ResponseEntity<?> getCategories()
    {
        return ResponseEntity.ok(this.categoryService.getCategories());
    } 

    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return this.categoryService.updateCategory(category);
    }

    // delete category
    @DeleteMapping("/{cId}")
    public void deleteCategory(@PathVariable("cId") Long cId)
    {
        this.categoryService.deleteCategory(cId);
    }

    

    
}
