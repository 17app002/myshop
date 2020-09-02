import java.util.Scanner;
import view.*;

public class MyShop {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\t商店管理系統 v1.0");
            System.out.println("***>>用戶登入介面**************");
            System.out.println("[1]系統管理員");
            System.out.println("[2]顧客");
            System.out.println("[3]離開");
            System.out.println("******************************");
            System.out.print("請選擇登入角色:");

            int select = scanner.nextInt();

            if (select == 1) {
                new AdminPage();
            } else if (select == 2) {
                new CustomerPage();
            } else if (select == 3) {
                System.out.println("離開程式.....");
                break;
            }
        }
    }
}


