/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package subteach;

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
        // Create a string of all characters we need
        // Loop through the string and add each character to hashtable, instead of manually doing it one by one
        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,?";
        for (int i=0; i<charSet.length(); i++) {
            table.put(charSet.charAt(i), i+1);
        }
        
        // Test the samples
        String sampleDec = "Jane";
        String sampleEnc = "B6Hg";
        String cipher1 = "pIZjugwgZ6HkZx6kZjiZg77GtGgHjUkZ76tjiwZ6ZIgvG9wGvgZI tuZ6IZY,LLYLKln";
        
        String sample2Dec = "135";
        String sample2Enc = "29B";
        String cipher2 = ",40Va0aT.H0az8a0Y08Fl0Y0kpuk1g08ll0aq0fdddd";
        
        int key1 = crack(sampleEnc, sampleDec);
        int key2 = crack(sample2Enc, sample2Dec);
        System.out.println(rem(cipher1, key1)); // Should output "Is there any way to efficiently factor a semiprime such as 54775723?"
        System.out.println(rem(cipher2, key2)); // Should output "Is it true that 2 and 2 ALWAYS add to 4????"
       
        
    }
    
    public static String rem(String message, int key) {
        // Shifting algorithm that encrypts or decrypts a message

        // Storing the result
        String result = "";

        // Loop through message
        // Convert each character to equivalent number code
        // Multiply number code by key and mod with 67
        // Convert this new code back to a character
        // Add to result
        for (int i=0; i<message.length(); i++) {
            int charCode = table.get(message.charAt(i));
            int newCode = (charCode * key) % 67;
            char newChar = reverseMap(newCode);
            result += newChar;
        }
        return result;
    }
    
    public static int crack(String enc, String dec) {
        // Cracking algorithm to extract the key from sample messages
        // Since the range of possible keys is very small, we can easily brute force the key
        // We loop through all the keys and try each one on the message
        // Return the one that turns the sample cipher into the sample decrypted message

        // Since the same key will be used on all characters of a message
        // We don't need to loop through the entire message, simply checking the first characters will do
        int result = 0;
        for (int i=2; i<67; i++) {
            // Shift the first character of the encrypted message on each iteration
            // If it equals the first character of the decrypted message, that's the key we want
            String shiftedEnc = rem("" + enc.charAt(0), i);
            String decFirstChar = "" + dec.charAt(0);
            if (shiftedEnc.equals(decFirstChar)) {
                result = i;
            }
        }
        return result;
    }
    
    public static char reverseMap(int value) {
        // Returns key from value in hashtable
        
        // Store key in variable
        String a = "";
        // Create a map entry for each element of the hashtable
        // (key=value)
        // Check if the value matches the one we want and get the corresponding key
        for (Map.Entry entry : table.entrySet()) {
            if (Integer.valueOf(value) == entry.getValue()) {
                a += entry.getKey();
            }
        }
        return a.charAt(0);
    }
    
}
