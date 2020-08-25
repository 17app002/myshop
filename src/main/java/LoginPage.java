import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginPage {

    private int role;
    private String userName;
    private String passWord;
    private boolean login;
    //登入次數
    private int count;

    public LoginPage() {
        init();
    }

    public void init() {
        count = 0;
        login = false;
        show();
    }

    public boolean isLogin() {
        return login;
    }

    /***
     * 登入介面
     */
    public void show() {

        do {
            Scanner scanner = new Scanner(System.in);
            if (++count == 1) {
                System.out.println("======================================");
                System.out.println("\t\t 商店管理系統");
                System.out.println("登入:[1]系統管理員 [2]顧客 [3]離開系統");
                //登入的角色
                System.out.println("請輸入:");
                try {
                    role = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("輸入錯誤，請重新輸入.");
                    count = 0;
                    continue;
                }
            }

            if (role == 3) {
                break;
            }
            //登入的帳號
            System.out.println((count == 1) ? "請輸入帳號:" : "請輸入帳號(-1:重新選擇):");
            userName = scanner.next();
            //是否輸入錯誤
            if (count > 1 && userName.equals("-1")) {
                count = 0;
                continue;
            }
            //輸入密碼
            System.out.println("請輸入密碼");
            passWord = scanner.next();
            login = checkAccount(userName, passWord, role);

            if (!login) {
                System.out.println("登入失敗(" + count + ")，請重新登入....");
            }
        } while (!login);
    }

    /**
     * 檢查登入帳密
     *
     * @return
     */
    public static boolean checkAccount(String userName, String passWord, int role) {
        String sqlStr = null;

        switch (role) {
            case 1:
                sqlStr = "select name,password from admin;";
                break;
            case 2:
                sqlStr = "select name,password from customers;";
                break;
            default:
                return false;

        }

        Statement statement = MyShop.dbUtil.getStatement();
        try {
            ResultSet resultSet = statement.executeQuery(sqlStr);
            while (resultSet.next()) {
                if (userName.equals(resultSet.getString("name")) &&
                        passWord.equals(resultSet.getString("password"))) {

                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

}



