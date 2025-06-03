package com.example.demo.services;

import com.example.demo.dto.CategoryRequestDTO;
import com.example.demo.dto.CategoryResponseDTO;
import com.example.demo.entities.Category;
import com.example.demo.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDTO::new)
                .toList();
    }

    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        return new CategoryResponseDTO(category);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        Category saved = categoryRepository.save(category);
        return new CategoryResponseDTO(saved);
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        category.setName(dto.name());
        category.setDescription(dto.description());

        Category updatedCategory = categoryRepository.save(category);

        return new CategoryResponseDTO(updatedCategory);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        categoryRepository.delete(category);
    }
}
