package dao;

public interface CustomerDao {

    public Customer login(String username,String password);

    public boolean register(Customer customer);

    public boolean checkCustomer(Customer customer);
}
