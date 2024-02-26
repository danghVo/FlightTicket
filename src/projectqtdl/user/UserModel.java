/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectqtdl.user;
import projectqtdl.MySQLConnect;
import java.sql.*;
import projectqtdl.MySQLConnect;

/**
 *
 * @author huynh
 */
public class UserModel {
   private static String dbname = "users";
   private static Connection db = MySQLConnect.connect();
    
   public static boolean createUser(User user) {
       CallableStatement cs = null;

       if(db != null) {
           try{
                String sql = "{call createUser(?, ?, ?, ?)}";
                cs = db.prepareCall(sql);
                
                cs.setString(1, user.username);
                cs.setString(2, user.password);
                cs.setString(3, user.email);
                cs.setString(4, user.phone);
                
                return cs.executeUpdate()>0;
                
           }
           catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
       
       return false;
   }
   
   public static boolean checkIsExist(String valueName,String value){
       PreparedStatement cs = null;
       if(db != null && valueName != "") {
           try{
               String sql = "";
               if(valueName.equals("email")){
                   sql = "select CASE WHEN username = null THEN 0 else 1 end as result from users where users.email like ? ";
               }
               if(valueName.equals("username")){
                   sql = "select CASE WHEN username = null THEN 0 else 1 end as result from users where users.username like ? ";
               }
               if(valueName.equals("phone")){
                   sql = "select CASE WHEN username = null THEN 0 else 1 end as result from users where users.phone like ? ";
               }
               
               cs = db.prepareStatement(sql);
               cs.setString(1, value);             
               
               ResultSet result = cs.executeQuery();
               if(result.next()) 
                   return result.getBoolean("result");
           }
           catch(SQLException ex) {
               ex.printStackTrace();
           }
       }
       return false;
   }
   
   public static boolean logIn(String username, String password) {
       CallableStatement cs = null;
           try{
               int success = 0;
               String sql = "{? = call logIn(?, ?)}";
               
               cs = db.prepareCall(sql);
               cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
               cs.setString(2, username);
               cs.setString(3, password);
               cs.execute();
               return cs.getBoolean(1);
           }
           catch(SQLException ex) {
               ex.printStackTrace();
           }
       return false;
   }

   
   public static String getUserById(int id) {
       PreparedStatement cs = null;
       if(db != null) {
           try{
               String sql = "select username from users where users.id = ?";
              
               cs = db.prepareStatement(sql);
               cs.setInt(1, id);             
               
               ResultSet result = cs.executeQuery();
               if(result.next()) 
                   return result.getString("username");
           }
           catch(SQLException ex) {
               ex.printStackTrace();
           }
       }
       return "";
   }
   
   public static boolean bookTicket(int flight_id, String name, int number, int payOption, int payed,int user_id){
       CallableStatement cs = null;
       if(db != null) {
           try{
               String sql = "{call book(?, ?, ?, ?, ?, ?)}";
               
               cs = db.prepareCall(sql);             
               cs.setInt(1, flight_id);
               cs.setString(2, name);
               cs.setInt(3, number);
               cs.setInt(4, payOption);
               cs.setInt(5, payed);
               cs.setInt(6, user_id);
               
               int result = cs.executeUpdate();
               return result > 0;
           }
           catch(SQLException ex) {
               ex.printStackTrace();
           }
       }
       return false;
   }
   
   public static int getUserId(String username) {
       PreparedStatement cs = null;
       if(db != null) {
           try{
               String sql = "select id from users where username = ?";
              
               cs = db.prepareStatement(sql); 
               cs.setString(1, username);
               
               ResultSet result = cs.executeQuery();
               if(result.next()) return result.getInt("id");
           }
           catch(SQLException ex) {
               ex.printStackTrace();
           }
       }
       return 0;
   }
   
    public static ResultSet getAllTicketOfUser(int id) {
        PreparedStatement cs = null;
            if(db != null) {
                try{
                    String sql = "select * from users, ticket, flight "
                            + "where users.id = ticket.user_id and ticket.flight_id = flight.id and users.id = ?";

                    cs = db.prepareStatement(sql); 
                    cs.setInt(1, id);

                    ResultSet result = cs.executeQuery();
                    return result;
                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return null;
    }
}
