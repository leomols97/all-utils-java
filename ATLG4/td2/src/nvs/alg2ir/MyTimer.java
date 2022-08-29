package nvs.alg2ir;

/**
 * Classe thread affichant un petit message a intervalle regulier : exemple
 * d'utilisation de la methode Thread.sleep
 */
public class MyTimer extends Thread {

    private int laps;
    public boolean shouldRun; // notez le public !

    public MyTimer(int laps) {
        this.laps = laps;
        shouldRun = true;
    }

    public void run() {
        while (shouldRun) {
            try {
                System.out.println("a");
                sleep(laps / 2);
                System.out.println("MyTimer: run");
                sleep(laps / 2);
                System.out.println("b");
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
