package g56133.atl.SortingRace.model;

import java.util.ArrayList;

/**
 * This class will contain all the thread that will be launch.s
 * 
 * @author Leong Paeg-Hing
 */
public class Pool {
    
    private int limitThreads;
    private ArrayList<Thread> allThreads;
    private int nextThread;
    private boolean isCompleted;
    
    /**
     * Constructor of the class.
     * 
     * @param limitThreads 
     */
    public Pool(int limitThreads) {
        this.limitThreads = limitThreads;
        this.isCompleted = false;
        this.allThreads = new ArrayList<>();
    }
    
    public void addThreads(Thread thread) {
        this.allThreads.add(thread);
    }
    
    public synchronized void start() {
        for(int i = 0; i < this.allThreads.size() && i < this.limitThreads;
                i++, this.nextThread++) {
            this.allThreads.get(i).start();
        }
    }
    
    public synchronized void startNext() {
        if(this.nextThread < this.allThreads.size()) {
            this.allThreads.get(this.nextThread).start();
            this.nextThread++;
        }else {
            this.isCompleted = true;
        }
    }
    
    public boolean IsCompleted() {
        return this.isCompleted;
    }
}
