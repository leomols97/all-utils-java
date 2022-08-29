package nvs.alg2ir;

/**
 * Classe de test de la classe MyTimerInterrupt : utilisation de la methode
 * interrupt()
 */
public class TestMyTimerInterrupt {

    public static void main(String[] args) {
        MyTimerInterrupt myTimer = new MyTimerInterrupt(1000);
        myTimer.start();
        try {
            Thread.sleep(7011);
        } catch (InterruptedException e) {
            System.out.println("TestMytimer: exception " + e);
        }
        myTimer.interrupt();
    }
}
