/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectqtdl.flight;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author huynh
 */
public class checkFlight {
    public static String check_valid(String from, String to, String date,String boardingTime,String seat,String gate, String flight) {
        if(from == null || to == null || date == null || boardingTime == null || seat == null || gate == null || flight == null){
            return "Bạn chưa nhập đủ thông tin";
        }
        
        String pattern = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ\n" +
                         "fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu\n" +
                          "UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]+";
        
        Pattern fromToCheck = Pattern.compile(pattern);
        Pattern seatCheck = Pattern.compile("[0-9]+");
        Pattern gateCheck = Pattern.compile("[0-9]{1,3}");        
        Pattern flightCheck = Pattern.compile("[A-Z]{1,3}[0-9]{1,4}");
        Pattern timeCheck = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");
        
        Matcher fromToMatcher = fromToCheck.matcher(from);
        if(fromToMatcher.find() == false) return "Nơi đi không đúng";
        
        fromToMatcher = fromToCheck.matcher(to);
        if(fromToMatcher.find() == false) return "Nơi đến không đúng";
        
        Matcher seatMatcher = seatCheck.matcher(seat);
        if(seatMatcher.find() == false) return "Ghế ngồi không đúng";
        
        Matcher gateMatcher = gateCheck.matcher(seat);
        if(gateMatcher.find() == false) return "Cổng không đúng";
        
        Matcher flightMatcher = flightCheck.matcher(flight);
        if(flightMatcher.find() == false) return "Chuyến bay không đúng";
        
        String messageDate = checkDate(date);
        if(messageDate != "") return messageDate;
        
        Matcher timeMatcher = timeCheck.matcher(boardingTime);
        if(timeMatcher.find() == false) return "Thời gian bay không đúng";
        
        return "";
    }
    
    private static String checkDate(String date){
        String[] arrOfDate = date.split("-");
        Pattern dateCheck = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");
        
        if(arrOfDate.length < 3)
               return "Bạn chưa nhập đủ ngày tháng năm";
        Matcher dateMatch = dateCheck.matcher(date);
        if(dateMatch.find() == false) return "Không đúng định dạng ngày tháng\n vd(05-06-2023)";
        
        int day = Integer.parseInt(arrOfDate[0]);
        int month = Integer.parseInt(arrOfDate[1]);
        int year = Integer.parseInt(arrOfDate[2]);
        if(day < 0 || day >31) return "Ngày không đúng";
        if(month < 0 || month > 12) return "Tháng không đúng";
        if(year < 0) return "Năm không đúng"; 
        if(day == 29)
            if(year%100 == 0 || year%4 != 0 || year%400 != 0)
                return "Năm không phải là năm nhuận";
        return "";  
    }
}
