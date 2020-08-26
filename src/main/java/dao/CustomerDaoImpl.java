package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.JDBCUtil;

/***
 * 顧客類別
 */
public class CustomerDaoImpl implements CustomerDao{
    public Customer login(String name, String password) {
        String sql="select * from customers where name=? and password=?";
        Connection conn=JDBCUtil.getConnection("myshop");

        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,password);
            ResultSet result=pstmt.executeQuery();

            if(result.next()){
                Customer customer=new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setPassword(result.getString("password"));
                customer.setPassword(result.getString("phone"));
                customer.setMoney(result.getInt("money"));
                return customer;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    /***
     * 註冊功能
     * @param customer
     * @return
     */
    public boolean register(Customer customer) {
        String sql="insert into customers (name,password,phone,money) values(?,?,?,?)";
        Connection conn=JDBCUtil.getConnection("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getPhone());
            pstmt.setInt(4, customer.getMoney());

            pstmt.execute();


        }catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return true;
    }

    public boolean check(Customer customer) {
        String sql="select * from customers where phone=?";
        Connection conn=JDBCUtil.getConnection("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getPhone());
            ResultSet result=pstmt.executeQuery();

            if(result.next()){
                return true;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
}
