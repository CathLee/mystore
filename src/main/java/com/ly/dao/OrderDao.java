package com.ly.dao;

import com.ly.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    /**
     * 保存订单的基本信息 还要保存订单关联的订单项信息
     */
    void save(@Param("o") Order o);

    /**
     * 根据订单号查找订单
     *
     */
    Order findOrderByNum(@Param("ordernum") String ordernum);

    /**
     * 根据客户的id查询订单，按照日期排序排列
     *
     */
    List<Order> findOrdersByUser(@Param("userId") String userId);

    List<Order> findOrders();

    void faHuo(@Param("ordernum") String ordernum);

}
