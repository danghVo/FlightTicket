/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectqtdl.flight;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  
import java.text.SimpleDateFormat;
import javax.swing.table.*;
import java.util.*;

/**
 *
 * @author huynh
 */
public class Flight {
    public static ArrayList<Flight> flightArr ;
    public String from;
    public String to;
    public String date;
    public String boardingTime;
    public int seat;
    public String gate;
    public String flight;
    private int id;
    
    public static void renewFlightArr(){
        Flight.flightArr = new ArrayList<Flight>(); 
    }
    
    public static void addFlight(Flight ticket) {
        Flight.flightArr.add(ticket);
    }
    
    public static Flight getFlightInArr(int index) {
        return Flight.flightArr.get(index);
    }
    
    public static Flight getFlightById(int id){
        for (Flight ticket: Flight.flightArr) {
            if(ticket.getId() == id) return ticket;
        }
        return null;
    }
    
    public static int getFlightIdInArr(int index) {
        return Flight.flightArr.get(index).getId();
    }
    
    public static void deleteFlightById(int rowId) {
        Flight.flightArr.remove(rowId);
    }
    
    public Flight(String from, String to, String date,String boardingTime,int seat,String gate, String flight, int id) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.boardingTime = boardingTime;
        this.gate = gate;
        this.seat = seat;
        this.flight = flight;
        this.setId(id);
    }
    
    @Override
    public String toString() {
        return this.from + 
                " " + this.to + 
                " " + this.date + 
                " " + this.boardingTime + 
                " " + this.seat + 
                " " + this.gate + 
                " " + this.flight;
    }
    
    public Vector getFLightVector() {
        Vector column = new Vector();
        column.add(this.from);
        column.add(this.to);
        column.add(this.date);
        column.add(this.boardingTime);
        column.add(this.seat);
        column.add(this.gate);
        column.add(this.flight);
        return column;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
}
