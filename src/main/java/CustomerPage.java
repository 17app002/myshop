import dao.Customer;
import dao.CustomerDaoImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

/***
 * 顧客頁面
 */
public class CustomerPage {

    public CustomerPage() {
        layout();
    }

    public void layout() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t顧客登入頁面");

        int select = 0;
        while (true) {
            try {
                System.out.println("[1]登入 [2]註冊 [3]離開");
                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("請輸入正確數字");
            }

            if (select < 1 && select > 3) {
                System.out.println("請重新輸入....");
                return;
            } else if (select == 3) {
                System.out.println("返回上一頁");
                return;
            }
            System.out.println("==============================");
            System.out.println(select == 1 ? "登入" : "用戶註冊");
            System.out.println("==============================");
            //輸入的用戶
            Customer temp = getCustomer(select);
            CustomerDaoImpl customerDao = new CustomerDaoImpl();
            //取得實際用戶
            Customer customer = null;
            //登入
            if (select == 1) {
                customer = customerDao.login(temp.getName(), temp.getPassword());
                if (customer != null) {
                    System.out.println("登入成功!");
                    System.out.println("轉到商品介面.....");
                    new ItemPage(customer);
                    return;
                }
                System.out.println("登入失敗...");
                //註冊
            } else if (select == 2) {
                customer = temp;
                if (!customerDao.check(customer)) {
                    boolean success = customerDao.register(customer);
                    if (success) {
                        System.out.println("註冊成功!");
                        System.out.println("轉到商品介面.....");
                        new ItemPage(customer);
                        return;
                    }
                }

                System.out.println("用戶已存在!");
            }
        }
    }

    public Customer getCustomer(int select) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入帳號:");
        String name = scanner.next();
        System.out.println("請輸入密碼:");
        String password = scanner.next();
        String phone = "";
        //新用戶註冊送300
        int money = 300;
        //註冊用
        if (select == 2) {
            System.out.println("請輸入電話:");
            phone = scanner.next();
        }

        Customer customer = new Customer(name, password, phone, money);

        return customer;
    }
}
