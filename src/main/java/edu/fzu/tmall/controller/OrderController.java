package edu.fzu.tmall.controller;


import edu.fzu.tmall.pojo.Order;
import edu.fzu.tmall.service.OrderService;
import edu.fzu.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 列出所有的订单项
     *
     * @param model
     * @return
     */
    @RequestMapping("/listOrder")
    public String listOrder(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/listOrder";
    }

    @RequestMapping("/listOrderByPage")
    public String listOrderByPage(@RequestParam(value = "start", required = false) Integer start,
                                  Model model) {
        if (start == null || start == 0) {
            start = 0;
        }
        System.out.println(start);
        Page page = new Page(start, 10);
        int total = orderService.total();
        page.setTotal(total);
        List<Order> orders = orderService.listByPage(page);
        Order order = orders.get(0);
        if (order.getOrderItems() != null) {
            System.out.println("这是orderItems" + order.getOrderItems());
        }
        model.addAttribute("orders", orders);
        model.addAttribute("page", page);
        return "admin/listOrder";
    }

    @RequestMapping("/getDetails/{id}")
    public String getDetails(@PathVariable("id") Integer id, Model model) {
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        return "admin/listOrder";
    }


    @RequestMapping(value = "/delivery")
    public String deliver(@RequestParam("id") Integer id,
                          Model model) {
        System.out.println("订单id为:" + id);
        orderService.delivery(id);
        return "admin/listOrder";
    }

}