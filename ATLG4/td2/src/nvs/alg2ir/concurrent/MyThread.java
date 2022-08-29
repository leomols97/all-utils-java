package nvs.alg2ir.concurrent;

/**
 * Thread accedant en lecture a une instance de ToujoursPair
 *
 * Exemple inspire par Thinking in Java, 3rd Edition, Beta Copyright (c)2002 by
 * Bruce Eckel www.BruceEckel.com
 */
public class MyThread extends Thread {

    ToujoursPair tp;

    public MyThread(ToujoursPair tp) {
        this.tp = tp;
    }

    public void run() {
        while (true) {
            int val = tp.getI();
            if (val % 2 != 0) {
                System.out.println("myThread : " + val);
                System.exit(0);
            }
        }
    }
}
