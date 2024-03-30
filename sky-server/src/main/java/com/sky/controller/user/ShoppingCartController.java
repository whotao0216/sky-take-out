package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "购物车接口")
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.add(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("list")
    public Result<List<ShoppingCart>> list() {
        List<ShoppingCart> result = shoppingCartService.list();
        return Result.success(result);
    }

    @PostMapping("/sub")
    public Result subItem(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.subItem(shoppingCartDTO);
        return Result.success();
    }

    @DeleteMapping("/clean")
    public Result cleanShoppingCart() {
        shoppingCartService.clean();
        return Result.success();
    }
}
