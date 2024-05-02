package org.soneech.truereview.controller;

import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.response.review.CategoryResponse;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final DefaultModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(mapper.convertToListWithCategoryResponse(categoryService.getAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getOneCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.convertToCategoryResponse(categoryService.getCategoryById(id)));
    }
}
