package nvs.alg2ir.synchronised;

/**
 * Thread utilisant une methode d'une instance de MyObject
 */
public class MyThread extends Thread {

    private MyObject myObject;

    public MyThread(String name, MyObject myObject) {
        super(name);
        this.myObject = myObject;
    }

    public void run() {
        String nom = Thread.currentThread().getName();
        System.out.println("My thread: thread  " + nom + " in run");
        myObject.show();
        System.out.println("My thread: thread " + nom + " out run");
    }
}
