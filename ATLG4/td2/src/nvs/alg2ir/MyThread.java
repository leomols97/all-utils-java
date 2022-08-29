package nvs.alg2ir;

/**
 * Creation d'une classe thread par derivation de la classe Thread
 */
public class MyThread extends Thread {

    private String name;

    public MyThread(String s) {
        this.name = s;
    }

    public void run() {
        for (int i = 0; i < 100000; ++i) {
            //for (int j = 0; j < 100000; ++j) ;
            System.out.println("MyThread: " + name + " : " + i);
        }
    }
}
