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
public class MathUtils {
    /* La complexité finale de l'algorithme est 
       O(1) + O(n) + O(1)= O(n)
    */
    public static double exponentiation(double x, int n){
        // -> 
        if (n<0)
            throw new IllegalArgumentException("n must be >= 0.");
        double result = 1;
        // <- cette partie de code est toujours le meme temps en fonction de n.
        // cette partie de code est dite en O(1) (temps constant)
        
        //->
        for(int i = 0; i < n /* O(1)*/; i++ /* O(1) */){
            // Do n times:
            result *= x; // ca, c'est du O(1) (temps constant)
        }
        // <- ca prend un temps qui varie en fonction de n
        // la boucle est répétée n fois. Donc la complexité de ce bout de code 
        // est O(n * (O(1) + O(1) + (O(1))
        // O(n * O(1)     <- O(1)+O(1)= O(1)
        // O(n)
        return result;
    }    
    
    public static double exponentiationFast(double x, int n){
        if (n<0)
            throw new IllegalArgumentException("n must be >= 0.");
        if (n==0){
            return 1;
        } else if (n==1) {
            return x;
        } else if(n % 2 == 0){
            // calculer x*(n/2) et multiplier ca par lui-meme
            double tmp = exponentiationFast(x, n / 2);   
            return tmp * tmp;
        } else {
            // calculer x*(n-1) et multiplier ca par x
            double tmp = exponentiationFast(x, n -1);
            return tmp * x;
            
        }
    }
    
    public static double exponentiationRapide2(double x, int n){
        if (n<0) //O(1)
            throw new IllegalArgumentException("n must be >= 0.");
        double result = 1; // O(1)
        while (n > 0 /* O(1) */){ // (O(1) + O(1)) * nombre_execution_boucle
            // tout le bloc d'instruction est du O(1)
            if (n % 2 == 1) {
                result *= x;
                n--;
            } else {
                x *= x;
                n /= 2;
            }
        }
        return result;
    }
    
    public static int secondeHeures(int sec){
        return (sec/3600);
    }
    
    public static int secondeMinutes(int sec){
        return (sec%3600)/60;
    }
    
    public static int secondes(int sec){
        return (sec%3600)%60;
    }
    
    public static void doubleDes(int nbLances){
        for(int i = 0; i <= nbLances; i++){
            int des1 = (int) (Math.random() * 7);
            int des2 = (int) (Math.random() * 7);
            System.out.println(des1+" "+des2);
            if(des1 == des2){
                System.out.println("c'est un double dés !!!! GG");
            }
        }
    }
    
    public static boolean leapYear(int year){
        return ((year%400 == 0) || ((year%4 == 0) && (year%100 != 0)));
    }
    
    public static void conversionSeconde(int sec){
        System.out.println("il est "+secondeHeures(sec)+"h"+
                secondeMinutes(sec)+"'"+secondes(sec));
    }
    
    public static void main(String[] args) {
        //System.out.println(heureSeconde(10000));
        //System.out.println(heureMinute(10000));
        //System.out.println(heureSecondes(10000));
        //doubleDes(10);
        //int test = (int) (Math.random() * 7);
        //System.out.println(test);
        //System.out.println(leapYear(2100));
        //conversionSeconde(3726);
    }
    
    public static int random01(){
        int nbr = (int)Math.random();
        return nbr;
    }
    
    public static int randomMax0(int max){
        int nbr = (int)(Math.random() * max+1);
        return nbr;
    }
    
    public static int randomMinMax(int min, int max){
        int nbr = (int)((Math.random() * max-min+1)+min);
        return nbr;
    }
}
