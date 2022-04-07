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
public class MarcheAléatoire {
    /**
     * Affiche le nom, prénom et matricule d'un étudiant.
     * 
     * @param nom le nom de l'étudiant.
     * @param prénom le prénom de l'étudiant.
     * @param matricule le matricule de l'étudiant.
     */
    public static void afficherTitre(String nom, String prénom, String matricule){
        System.out.println("Simulation d'une marche aléatoire 1D");
        System.out.println(nom+"-"+prénom+"-"+matricule);
    }
    
    /**
     * Choisit aléatoire de faire un pas vers la droite ou la gauche.
     * 
     * @param p la probabilité de faire un pas vers la droite.
     * @return +1 si c'est un pas vers la droite et -1 si c'est vers la gauche.
     */
    public static int pasAléatoire(double p){
        if(p < 0 || p > 1){
            throw new IllegalArgumentException("Erreur :"
                    + " la probabilité entré est incorrecte");
        }
        double random = Math.random();
        if(random <= p){
            return 1;
        } else{
            return -1;
        }
    }
    
    /**
     * Simule une marche vers la gauche ou
     * la droite choisi de manière aléatoire.
     * 
     * @param x0 la position de départ.
     * @param n le nombre de pas a éffectuer.
     * @param p la probabilité de faire un pas vers la droite.
     * @return retourne la position finale de la simulation.
     */
    public static int simulation(int x0, int n, double p){
        for(int i = 0; i<n; i++){
            int pas = pasAléatoire(p);
            x0 += pas;
        }
        return x0;
    }
    
    /**
     * Calcule la distance entre la position de départ et
     * de la position finale.
     * 
     * @param x0 la position de départ.
     * @param x1 la position finale.
     * @return la distance entre la position de départ et la position fianle.
     */
    public static int distance(int x0, int x1){
        return x1 - x0;
    }
    
    /**
     * Permet de faire en sorte que l'entrée au clavier soit un entier positif.
     * 
     * @return l'entier positif une fois vérifié.
     */
    public static int lireEntierPositif(){
        int entier;
        Scanner kbd = new Scanner(System.in);
        do{
            System.out.println("Veuillez entrez le nombre de pas a éffectuer");
            while(!kbd.hasNextInt()){
                kbd.nextLine();
                System.out.println("Pepehand, KEKW");
                System.out.println("Un autre");
            }
            entier = kbd.nextInt();
        } while(entier<0);
        return entier;
    }
    
    /**
     * Permet de faire en sorte que l'entrée au clavier soit un entier.
     * 
     * @return l'entier une fois vérifié.
     */
    public static int lectureRobuste(){
        Scanner kbd = new Scanner(System.in);
        System.out.println("Donnez la position initial :");
        while(!kbd.hasNextInt()){
            System.out.println("Entrez un entier svp :");
            kbd.next();
        }
        int value = kbd.nextInt();
        return value;
    }
}
