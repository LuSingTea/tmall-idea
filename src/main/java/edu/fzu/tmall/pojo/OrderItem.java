package edu.fzu.tmall.pojo;

import javax.persistence.*;

@Entity
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // 订单项id

    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product; // 一个商品可以作为很多的订单项
    
    @ManyToOne
    @JoinColumn(name = "oid") // 一个订单有好多订单项
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;  // 一个用户有好多订单项

    private int number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}