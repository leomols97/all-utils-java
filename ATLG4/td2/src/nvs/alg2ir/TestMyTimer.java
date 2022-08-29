package nvs.alg2ir;

/**
 * Classe de test de la classe MyTimer
 */
public class TestMyTimer {

    public static void main(String[] args) {
        MyTimer myTimer = new MyTimer(4000);
        myTimer.start();
        try {
            System.out.println("what");
            Thread.sleep(7011);
            System.out.println("ok");
        } catch (InterruptedException e) {
            System.out.println("TestMyTimer: exception " + e);
        }
        myTimer.shouldRun = false;
        System.gc();
        System.out.println("MyTimer: gc called");
        System.out.println("MyTimer: end");
    }

}
