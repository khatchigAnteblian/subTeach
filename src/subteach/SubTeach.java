/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subteach;

/**
 *
 * @author 345983704
 */

import java.util.Hashtable;
import java.util.Map;

public class SubTeach {

    /**
     * @param args the command line arguments
     */
    
    static Hashtable<Character, Integer> table = new Hashtable<>();
    
    public static void main(String[] args) {
        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,?";
        for (int i=0; i<charSet.length(); i++) {
            table.put(charSet.charAt(i), i+1);
        }
        
        String sampleDec = "Jane";
        String sampleEnc = "B6Hg";
        String cipher1 = "pIZjugwgZ6HkZx6kZjiZg77GtGgHjUkZ76tjiwZ6ZIgvG9wGvgZI tuZ6IZY,LLYLKln";
        
        String sample2Dec = "135";
        String sample2Enc = "29B";
        String cipher2 = ",40Va0aT.H0az8a0Y08Fl0Y0kpuk1g08ll0aq0fdddd";
        
        int key1 = crack(sampleEnc, sampleDec);
        int key2 = crack(sample2Enc, sample2Dec);
        System.out.println(rem(cipher1, key1));
        System.out.println(rem(cipher2, key2));
       
        
    }
    
    public static String rem(String message, int key) {
        String result = "";
        for (int i=0; i<message.length(); i++) {
            int charCode = table.get(message.charAt(i));
            int newCode = (charCode * key) % 67;
            char newChar = reverseMap(newCode);
            result += newChar;
        }
        return result;
    }
    
    public static int crack(String enc, String dec) {
        int result = 0;
        for (int i=2; i<67; i++) {
            String shiftedEnc = rem("" + enc.charAt(0), i);
            String decFirstChar = "" + dec.charAt(0);
            if (shiftedEnc.equals(decFirstChar)) {
                result = i;
            }
        }
        return result;
    }
    
    public static char reverseMap(int value) {
        String a = "";
        for (Map.Entry entry : table.entrySet()) {
            if (Integer.valueOf(value) == entry.getValue()) {
                a += entry.getKey();
            }
        }
        return a.charAt(0);
    }
    
}
