package dao;

import entity.Item;

import java.util.List;

public interface ItemDao {
    //更新商品
    public boolean update(Item item);

    //上架(需檢查是否有相同商品)
    public boolean add(Item item);

    //檢查商品(是否存在)
    public boolean check(Item item);

    //取得目前商品
    public List<Item> findAll();

    //依照id取商品
    public Item findById(int id);

}
