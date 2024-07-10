package ru.practicum.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.dto.NewCategoryDto;
import ru.practicum.category.mapper.CategoryMapper;
import ru.practicum.category.model.Category;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.event.repository.EventRepository;
import ru.practicum.exception.ConflictException;
import ru.practicum.exception.NotFoundException;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryDto createCategory(NewCategoryDto newCategoryDto) {
        Category newCategory = categoryMapper.toCategoryFromNewCategoryDto(newCategoryDto);
        Category createdCategory = categoryRepository.save(newCategory);

        return categoryMapper.toCategoryDto(createdCategory);
    }

    @Transactional
    @Override
    public void deleteCategory(Long catId) {
        checkExistsCategory(catId);
        getExceptionIfEventsAssociateWithCategory(catId);

        categoryRepository.deleteById(catId);
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long catId) {
        checkExistsCategory(catId);

        categoryDto.setId(catId);
        Category category = categoryMapper.toCategoryFromCategoryDto(categoryDto);
        Category updatedCategory = categoryRepository.saveAndFlush(category);

        return categoryMapper.toCategoryDto(updatedCategory);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Category> allCategories = categoryRepository.findAll(pageable).getContent();

        return categoryMapper.toCategoryDtoList(allCategories);
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDto getOneCategoryDto(Long catId) {
        return categoryMapper.toCategoryDto(categoryRepository.findById(catId).orElseThrow(() ->
                new NotFoundException("Category with id=" + catId + " was not found",
                        Collections.singletonList("Category id does not exist"))));
    }

    private void checkExistsCategory(Long catId) {
        if (!categoryRepository.existsById(catId)) {
            throw new NotFoundException("Category with id=" + catId + " was not found",
                    Collections.singletonList("Category id does not exist"));
        }
    }

    private void getExceptionIfEventsAssociateWithCategory(Long catId) {
        if (eventRepository.existsByCategory_Id(catId)) {
            throw new ConflictException("The category is not empty",
                    Collections.singletonList("No events should be associated with the category"));
        }
    }
}
