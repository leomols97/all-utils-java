/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AllUtils;

import java.util.Scanner;

/**
 *
 * @author Leong Paeg-Hing
 */
public class UtilsForScan {
    
    public static int lireEntierPositif(){
        int entier;
        Scanner kbd = new Scanner(System.in);
        do{
            System.out.println("Entrez un entier positif");
            while(!kbd.hasNextInt()){
                kbd.nextLine();
                System.out.println("Pepehand, KEKW");
                System.out.println("Un autre");
            }
            entier = kbd.nextInt();
        } while(entier<0);
        return entier;
    }
    
    public static int lectureRobuste(){
        Scanner kbd = new Scanner(System.in);
        System.out.println("Entrez un entier svp :");
        while(!kbd.hasNextInt()){
            System.out.println("Entrez un entier svp :");
            kbd.next();
        }
        int value = kbd.nextInt();
        return value;
    }
    
    public static int lectureRobusteEntierString(String nombre){
        Scanner kbd = new Scanner(System.in);
        int nb;
        String value = nombre;
        boolean ok =true;
        do{
            if(!ok){
                System.out.println("Ce n'est pas un entier, mettez un entier :");
                value = kbd.nextLine();
            }
            ok = true;
            for(int i = 0; i<value.length(); i++){
                if(!Character.isDigit(value.charAt(i))){
                    ok = false;
                }
            }
        }while(!ok);   
        return nb = Integer.parseInt(value);
    }
    
    public static double lectureRobusteDoubleString(String nombre){
        Scanner kbd = new Scanner(System.in);
        double nb;
        String value = nombre;
        boolean ok =true;
        do{
            if(!ok){
                System.out.println("Ce n'est pas un réel, mettez un réel :");
                value = kbd.nextLine();
            }
            ok = true;
            for(int i = 0; i<value.length(); i++){
                if(!(Character.isDigit(value.charAt(i)) || value.charAt(i) == '.')){
                    ok = false;
                }
            }
        }while(!ok);   
        return nb = Double.parseDouble(value);
    }
}
