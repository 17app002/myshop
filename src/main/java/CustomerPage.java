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
        int select = 0;
        while (true) {
            try {
                System.out.println("\n\t商店管理系統 v1.0");
                System.out.println("***>>顧客登入介面**************");
                System.out.println("[1]登入");
                System.out.println("[2]註冊");
                System.out.println("[3]離開");
                System.out.println("*******************************");

                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("請輸入正確數字");
            }

            if (select < 1 && select > 3) {
                System.out.println("請重新輸入....");
                continue;
            } else if (select == 3) {
                System.out.println("返回上一頁");
                break;
            }



            System.out.println("===============================");
            System.out.println(select == 1 ? "登入" : "用戶註冊");
            System.out.println("===============================");
            //輸入的用戶
            Customer temp = getInputCustomer(select);
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

    public Customer getInputCustomer(int select) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入帳號:");
        String name = scanner.next();
        System.out.print("請輸入密碼:");
        String password = scanner.next();
        String phone = "";
        //新用戶註冊送300
        int money = 300;
        //註冊用
        if (select == 2) {
            System.out.print("請輸入電話:");
            phone = scanner.next();
        }

        Customer customer = new Customer(name, password, phone, money);

        return customer;
    }
}
