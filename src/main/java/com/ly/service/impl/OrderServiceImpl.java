package com.ly.service.impl;

import com.ly.dao.OrderDao;
import com.ly.model.Order;
import com.ly.model.User;
import com.ly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public void genOrder(Order o) {
        if (o.getUser() == null) {
            throw new IllegalArgumentException("订单所属的客户信息没有");
        }
        if (o.getItems() == null || o.getItems().size() == 0) {
            throw new IllegalArgumentException("订单中没有订单项");
        }
        orderDao.save(o);
    }

    @Override
    public Order findOrderByNum(String ordernum) {
        return orderDao.findOrderByNum(ordernum);
    }

    @Override
    public List<Order> findUserOrders(User user) {
        return orderDao.findOrdersByUser(user.getId());
    }

    @Override
    public List<Order> findOrders() {
        // TODO Auto-generated method stub
        return orderDao.findOrders();
    }

    @Override
    public void faHuo(String ordernum) {
        // TODO Auto-generated method stub
        orderDao.faHuo(ordernum);
    }
}
