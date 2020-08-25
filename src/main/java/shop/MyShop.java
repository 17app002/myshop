package shop;

import dbutil.DBUtil;

public class MyShop {
    private String dbName = "myshop";
    public static DBUtil dbUtil;
    public LoginPage loginPage;

    public MyShop() {
        dbUtil = new DBUtil(dbName, "root", "password");
        if (dbUtil.isConnection()) {
            System.out.println("商店資料初始化成功....");
        }
    }
    public boolean login() {
        loginPage = new LoginPage();
        return loginPage.isLogin();
    }

    public static void main(String[] args) {
        MyShop myShop = new MyShop();

        if (!myShop.login()) {
            System.out.println("登出系統成功");
            dbUtil.release();
            return;
        }

        System.out.println("登入成功!");
        dbUtil.release();
    }
}


