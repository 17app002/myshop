import dao.Customer;
import dao.CustomerDaoImpl;
import dao.Item;
import dao.ItemDaoImpl;
import dbutil.DBUtil;
import dbutil.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class dbtest {

    @Test
    public void test(){
        DBUtil dbUtil = new DBUtil("myshop", "root", "password");
        if (dbUtil.isConnection()) {
            System.out.println("資料庫連結成功！");
        }
    }

    @Test
    public void test1(){
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        //登入
        Customer customer = customerDao.login("mandy", "mandy");
        System.out.println(customer);
        customer = new Customer("張三", "12345678", "0981765789", 8000);
        //註冊
        if (!customerDao.check(customer)) {
            boolean success = customerDao.register(customer);
            if (success) {
                System.out.println("註冊成功!");
            }
        } else {
            System.out.println("用戶已存在!");
        }
    }

    @Test
    public void showItems(){
        Customer customer=new CustomerDaoImpl().login("mandy","mandy");
        new ItemPage(customer);
    }

    @Test
    public void itemPageTest(){

        Customer customer=new CustomerDaoImpl().login("mandy","mandy");
        new ItemPage(customer);
    }

    @Test
    public void ItemTest(){
        long millis=System.currentTimeMillis();

        java.sql.Date date=new java.sql.Date(millis);
        Item item=new Item("iphone充電線",599,5,null,"紅色");

        ItemDaoImpl itemDao=new ItemDaoImpl();
        if(itemDao.check(item)){
            System.out.println("已有重複產品");
        }else{
            itemDao.add(item);
        }

        System.out.println(JDBCUtil.strToDate("2015-05-06"));
        System.out.println(item);


        //更新產品
        List<Item> items=itemDao.findAll();
        System.out.println(items.get(0));
        itemDao.update(items.get(0));
        for(Item i:items){
            System.out.println(i);
        }

    }
}
