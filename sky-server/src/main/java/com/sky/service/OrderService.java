package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    void repetition(Long id);

    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);


    PageResult pageQuery4User(Integer page, Integer pageSize, Integer status);

    void cancelOrderById(Long id);

    OrderVO orderDetail(Long id);

    void reminder(Integer id);

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    PageResult pageQuery4Admin(OrdersPageQueryDTO ordersPageQueryDTO);

    void orderRejection(OrdersRejectionDTO ordersRejectionDTO);

    void orderCancel(OrdersCancelDTO ordersCancelDTO);

    void delivery(Long id);

    void complete(Long id);

    OrderStatisticsVO statistics();
}
