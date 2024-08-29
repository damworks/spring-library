package com.damworks.library.controller;

import com.damworks.library.model.Category;
import com.damworks.library.model.Subcategory;
import com.damworks.library.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping
    public List<Subcategory> getAllSubcategories() {
        return subcategoryService.getAllSubcategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subcategory> getSubcategoryById(@PathVariable Long id) {
        Optional<Subcategory> subcategory = subcategoryService.getSubcategoryById(id);
        return subcategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public List<Subcategory> getSubcategoriesByCategory(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return subcategoryService.getSubcategoriesByCategory(category);
    }

    @PostMapping
    public ResponseEntity<Subcategory> createSubcategory(@RequestBody Subcategory subcategory) {
        Subcategory createdSubcategory = subcategoryService.createSubcategory(subcategory);
        return new ResponseEntity<>(createdSubcategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subcategory> updateSubcategory(@PathVariable Long id, @RequestBody Subcategory subcategoryDetails) {
        Optional<Subcategory> subcategoryOptional = subcategoryService.getSubcategoryById(id);
        if (subcategoryOptional.isPresent()) {
            Subcategory subcategory = subcategoryOptional.get();
            subcategory.setSubcategoryName(subcategoryDetails.getSubcategoryName());
            subcategory.setCategory(subcategoryDetails.getCategory());
            subcategoryService.updateSubcategory(subcategory);
            return ResponseEntity.ok(subcategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
        return ResponseEntity.noContent().build();
    }
}
