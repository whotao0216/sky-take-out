package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {
    void add(CategoryDTO categoryDTO);

    PageResult pageQueryCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    Category getCategoryById(Integer id);

    void editCategory(CategoryDTO categoryDTO);

    void updateCategoryStatus(Integer status, Long id);

    void deleteCategoryById(Long id);

    List<Category> list(Integer type);
}
