package dao;

import dbutil.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/***
 * 顧客類別
 */
public class CustomerDaoImpl implements RoleDao {
    public Role login(String name, String password) {
        String sql = "select * from customers where name=? and password=?";
        Connection conn = JDBCUtil.getConnection("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setPassword(result.getString("password"));
                customer.setPhone(result.getString("phone"));
                customer.setMoney(result.getInt("money"));
                return customer;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 註冊
     *
     * @param role
     * @return
     */
    public boolean register(Role role) {
        Customer customer = (Customer) role;
        String sql = "insert into customers (name,password,phone,money) values(?,?,?,?)";
        Connection conn = JDBCUtil.getConnection("myshop");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getPhone());
            pstmt.setInt(4, customer.getMoney());

            pstmt.execute();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return true;
    }

    public boolean check(Role role) {
        Customer customer = (Customer) role;
        String sql = "select * from customers where phone=?";
        Connection conn = JDBCUtil.getConnection("myshop");
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getPhone());
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Role role) {
        Customer customer = (Customer) role;
        Connection conn = JDBCUtil.getConnection("myshop");
        String sql = "update customers set money=? where id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customer.getMoney() - 1);
            pstmt.setInt(2, customer.getId());
            pstmt.execute();
            return true;


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public List<Role> findAll() {

        List<Role> roles = new ArrayList<Role>();
        String sql = "select * from customers";
        Connection conn = JDBCUtil.getConnection("myshop");
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    String phone = resultSet.getString("phone");
                    int money = resultSet.getInt("money");

                    roles.add(new Customer(id, name, password, phone, money));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roles;
    }
}
