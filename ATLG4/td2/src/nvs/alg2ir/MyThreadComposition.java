/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nvs.alg2ir;

import java.util.ArrayList;

/**
 *
 * @author Leong Paeg-Hing
 */
public class MyThreadComposition extends Thread{
    
    private String name;
    
    private ArrayList<Thread> allThread;
    
    public MyThreadComposition(Thread thread, String name) {
        this.name = name;
        this.allThread.add(thread);
    }
    
    public MyThreadComposition(ArrayList<Thread> allThread) {
        this.name = allThread.get(0).getName();
        this.allThread = allThread;
    }
    
    public void run() {
        this.allThread.forEach(thread -> {
            thread.start();
        });
    }
    
}
