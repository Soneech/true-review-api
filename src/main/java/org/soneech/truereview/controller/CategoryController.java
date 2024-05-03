package org.soneech.truereview.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.soneech.truereview.dto.request.UpdateCategoryRequest;
import org.soneech.truereview.dto.response.review.CategoryResponse;
import org.soneech.truereview.exception.UpdateCategoryException;
import org.soneech.truereview.mapper.DefaultModelMapper;
import org.soneech.truereview.model.Category;
import org.soneech.truereview.service.CategoryService;
import org.soneech.truereview.util.ErrorsUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final DefaultModelMapper mapper;

    private final ErrorsUtil errorsUtil;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(mapper.convertToListWithCategoryResponse(categoryService.findAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getOneCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.convertToCategoryResponse(categoryService.findById(id)));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") Long id,
                                                           @RequestBody @Valid UpdateCategoryRequest request,
                                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UpdateCategoryException(HttpStatus.BAD_REQUEST,
                    errorsUtil.getFieldsErrors(bindingResult), "Некорректные данные категории");
        }
        Category updatedCategory = categoryService.updateCategory(mapper.convertToCategory(request, id));
        return ResponseEntity.ok(mapper.convertToCategoryResponse(updatedCategory));
    }
}
