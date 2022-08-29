package nvs.alg2ir;

/**
 * Creation d'une classe thread par implementation de l'interface Runnable
 */
public class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10000000; ++j) ;
            System.out.println("MyRunnable: " + name + " : " + i);
        }
    }
}
