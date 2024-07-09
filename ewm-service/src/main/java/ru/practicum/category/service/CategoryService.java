package ru.practicum.category.service;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {
    @Transactional
    CategoryDto createCategory(NewCategoryDto newCategoryDto);

    @Transactional
    CategoryDto updateCategory(CategoryDto categoryDto, Long catId);

    @Transactional
    void deleteCategory(Long catId);

    @Transactional(readOnly = true)
    List<CategoryDto> getCategories(Integer from, Integer size);

    @Transactional(readOnly = true)
    CategoryDto getOneCategoryDto(Long catId);
}
