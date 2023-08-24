package Nguoi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuanLy {
    protected static boolean kiemTraEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    protected static boolean kiemTraTen(String hoTen){
        return hoTen.matches("^[^0-9]+$");
    }
    
    protected static boolean kiemTraSDT(String sdt){
        return sdt.matches("0\\d{9}");
    }

    protected static String chuanHoaChuoi(String s){
        s = s.trim();
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
    
        for (String word : words) {
            if (!word.isEmpty()) {  // Skip empty words
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                sb.append(firstLetter).append(restOfWord).append(' ');
            }
        }
    
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing space
        return sb.toString();
    }
}
