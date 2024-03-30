package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    void add(Category category);

    Page<Category> pageQueryCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    Category getCategoryById(Integer id);

    void updateCategory(Category category);

    void deleteCategoryById(Long id);

    List<Category> list(Integer type);
}
