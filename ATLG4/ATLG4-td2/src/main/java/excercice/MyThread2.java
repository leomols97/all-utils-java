/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excercice;

import static java.lang.Thread.sleep;

/**
 *
 * @author Leong Paeg-Hing
 */
public class MyThread2 extends Thread{
    
    private String re1;
    private String re2;
    
    public MyThread2(String r1, String r2) {
        super("mt1");
        this.re1 = r1;
        this.re2 = r2;
    }
    
    public void run() {
        synchronized (this.re2) {
            System.out.println("Thread 2: locked resource 2");

            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }

            synchronized (this.re1) {
                System.out.println("Thread 2: locked resource 1");
            }
        }
    }
}
