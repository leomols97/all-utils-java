package nvs.alg2ir;

/**
 * Exemple de thread demon ou utilisateur
 */
public class DaemonThread extends Thread {

    public void run() {
        for (int n = 0; n < 42; ++n) {
            System.out.println("DaemonThread: run " + n);
            try {
                sleep(420);
            } catch (InterruptedException e) {
                System.out.println("DaemonThread thread: exception " + e);
            }
        }
    }

    public static void main(String[] args) {
        DaemonThread d;
        d = new DaemonThread();
        d.setDaemon(true);
        d.start();
        try {
            System.out.println("DaemonThread main: i do nothing during a while");
            sleep(7110);
            d.join();
            System.out.println("A");
        } catch (InterruptedException e) {
            System.out.println("DaemonThread: exception " + e);
        }
    }
}
