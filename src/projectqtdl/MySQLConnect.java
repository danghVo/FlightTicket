
package projectqtdl;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnect {
    public static Connection connect()
    {
        Connection conn = null;
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://root@localhost/projectqtdl" ,"root","cayhuong123");
        } catch (Exception ex) { //xử lý ngoại lệ nếu có
            ex.printStackTrace();
            }
        return conn;
    }
}
