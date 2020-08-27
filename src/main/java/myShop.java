import java.util.Scanner;

public class myShop {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exit=false;
        while (!exit) {
            System.out.println("\n\t商店管理系統 v1.0");
            System.out.println("***>>用戶登入介面************");
            System.out.println("[1]系統管理員");
            System.out.println("[2]顧客");
            System.out.println("[3]離開");
            System.out.println("****************************");
            System.out.print("請選擇登入角色:");
            int select = scanner.nextInt();
            switch (select){
                case 1:
                    new AdminPage();
                    break;
                case 2:
                    new CustomerPage();
                    break;
                case 3:
                    exit=true;
                    break;
            }
        }
    }
}


