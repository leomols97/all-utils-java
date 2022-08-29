/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excercice;

/**
 *
 * @author Leong Paeg-Hing
 */
public class MyThread1 extends Thread {

    private String re1;
    private String re2;

    public MyThread1(String r1, String r2) {
        super("mt1");
        this.re1 = r1;
        this.re2 = r2;
    }

    public void run() {
        synchronized (this.re1) {
            System.out.println("Thread 1: locked resource 1");

            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }

            synchronized (this.re2) {
                System.out.println("Thread 1: locked resource 2");
            }
        }
    }

}
