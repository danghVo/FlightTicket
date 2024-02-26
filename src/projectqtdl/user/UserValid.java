/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectqtdl.user;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author huynh
 */
public class UserValid {
    public static String checkUser(User user) {
        Pattern emailCheck = Pattern.compile("^[\\w\\d]+@([\\w]+\\.)+[\\w]{2,4}$");
        Matcher emailMatch = emailCheck.matcher(user.email);
        
        if(emailMatch.find() == false){
            return "Email không hợp lệ";
        }
        
        Pattern phoneCheck = Pattern.compile("^(09|08|07)[0-9]{8,10}$");
        Matcher phoneMatch = phoneCheck.matcher(user.phone);
        
        if(phoneMatch.find() == false){
            return "Số điện thoại không hợp lệ";
        }
        
        return "";
    }
}
