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
public class TestDeadLock {
    
    public static void main(String[] args) {
        String r1 = "resources1";
        String r2 = "resources2";
        MyThread1 mt1 = new MyThread1(r1, r2);
        MyThread2 mt2 = new MyThread2(r1, r2);
        
        mt1.start();
        mt2.start();
        
        System.out.println("Fini maybe ?");
    }
}
