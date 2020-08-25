import dbutil.DBUtil;

public class MyShop {

    private String dbName="myshop";
    private String tableName="admin";

    public MyShop(){

        DBUtil dbUtil=new DBUtil(dbName,"root","password");

        if(dbUtil.isConnection()){
            dbUtil.release();
        }
    }

    public static void main(String[] args) {
        MyShop myShop=new MyShop();
    }

}


