package dao;

import dbutil.JDBCUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean update(Item item) {
        Connection conn = JDBCUtil.getConnection("myshop");
        String sql = "update items set qty=? where id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, item.getQty() - 1);
            pstmt.setInt(2, item.getId());
            if (pstmt.execute()) {
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
    public boolean put(Item item) {
        Connection conn = JDBCUtil.getConnection("myshop");
        String sql = "insert into items (name,price,qty,create_date,info) values(?,?,?,?,?)";

        Date sqlDate = item.getCreate_date();
        if (sqlDate == null) {
            sqlDate = new Date(System.currentTimeMillis());
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setFloat(2, item.getPrice());
            pstmt.setInt(3, item.getQty());
            pstmt.setDate(4, sqlDate);
            pstmt.setString(5, item.getInfo());

            pstmt.execute();
            System.out.println("產品上架成功!");
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
    public boolean check(Item item) {
        String sql = "select * from items where name=?";
        Connection conn = JDBCUtil.getConnection("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
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
    public List<Item> getAll() {

        List<Item> items = new ArrayList<>();
        String sql = "select * from items";
        Connection conn = JDBCUtil.getConnection("myshop");
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    float price = resultSet.getFloat("price");
                    int qty = resultSet.getInt("qty");
                    Date date = resultSet.getDate("create_date");
                    String text = resultSet.getString("info");
                    items.add(new Item(id, name, price, qty, date, text));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return items;
    }


    /**
     * @param strDate
     * @return
     */
    public Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }
}
