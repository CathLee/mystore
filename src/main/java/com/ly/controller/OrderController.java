package com.ly.controller;

import com.ly.model.*;
import com.ly.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    public OrderService orderService;
    //发货
    @RequestMapping(value = "faHuo&ordernum={ordernum}",method = RequestMethod.GET)
    public String faHuo(@PathVariable String ordernum, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.faHuo(ordernum);
        List<Order> orders = orderService.findOrders();
        HttpSession session = req.getSession();
        session.setAttribute("orders", orders);
        System.out.println(orders);
        return "managerOrder_01";
    }

    //显示订单页
    @RequestMapping(value = "findOrders",method = RequestMethod.GET)
    public String findOrders(HttpSession session){
        List<Order> orders = orderService.findOrders();
        session.setAttribute("orders", orders);
        return "managerOrder_01";
    }

    //结算
    @RequestMapping(value = "genOrder",method = RequestMethod.GET)
    public String genOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 取出购物车信息
        // 取出购物项信息
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        if (cart == null) {
            resp.getWriter().write("会话已经结束！！");
            return "index_01";
        }
        Order order = new Order();
        order.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String ordernum = genOrdernum();
        order.setOrdernum(ordernum);
        order.setQuantity(cart.getTotalQuantity());
        order.setMoney(cart.getTotalMoney());
        order.setUser(user);
        // 订单项
        List<Orderitem> oItems = new ArrayList<Orderitem>();
        for (Map.Entry<String, CartItem> me : cart.getItmes().entrySet()) {
            CartItem cItem = me.getValue();
            Orderitem oItem = new Orderitem();
            oItem.setId(genOrdernum());
            oItem.setBook(cItem.getBook());
            oItem.setPrice(cItem.getMoney());
            oItem.setQuantity(cItem.getQuantity());
            oItem.setOrdernum(ordernum);
            oItems.add(oItem);
        }
        // 建立订单项和订单的关系
        order.setItems(oItems);
        System.out.println("order is :"+order);
        orderService.genOrder(order);
        session.setAttribute("order", order);
        session.removeAttribute("cart");
        return "order";
    }
    //生成订单号
    private String genOrdernum() {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s1 = df.format(now);
        return s1 + System.nanoTime();
    }
    @RequestMapping(value = "findAllOrders",method = RequestMethod.GET)
    private String findAllOrders(HttpServletRequest req, HttpServletResponse resp)  {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orders = orderService.findUserOrders(user);
        req.setAttribute("orders", orders);
        return "personOrder";
    }

}
