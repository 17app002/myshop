package dao;

import java.sql.Date;

/***
 * 商品類別
 */
public class Item {
    private int id;
    private String name;
    private float price;
    private int qty;
    private Date create_date;
    private String info;

    public Item() {
    }

    public Item(String name, float price, int qty, Date create_date, String info) {
        this.id = 0;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.create_date = create_date;
        this.info = info;
    }

    public Item(int id, String name, float price, int qty, Date create_date, String info) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.create_date = create_date;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", create_date='" + create_date + '\'' +
                ", info='" + info + '\'' +
                '}';
    }


}
