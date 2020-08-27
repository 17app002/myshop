import dao.Item;
import dao.ItemDaoImpl;
import dbutil.DBUtil;
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
    public void testItem(){
        long millis=System.currentTimeMillis();

        java.sql.Date date=new java.sql.Date(millis);
        Item item=new Item("iphone充電線",599,5,null,"紅色");

        ItemDaoImpl itemDao=new ItemDaoImpl();
        if(itemDao.check(item)){
            System.out.println("已有重複產品");
        }else{
            itemDao.put(item);
        }

        System.out.println(new ItemDaoImpl().strToDate("2015-05-06"));
        System.out.println(item);


        //更新產品
        List<Item> items=itemDao.getAll();
        System.out.println(items.get(0));
        itemDao.update(items.get(0));
        for(Item i:items){
            System.out.println(i);
        }

    }
}
