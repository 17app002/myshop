import dbutil.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class THSViewer {
    public static void main(String[] args) {

        System.out.println("遠端主機連線中......");

        Connection connection= JDBCUtil.getConnection("db4free.net","iiiplay001","me516888","ths_data");

        System.out.println(connection);

        try {
            Statement statement=connection.createStatement();
            String sqlStr = "select * from ths_data;";
            ResultSet resultSet=statement.executeQuery(sqlStr);
            String[] columns={"date","no","start_loc","start_time",
                    "end_loc","end_time","discount","ticks","price","url","status"};

            if(resultSet==null){
                System.out.println("目前無資料");
                return;
            }

            resultSet.last();
            System.out.println("共"+resultSet.getRow()+"筆資料");
            resultSet.beforeFirst();


            while (resultSet.next()){
                String data="";
                for(String column:columns){
                    data+=resultSet.getString(column)+"\t";
                }
                data+="\n";
                System.out.println(data);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
