package org.soneech.truereview.service;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.exception.CategoryNotFoundException;
import org.soneech.truereview.model.Category;
import org.soneech.truereview.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public boolean existsById(long id) {
        return categoryRepository.existsById(id);
    }
}
