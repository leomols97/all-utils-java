package nvs.alg2ir.block;

/**
 * Classe de test instanciant deux threads utilisant une methode a blocs
 * synchronises d'un meme objet.
 */
public class Test {

    public static void main(String[] args) {

        MyObject mo = new MyObject();
        MyThread t1 = new MyThread("t1", mo);
        MyThread t2 = new MyThread("t2", mo);

        t1.start();
        Thread.yield();
        t2.start();
    }
}
