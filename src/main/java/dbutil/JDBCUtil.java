package dbutil;

import java.sql.*;
import java.text.SimpleDateFormat;

public class JDBCUtil {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/myshop";
    private static String user = "root";
    private static String password = "password";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(String url,String user,String password,String dbName) {

        url = "jdbc:mysql://"+url+":3306/" + dbName + "?characterEncoding=UTF-8&useSSL=false";


        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection(String dbName) {

        url = "jdbc:mysql://localhost:3306/" + dbName + "?characterEncoding=UTF-8&useSSL=false";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                st = null;
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conn = null;
            }
        }
    }


    /**
     * @param strDate
     * @return "yyyy-MM-dd"
     */
    public static Date strToDate(String strDate) {
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
