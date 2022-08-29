package nvs.alg2ir.block;

import java.util.Random;

/**
 * Classe pourvue d'une methode d'affichage avec des blocs synchronized sur
 * l'objet lui-meme ou sur une chaine de caracteres.
 */
public class MyObject {

    private Random rnd;

    public MyObject() {
        rnd = new Random();
    }

    public void fct() {
        String nom = Thread.currentThread().getName();
        System.out.println("MyObject: " + nom + " in fct");

        //synchronized (this) {
            synchronized("verrou") {
            System.out.println("MyObject: " + nom + " in bloc 1");
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
            }
            System.out.println("MyObject: " + nom + " out bloc 1");
        }

        System.out.println("MyObject: " + nom + " between bloc 1 and bloc 2");
        try {
            Thread.sleep(rnd.nextInt(1000));
        } catch (InterruptedException e) {
        }

        //synchronized (this) {
         synchronized ("verrou") {
            System.out.println("MyObject: " + nom + " in bloc 2");
            try {
                Thread.sleep(rnd.nextInt(1000));
            } catch (InterruptedException e) {
            }
            System.out.println("MyObject: " + nom + " out bloc 2");
        }

        System.out.println("MyObject: " + nom + " out fct");
    }
}
