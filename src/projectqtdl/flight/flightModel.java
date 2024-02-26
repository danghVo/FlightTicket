/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectqtdl.flight;

import projectqtdl.flight.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import projectqtdl.MySQLConnect;
/**
 *
 * @author huynh
 */
public class flightModel {
     public static Connection db = MySQLConnect.connect();
     
    public static ResultSet getAllFlight() {
       CallableStatement pst = null;

       if(db != null) {
           try{
                String sql = "{Call showAllFlight()}";
                pst = db.prepareCall(sql);
                
                ResultSet result = pst.executeQuery();
                return result;
                
           }
           catch (SQLException ex) {
               System.out.println(ex);
           }
       }
       return null;
   }
   
   public static boolean addFlight(Flight flight) {
       CallableStatement pst = null;
       if(db != null){
           try{
               String sql = "{call addFlight(?,?,?,?,?,?,?)}";
               pst = db.prepareCall(sql);
               
               pst.setString(1, flight.from);
               pst.setString(2, flight.to);
               pst.setString(3, flight.date);
               pst.setString(4, flight.boardingTime);
               pst.setInt(5, flight.seat);
               pst.setString(6, flight.gate);
               pst.setString(7, flight.flight);
               
               int result = pst.executeUpdate();
               if(result > 0) return true;
           }
           catch(SQLException ex) {
               System.out.println(ex);
           }
       }
       return false;
   }
   
   public static boolean updateFlight(Flight flight) {
       CallableStatement pst = null;
       if(db != null){
           try{
               String sql = "{call updateFlight(?,?,?,?,?,?,?,?)}";
               pst = db.prepareCall(sql);
               
               pst.setString(1, flight.from);
               pst.setString(2, flight.to);
               pst.setString(3, flight.date);
               pst.setString(4, flight.boardingTime);
               pst.setInt(5, flight.seat);
               pst.setString(6, flight.gate);
               pst.setString(7, flight.flight);
               pst.setInt(8, flight.getId());
              
               int result = pst.executeUpdate();
               if(result > 0) return true;
           }
           catch(SQLException ex) {
               System.out.println(ex);
           }
       }
       return false;
   }
   
   public static boolean deleteFlight(int id) {
       CallableStatement pst = null;
       if(db != null){
           try{
               String sql = "{call deleteFlight(?)}";
               pst = db.prepareCall(sql);
               
               pst.setInt(1, id);               
               
               int result = pst.executeUpdate();
               if(result > 0) return true;
           }
           catch(SQLException ex) {
               System.out.println(ex);
           }
       }
       return false;
   }
   
   public static ResultSet getAllFrom() {
       CallableStatement cs = null;
       if(db != null){
        try{
            String sql = "{call getAllFrom()}";
            cs = db.prepareCall(sql);

            ResultSet rs = cs.executeQuery();
            return rs;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
       }
       return null;
   }
   
   public static ResultSet getAllTo() {
       CallableStatement cs = null;
       if(db != null){
        try{
            String sql = "{call getAllTo()}";
            cs = db.prepareCall(sql);

            ResultSet rs = cs.executeQuery();
            return rs;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
       }
       return null;
   }
   
   public static ResultSet getAllDate() {
       CallableStatement cs = null;
       if(db != null){
        try{
            String sql = "{call getAllDate()}";
            cs = db.prepareCall(sql);

            ResultSet rs = cs.executeQuery();
            return rs;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
       }
       return null;
   }
   
   public static ResultSet getSearchFlight(String from,String to){
       CallableStatement cs = null;
       if(db != null){
        try{
            String sql = "{call searchFlight(?, ?)}";
            cs = db.prepareCall(sql);
            
            cs.setString(1, (from.equals("--"))? "." : from);
            cs.setString(2, (to.equals("--"))? "." : to);            

            ResultSet rs = cs.executeQuery();
            return rs;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
       }
       return null;
   }
   
   public static Flight getFlightById(int id) {
       PreparedStatement ps = null;
       if(db != null){
        try{
            String sql = "Select * from flight where flight.id = ?";
            ps = db.prepareStatement(sql);
            
            ps.setInt(1, id);       

            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return new Flight(rs.getString("from"), 
                                  rs.getString("to"), 
                                  rs.getString("date"), 
                                  rs.getString("boarding_time"), 
                                  rs.getInt("seat"),
                                  rs.getString("gate"),
                                  rs.getString("flight"),
                                  rs.getInt("id"));
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
       }
       return null;
   }
   
   public static ResultSet getAllTicketOf(int id) {
        PreparedStatement ps = null;
        if(db != null){
         try{
             String sql = "Select * from ticket where ticket.flight_id = ?";
             ps = db.prepareStatement(sql);

             ps.setInt(1, id);       

             ResultSet rs = ps.executeQuery();
             return rs;
         }
         catch(SQLException ex) {
             ex.printStackTrace();
         }
        }
        return null;
   }
   
   public static String getPriceBySeat(int id, String name, int number) {
       PreparedStatement ps = null;
        if(db != null){
         try{
             String sql = "Select cost from ticket where ticket.flight_id = ? and ticket.seat_name = ? and ticket.seat_number = ?";
             ps = db.prepareStatement(sql);

             ps.setInt(1, id);       
             ps.setString(2, name);
             ps.setInt(3, number);

             ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getString("cost");
         }
         catch(SQLException ex) {
             ex.printStackTrace();
         }
        }
        return "Chưa có giá tiền";
   }
}
