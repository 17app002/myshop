import dao.Admin;
import dao.AdminDaoImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

/***
 * 顧客頁面
 */
public class AdminPage {

    public AdminPage() {
        layout();
    }

    public void layout() {
        Scanner scanner = new Scanner(System.in);
        int select = 0;
        while (true) {
            try {
                System.out.println("\n\t商店管理系統 v1.0");
                System.out.println("***>>管理員登入介面************");
                System.out.println("[1]登入");
                System.out.println("[2]離開");
                System.out.println("*******************************");

                select = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("請輸入正確數字");
            }

            if (select == 2) {
                System.out.println("返回上一頁");
                break;
            }

            if (select < 1 && select > 2) {
                System.out.println("請重新輸入....");
                continue;
            }

            System.out.println("===============================");
            System.out.println(select == 1 ? "登入" : "用戶註冊");
            System.out.println("===============================");
            //輸入的用戶
            Admin admin = getInputAdmin();
            //登入
            if (select == 1) {
                admin = new AdminDaoImpl().login(admin.getName(), admin.getPassword());
                if (admin != null) {
                    System.out.println("登入成功!");
                    System.out.println("轉到商品介面.....");
                    new ItemPage(admin);
                    return;
                }
                System.out.println("登入失敗...");
            }
        }
    }

    public Admin getInputAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入帳號:");
        String name = scanner.next();
        System.out.print("請輸入密碼:");
        String password = scanner.next();


        Admin admin = new Admin(name, password);

        return admin;
    }
}
