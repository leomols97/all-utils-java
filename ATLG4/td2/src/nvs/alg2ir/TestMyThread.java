package nvs.alg2ir;

/**
 * Classe de test de la classe MyThread
 */
public class TestMyThread {

    public static void main(String[] args) {
        MyThread t = new MyThread("one");
        t.start();
        //t.run();
        for (int i = 0; i < 50000; ++i) {
            //for (int j = 0; j < 50000; ++j) ;
            System.out.println("TestMyThread: " + i);
        }
    }
}
