package nvs.alg2ir.concurrent;

/**
 * Thread accedant en ecriture et lecture a une instance de ToujoursPair
 *
 * Exemple inspire par Thinking in Java, 3rd Edition, Beta Copyright (c)2002 by
 * Bruce Eckel www.BruceEckel.com
 */
public class Test {

    public static void main(String[] args) {
        ToujoursPair tp = new ToujoursPair();
        MyThread t = new MyThread(tp);
        t.start();
        while (true) {
            tp.nextI();
            if (tp.getI() % 1000000 == 0) {
                System.out.println(tp.getI());
            }
        }
    }
}
