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
public class VoyelleUtils {
    public static void main(String[] args) {
        presentation("DEV1 - JAVAL, interrogation 2",
                "Leong Paeg-Hing", "g56133 - A311");
        for(int i = 0; i < 10; i++){
            System.out.println(aleatoire(6));
        }
        System.out.println(estConsonne('a'));
        System.out.println(estConsonne('b'));
        System.out.println(estConsonne(' '));
        try{
            System.out.println(nombreDeConValides("je suis un gros debile", 8, 'k'));;
        }catch (IllegalArgumentException bug){
            System.out.println(bug.getMessage());
        }
    }
     
    /**
     * Permet de présenter le travail et son auteur.
     * 
     * @param titre le titre de l'interrogation.
     * @param nomPrenom le nom et le prénom de l'étudiant.
     * @param matEtGrpe le matricule et le groupe de l'étudiant.
     */
    public static void presentation(
            String titre, String nomPrenom, String matEtGrpe){
        System.out.println(titre);
        System.out.println(nomPrenom+ " - " +matEtGrpe);
    }
     
    /**
     * Permet de choisir un entier au hasard entre 0 et max inclus.
     * 
     * @param max la borne supérieur
     * 
     * @return un nombre aléatoire compris entre 0 et max.
     */
    public static int aleatoire(int max){
        return (int) (Math.random() * (max - 1));
    }
    
    /**
     * Vérifie si un caractère donné est une voyelle ou non.
     * 
     * @param character le caractère a examiner.
     * 
     * @return vrai si c'est une voyelle et faux sinon.
     */
    public static boolean estVoyelle(char character){
        switch(character){
            case 'a': case 'e': case 'i': case 'o': case 'u': case 'y':
                return true;
            case ' ':
                return false;
            default:
                return false;
        }
    }
    
    /**
     * Vérifie si un caractère donné est une consonne ou non.
     * 
     * @param character le caractère a examiner.
     * 
     * @return vrai si c'est une consonne et faux sinon.
     */
    public static boolean estConsonne(char character){
        return(!estVoyelle(character) && character != ' ');
    }
    
    /**
     * Permet de former une chaine avec des consonnes
     * chosis au hasard dans une autre chaine.
     * 
     * @param maChaine la chaine ou choisir les voyelles.
     * @param nbConsonnes le nombre de voyelle à choisir.
     * 
     * @return la chaine de caractères formée des consonnes choisies.
     */
    public static String choisirNbConsonnes(String maChaine, int nbConsonnes){
        if(nbConsonnes <= 0){
            throw new IllegalArgumentException(
                    "Error : nombre de consonnes <= 0");
        }
        String result = "";
        int cpt = 0;
        while(cpt < nbConsonnes){
            int aleat = aleatoire(maChaine.length() - 1);
            char car = maChaine.charAt(aleat);
            if (estConsonne(car)){
                result += car;
                cpt++;
            }
        }
        return result;
    }
    
    /**
     * Permet de compter le nombre de consonnes
     * qui se trouve avant une lettre donné.
     * 
     * @param maChaine la chaine d'origine.
     * @param nbConsonnes le nombres de consonnes a choisir aléatoirement.
     * @param character le caractère a comparer.
     * 
     * @return 
     */
    public static int nombreDeConValides(
            String maChaine, int nbConsonnes, char character){
        if(nbConsonnes <= 0){
            throw new IllegalArgumentException(
                    "Error: nombre de consonnes <= 0");
        }
        String chaine = choisirNbConsonnes(maChaine, nbConsonnes);
        System.out.println(chaine);
        int cpt = 0;
        for(int i = 0; i < chaine.length(); i++){
            if(chaine.charAt(i) < character){
                cpt++;
            }
        }
        return cpt;
    }
}
