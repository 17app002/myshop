import java.util.Scanner;

public class myShop {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("商店管理系統 v1.0");
            System.out.println("[1]admin [2]顧客  [3]離開");

            int select = scanner.nextInt();
            if(select==1){
                new AdminPage();
            }
            else if (select == 2) {
                new CustomerPage();
            } else {
                break;
            }
        }
    }
}


