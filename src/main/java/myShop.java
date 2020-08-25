import dao.Customer;
import dao.CustomerDaoImpl;

public class myShop {


    public static void main(String[] args) {

        CustomerDaoImpl customerDao = new CustomerDaoImpl();

        //登入
        Customer customer = customerDao.login("mandy", "mandy");

        System.out.println(customer);

        customer = new Customer("張三", "12345678", "0981765789", 8000);
        //註冊
        if (!customerDao.checkCustomer(customer)) {
            boolean success = customerDao.register(customer);
            if (success) {
                System.out.println("註冊成功!");
            }
        } else {
            System.out.println("用戶已存在!");
        }
    }
}
