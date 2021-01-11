/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AllUtils;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StringUtils {
    public static String reverseWord(String word){
        StringBuilder stb = new StringBuilder(word);
        for(int i = 0; i<word.length(); i++){
            stb.setCharAt(word.length(), word.charAt(i));
        }
        return stb.toString();
    }
}
