package dao;

import dbutil.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * 顧客類別
 */
public class AdminDaoImpl implements AdminDao {
    public Admin login(String name, String password) {
        String sql = "select * from admin where name=? and password=?";
        Connection conn = JDBCUtil.getConnection("myshop");

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                Admin admin = new Admin();
                admin.setId(result.getInt("id"));
                admin.setName(result.getString("name"));
                admin.setPassword(result.getString("password"));
                return admin;
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

}
