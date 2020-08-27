import dao.Customer;
import dao.CustomerDaoImpl;
import dao.Item;
import dao.ItemDaoImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/***
 * 顧客頁面
 */
public class ItemPage {

    private Customer customer;

    public ItemPage(Customer customer) {
        this.customer = customer;
        if(customer.getRole()==0){
            layout();
        }else {
            System.out.println("管理者介面");
            addItemLayout();
        }
    }

    public void layout() {
        System.out.println("\t\t產品頁面");
        int select = 0;
        ItemDaoImpl itemDao = new ItemDaoImpl();
        //取得目前品項
        List<Item> items = itemDao.getAll();

        while (true) {
            int count = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("====================================================================");
            System.out.println("編號\t\t商品名稱\t\t價格\t\t數量");
            for (Item item : items) {
                count++;
                System.out.println(count + "\t\t" + item.getName() + "\t\t" + item.getPrice() + "\t\t" + item.getQty());
            }
            System.out.println(customer.getName() + "(" + customer.getMoney() + ")" + "請問要購買哪個品項呢(-1:離開)?");
            try {
                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("選項輸入錯誤，請重新輸入....");
                continue;
            }
            if (select == -1) {
                System.out.println("離開商品頁面.");
                break;
            }
            if (select < 1 || select > count) {
                System.out.println("選項輸入錯誤，請重新輸入....");
                continue;
            }
            Item item = items.get(select - 1);

            if (customer.getMoney() - item.getPrice() < 0) {
                System.out.println("金額不夠");
                continue;
            }

            if (item.getQty() <= 0) {
                System.out.println(item.getName() + " 商品已無庫存");
                continue;
            }

            //進行購買更新庫存動作
            if (itemDao.update(item)) {
                //增加到訂單頁面
                new OrderPage(item);
                System.out.println("商品訂購成功:" + item.getName());
                //System.out.println("按任一鍵繼續........");
                //String key=scanner.nextLine();

                //更新顧客資料
                int money = (int) ((float) customer.getMoney() - item.getPrice());
                customer.setMoney(money);
                new CustomerDaoImpl().update(customer);

                //更新商品列表
                items = itemDao.getAll();
            }
        }
    }


    public void showItem() {
        ItemDaoImpl itemDao = new ItemDaoImpl();
        //取得目前品項
        List<Item> items = itemDao.getAll();
        int count = 0;
        System.out.println("====================================================================");
        System.out.println("編號\t\t商品名稱\t\t價格\t\t數量");
        for (Item item : items) {
            count++;
            System.out.println(count + "\t\t" + item.getName() + "\t\t" + item.getPrice() + "\t\t" + item.getQty());
        }
    }

    /**
     * 增加商品畫面
     */
    public void addItemLayout() {

        showItem();
        while (true) {
            Item item = getItem();
            if (item == null) {
                System.out.println("新增商品完畢..");
                break;
            }
            if (item.getName() == null) {
                System.out.println("重新輸入資料");
                continue;
            }

            ItemDaoImpl itemDao = new ItemDaoImpl();

            if (itemDao.check(item)) {
                System.out.println("商品重複...");
                continue;
            }

            itemDao.add(item);
            System.out.println("商品新增成功!");
            showItem();
        }
    }


    public Item getItem() {
        System.out.println("===============新增商品===============");
        Scanner scanner = new Scanner(System.in);
        System.out.println("商品名稱:(-1:exit)");
        String name = scanner.nextLine();

        if (name.equals("-1")) {
            return null;
        }

        try {
            System.out.println("商品價格:");
            float price = scanner.nextFloat();
            System.out.println("商品數量:");
            int qty = scanner.nextInt();
            System.out.println("商品備註:");
            String info = scanner.next();

            return new Item(name, price, qty, null, info);

        } catch (InputMismatchException ex) {
            System.out.println("輸入資料錯誤，請重新輸入");
        }

        //輸入有錯誤情況(回傳空的item)
        return new Item();
    }
}
