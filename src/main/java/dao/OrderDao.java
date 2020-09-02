package dao;

import entity.Item;
import entity.Order;

import java.util.List;

public interface OrderDao {
    //更新訂單
    public boolean update(Order order);

    //新增訂單
    public boolean add(Order order);

    //取得目前所有訂單
    public List<Order> findAll();

    //依照id取商品
    public Order findById(int id);

    //依照 customer id取商品
    public List<Order> findByCustomerId(int customerId);

    //依照 item id取商品
    public List<Order> findByItemId(int itemId);

}
