package com.sky.service.impl;

import com.sky.dto.GoodsSalesDTO;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * @param begin
     * @param end
     * @return
     */
    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while (!begin.equals(end)) {
            //计算日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        String dateListString = StringUtils.join(dateList, ",");

        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : dateList) {
            LocalDateTime minTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime maxTime = LocalDateTime.of(date, LocalTime.MAX);
            HashMap hashMap = new HashMap();
            hashMap.put("begin", minTime);
            hashMap.put("end", maxTime);
            hashMap.put("status", Orders.COMPLETED);
            Double turnover = orderMapper.sumByMap(hashMap);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }
        String turnoverListString = StringUtils.join(turnoverList, ",");

        return new TurnoverReportVO(dateListString, turnoverListString);
    }

    @Override
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> localDateList = new ArrayList<>();
        List<Long> totalUserList = new ArrayList<>();

        while (!begin.equals(end)) {
            //计算日期
            begin = begin.plusDays(1);
            localDateList.add(begin);
            //统计总用户人数
            LocalDateTime maxTime = LocalDateTime.of(begin, LocalTime.MAX);
            Long userCount = userMapper.sumByTime(null, maxTime);
            totalUserList.add(userCount);
        }
        String localDateListString = StringUtils.join(localDateList, ',');
        String totalUserListString = StringUtils.join(totalUserList, ',');
        List<Long> newUserList = new ArrayList<>();
        for (LocalDate localDate : localDateList) {
            //统计新增用户
            LocalDateTime min = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime max = LocalDateTime.of(localDate, LocalTime.MAX);
            Long userCount = userMapper.sumByTime(min, max);
            userCount = userCount == null ? 0L : userCount;
            newUserList.add(userCount);
        }
        String newUserListString = StringUtils.join(newUserList, ',');

        return new UserReportVO(localDateListString, totalUserListString, newUserListString);
    }

    @Override
    public OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while (!begin.equals(end)) {
            //计算日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        String dateListString = StringUtils.join(dateList, ",");

        ArrayList<Integer> totalOrderList = new ArrayList<>();
        ArrayList<Integer> validOrderCountList = new ArrayList<>();
        for (LocalDate date : dateList) {
            //查询订单总数
            HashMap map = new HashMap();
            LocalDateTime minTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime maxTime = LocalDateTime.of(date, LocalTime.MAX);
            map.put("begin", minTime);
            map.put("end", maxTime);
            Integer orderCount = orderMapper.countByMap(map);
            orderCount=orderCount==null?0:orderCount;
            totalOrderList.add(orderCount);
            map.put("status", Orders.COMPLETED);
            Integer completeOrder = orderMapper.countByMap(map);
            completeOrder=completeOrder==null?0:completeOrder;
            validOrderCountList.add(completeOrder);
        }
        String totalOrderListString = StringUtils.join(totalOrderList, ',');
        String validOrderListString = StringUtils.join(validOrderCountList, ',');

        Integer totalOrder=orderMapper.countByMap(null);
        Integer validOrder=orderMapper.countStatus(Orders.COMPLETED);
        double rate= ((double) validOrder /totalOrder);

        return new OrderReportVO(dateListString,totalOrderListString,validOrderListString,totalOrder,validOrder,rate);
    }

    @Override
    public SalesTop10ReportVO getTop10(LocalDate begin, LocalDate end) {
        LocalDateTime minTime = LocalDateTime.of(begin, LocalTime.MIN);
        LocalDateTime maxTime = LocalDateTime.of(end, LocalTime.MAX);
        List<GoodsSalesDTO> salesTop = orderMapper.getSalesTop(minTime, maxTime);
        List<String> nameList = salesTop.stream().map(GoodsSalesDTO::getName).collect(Collectors.toList());
        List<Integer> numberList = salesTop.stream().map(GoodsSalesDTO::getNumber).collect(Collectors.toList());
        return new SalesTop10ReportVO(StringUtils.join(nameList, ","),StringUtils.join(numberList, ","));
    }
}
