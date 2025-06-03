package com.example.demo.controller;


import com.example.demo.dto.CategoryRequestDTO;
import com.example.demo.dto.CategoryResponseDTO;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/status")
    public String status(){
        return "Category controller running";
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAll() {
        List<CategoryResponseDTO> category = categoryService.findAll();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long id) {
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO created = categoryService.createCategory(dto);
        return ResponseEntity
                .created(URI.create("/category/" + created.id()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
