import dao.Admin;
import dao.AdminDaoImpl;
import dao.Role;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/***
 * 顧客頁面
 */
public class AdminPage {

    private final static int LOGIN = 1;
    private final static int REGISTER = 2;
    private final static int EXIT = 3;

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
                System.out.println("[2]註冊");
                System.out.println("[3]離開");
                System.out.println("*******************************");

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
            System.out.println(select == LOGIN ? "登入" : "註冊");
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

    public Role getInput(int select) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入帳號:");
        String name = scanner.next();
        System.out.print("請輸入密碼:");
        String password = scanner.next();
        int level = 0;
        if (select == REGISTER) {
            System.out.print("請輸入管理級別:");
            level = scanner.nextInt();
        }

        Role role = new Admin(name, password, level);

        return role;
    }

    /**
     * 登入畫面
     *
     * @param role
     */
    public void loginPage(Role role) {
        role = new AdminDaoImpl().login(role.getName(), role.getPassword());
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

        List<Role> adminList = new AdminDaoImpl().findAll();

        if (adminList.size() == 0) {
            System.out.println("無管理者資料");
            return;
        }

        for (Role role : adminList) {
            System.out.println(role);
        }
    }

    /**
     * 註冊畫面
     *
     * @param role
     */
    public void registerPage(Role role) {
        AdminDaoImpl adminDao = new AdminDaoImpl();
        if (!adminDao.check(role)) {
            boolean success = adminDao.register(role);
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
