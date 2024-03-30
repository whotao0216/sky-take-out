package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    void insert(List<OrderDetail> orderDetailList);
    @Select("select *from sky_take_out.order_detail where order_detail.order_id=#{orderId}")
    List<OrderDetail> getByOrderId(Long orderId);
}
