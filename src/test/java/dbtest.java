import dbutil.DBUtil;
import org.junit.jupiter.api.Test;

public class dbtest {

    @Test
    public void test(){
        DBUtil dbUtil = new DBUtil("myshop", "root", "password");
        if (dbUtil.isConnection()) {
            System.out.println("資料庫連結成功！");
        }
    }
}
