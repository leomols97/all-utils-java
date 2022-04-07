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
public class JeuDeCartes {
    public static String[] créerJeuDeCartes(){
        String[] valeur = {"2","3","4","5","6","7","8","9","10","Valet",
             "Dame","Roi","As"};
        String[] couleurs = {"Coeur", "Pique", "Carreau", "Tréfle"};
        String[] cartes = new String[52];
        int pos = 0;
        for(int i = 0; i < valeur.length; i++){
            for(int j = 0; j<couleurs.length; j++){
                cartes[pos] = valeur[i]+" de "+couleurs[j];
                pos++;
            }
        }
        return cartes;
    }
    
    
    public static void afficherCartes(String[] array){
        for (String v : array){
            System.out.println(v);
        }
    }
    
    public static int valeurCarte(String uneCarte){
        String[] valeur = {"2","3","4","5","6","7","8","9","10","Valet",
             "Dame","Roi","As"};
        int[] nbr = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}; 
        for(int i = 0; i<valeur.length; i++){
            if(uneCarte.startsWith(valeur[i])){
                return nbr[i];
            }
        }
        return -1;
    }
    
    /**
     * Compare la valeur de 2 cartes.
     * 
     * @param carte1 la prémière carte.
     * @param carte2 la deuxième carte a comparer avec la première.
     * @return -1 si la valeur de la prémière est supérieur a celle de
     * la deuxième, 1 si c'est l'inverse 
     * et 0 si la valeur des deux est identique.
     */
    public static int comparerCartes(String carte1, String carte2){
        if(valeurCarte(carte1) < valeurCarte(carte2)){
            return -1;
        } else if(valeurCarte(carte1) > valeurCarte(carte2)){
            return 1;
        } else{
            return 0;
        }
    }
    
    public static String tirerCarte(String[] cartes){
        int pos = (int)(Math.random() * cartes.length+1);
        return cartes[pos];
    }
    
    public static void trierJeudeCartes(String[] jeuDeCartes){
        if(jeuDeCartes.length == 0){
            throw new IllegalArgumentException("Erreur : le tableau est vide");
        }
        for (int i = 0; i < jeuDeCartes.length; i++){
            int minIdx = i;
            for (int j = i; j < jeuDeCartes.length; j++){
                if (valeurCarte(jeuDeCartes[j]) < valeurCarte(jeuDeCartes[minIdx])){
                    minIdx = j;
                }
            }
            String tmp = jeuDeCartes[minIdx];
            jeuDeCartes[minIdx] = jeuDeCartes[i];
            jeuDeCartes[i] = tmp;
        }
    }
}
