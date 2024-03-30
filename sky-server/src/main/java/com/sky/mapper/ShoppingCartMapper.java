package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    List<ShoppingCart> list(ShoppingCart shoppingCart);

    void update(ShoppingCart shoppingCart);

    void insert(ShoppingCart shoppingCart);

    void delete(ShoppingCart shoppingCart);

    @Delete("delete from sky_take_out.shopping_cart where shopping_cart.user_id=#{userId}")
    void deleteAll(Long userId);

    void insertAll(List<ShoppingCart> shoppingCartList);
}
