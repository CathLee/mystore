package com.ly.service;

import com.ly.model.Order;
import com.ly.model.User;

import java.util.List;

public interface OrderService {
    void genOrder(Order o);
    Order findOrderByNum(String ordernum);
    List<Order> findUserOrders(User user);
    List<Order> findOrders();

    void faHuo(String ordernum);
}
