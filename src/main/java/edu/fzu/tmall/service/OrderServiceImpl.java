package edu.fzu.tmall.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.fzu.tmall.dao.OrderDAO;
import edu.fzu.tmall.dao.OrderItemDAO;
import edu.fzu.tmall.pojo.Order;
import edu.fzu.tmall.pojo.OrderItem;
import edu.fzu.tmall.pojo.User;
import edu.fzu.tmall.util.Page;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private OrderItemDAO orderItemDao;
    /**
     * 查找全部的订单项
     * @return
     */
    @Override
    public List<Order> findAll() {
        System.out.println(orderDAO);
        List<Order> orders = orderDAO.findAll();
        for (Order order : orders) {
            System.out.println(order);
        }
        return orders;
    }

    @Override
	public List listByPage(Page page) {
		// TODO 补充代码
        String hql = "from Order";
        List<Order> orders = orderDAO.findVO(hql, page.getStart()/page.getCount(), page.getCount());
        for (Order order : orders) {
            // 查询每个订单关联的订单项
            System.out.println(order);
            hql = "from OrderItem as o where o.order.id = " + order.getId();
            List<OrderItem> orderItems = orderItemDao.findVO(hql);
            order.setOrderItems(orderItems);
            float total = 0; // 总金额
            int totalNUmber = 0;
            // 计算金额和订单相格数
            for (OrderItem orderItem : orderItems) {
                total += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
                totalNUmber += orderItem.getNumber();
            }
            order.setTotal(total);
            order.setTotalNumber(totalNUmber);
        }
        return orders;
	}

	@Override
	public int total() {
		int count = 0;
		// TODO 补充代码
        count = Math.toIntExact(orderDAO.count("select count(0) from Order"));
		return count;
	}
	
	@Override
	public Order delivery(Integer orderId) {
		// TODO 补充代码
        Order order = orderDAO.get(Order.class, orderId);
        order.setDeliveryDate(new Date());
        order.setStatus("waitConfirm");
        return order;
    }

	@Override
	public float createOrder(Order order, List<OrderItem> ois) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> listByUserWithoutDelete(User user) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Order get(Integer id) {
        Order order = orderDAO.get(Order.class, id);
        return order;
    }

}
