package edu.fzu.tmall.service;

import edu.fzu.tmall.pojo.Order;
import edu.fzu.tmall.pojo.OrderItem;
import edu.fzu.tmall.pojo.User;
import edu.fzu.tmall.util.Page;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();
	public List listByPage(Page page);
	public Order delivery(Integer orderId);
    public int total();
    public float createOrder(Order order, List<OrderItem> ois);
    public List<Order> listByUserWithoutDelete(User user);
    public Order get(Integer id);
}
