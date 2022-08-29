package nvs.alg2ir.synchronised;

/**
 * Classe pourvue de deux methodes d'affichage : illustration de l'utilisation
 * du mot cle synchronized comme modificateur de methode.
 */
public class MyObject {

    private String name;

    public MyObject(String name) {
        this.name = name;
    }

    //public void show() {
    public synchronized void show() {
        String nom = Thread.currentThread().getName();
        System.out.println("My object: thread " + nom
                + ", objet  " + name + " in show");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
        }
        System.out.println("My object: thread  " + nom
                + ",objet " + name + " out show");
    }

    //public void print() {
    public synchronized void print() {
        String nom = Thread.currentThread().getName();
        System.out.println("My object: thread  " + nom
                + ", objet " + name + " in print");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
        }
        System.out.println("My object: thread " + nom
                + ", objet " + name + " out print");
    }
}
