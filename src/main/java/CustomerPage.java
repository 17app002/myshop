import dao.Customer;
import dao.CustomerDaoImpl;
import dao.Role;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/***
 * 顧客頁面
 */
public class CustomerPage {

    private final static int LOGIN = 1;
    private final static int REGISTER = 2;
    private final static int EXIT = 3;


    public CustomerPage() {
        layout();
    }

    public void mainMenu() {
        System.out.println("\n\t商店管理系統 v1.0");
        System.out.println("***>>顧客登入介面**************");
        System.out.println("[1]登入");
        System.out.println("[2]註冊");
        System.out.println("[3]離開");
        System.out.println("*******************************");
    }

    public void layout() {
        Scanner scanner = new Scanner(System.in);
        int select = 0;
        while (true) {
            try {
                mainMenu();
                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("請輸入正確數字");
            }

            if (select == EXIT) {
                break;
            }
            if (select < LOGIN || select > EXIT) {
                System.out.println("請重新輸入....");
                continue;
            }

            System.out.println("===============================");
            System.out.println(select == 1 ? "登入" : "註冊");
            System.out.println("===============================");
            //取得輸入的用戶
            Role role = getInput(select);

            if (select == LOGIN) {
                loginPage(role);
            } else if (select == REGISTER) {
                registerPage(role);
            }
        }
    }

    /**
     * 取得輸入資料
     *
     * @param select
     * @return
     */
    public Role getInput(int select) {
        Role role = null;
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入帳號:");
        String name = scanner.next();
        System.out.print("請輸入密碼:");
        String password = scanner.next();
        //登入
        if (select == LOGIN) {
            role = new Customer(name, password, "", 0);
            //註冊
        } else if (select == REGISTER) {
            //新用戶註冊送1000
            int money = 1000;
            System.out.print("請輸入電話:");
            String phone = scanner.next();
            role = new Customer(name, password, phone, money);
        }

        return role;
    }

    /**
     * 登入畫面
     *
     * @param role
     */
    public void loginPage(Role role) {
        role = new CustomerDaoImpl().login(role.getName(), role.getPassword());
        if (role != null) {
            System.out.println("登入成功!");
            System.out.println("轉到商品介面.....");
            new ItemPage(role);
            return;
        }

        System.out.println("登入失敗!");
    }


    /**
     * 顯示所有顧客資訊
     */
    public static void findAllPage() {

        List<Role> customerList = new CustomerDaoImpl().findAll();

        if (customerList.size() == 0) {
            System.out.println("無顧客資料");
            return;
        }

        for (Role role : customerList) {
            System.out.println(role);
        }
    }

    /**
     * 註冊畫面
     *
     * @param role
     */
    public void registerPage(Role role) {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        if (!customerDao.check(role)) {
            boolean success = customerDao.register(role);
            if (success) {
                System.out.println("註冊成功!");
                System.out.println("轉到商品介面.....");
                new ItemPage(role);
                return;
            }
        }
        System.out.println("用戶已存在!");
    }

}
