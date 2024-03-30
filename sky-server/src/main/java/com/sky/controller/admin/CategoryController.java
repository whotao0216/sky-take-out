package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags="菜品分类接口")
@Slf4j
@RequestMapping("/admin/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("新增套餐分类")
    @PostMapping
    public Result add(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增套餐分类：{}",categoryDTO);
        categoryService.add(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页分类查询")
    public Result<PageResult> pageQueryCategory(CategoryPageQueryDTO categoryPageQueryDTO) {

        log.info("分类分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQueryCategory(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查询套餐信息")
    public Result<Category> getCategoryById(@PathVariable Integer id) {
        log.info("根据id查询套餐信息:{}", id);
        Category category= categoryService.getCategoryById(id);
        return Result.success(category);
    }

    @PutMapping
    @ApiOperation("更改套餐信息")
    public Result editCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("更改套餐信息:{}",categoryDTO);
        categoryService.editCategory(categoryDTO);
        return Result.success();
    }
    @PostMapping("/status/{status}")
    @ApiOperation("更改套餐状态信息")
    public Result updateCategoryStatus(@PathVariable Integer status,Long id){
        log.info("更改套餐状态信息:{},{}", status, id);
        categoryService.updateCategoryStatus(status, id);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("根据id删除套餐")
    public Result deleteCategoryById(Long id){
        log.info("根据id删除套餐:{}", id);
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

    @ApiOperation("根据类型查找分类")
    @GetMapping("/list")
    public Result<List<Category>> listResult(Integer type){
        log.info("根据类型查找分类:{}", type);
        List<Category> list= categoryService.list(type);
        return Result.success(list);
    }
}
