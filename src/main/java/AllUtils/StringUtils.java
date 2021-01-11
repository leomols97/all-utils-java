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
    
    public static void afficherTitre(String nom, String prénom, String matricule){
        System.out.println(nom+"-"+prénom+"-"+matricule);
    }
    
    /**
 * 
 * @param mot entré par l'utilisateur
 * @param lettre la lettre dont il faut compter le nombre d'occurrences
 * @return retourne le nombre d'occurrences pour la lettre donnée, dans le mot en question
 */
    static int nombreLettres(String mot, char lettre) {
        int cmt = 0;
        for (int i = 0; i < mot.length(); i++) {
            char c1 = mot.charAt(i);
            if (c1 == lettre) {
                cmt++;
            }
        }
        return cmt;
    }
    
/**
 * 
 * 
 * @param mot mot entré par l'utilisateur
 * @return retourne le nombre d'occurrences de la lettre la plus fréquente
 */
    static int occurrencesLettres(String mot) {
        int max = 0;
        for (int i = 0; i < mot.length(); i++) {
            if (nombreLettres(mot, mot.charAt(i)) > max) {
                max = nombreLettres(mot, mot.charAt(i));
            }
        }
        return max;
    }
}
