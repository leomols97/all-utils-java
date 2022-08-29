package g56133.atl.SortingRace.model;

import g56133.atl.SortingRace.utils.Observable;
import g56133.atl.SortingRace.utils.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the thread that will be launch to sort an array.
 * 
 * @author Leong Paeg-Hing
 */
public class SortThread extends Thread implements Observable{
    
    private Sort sort;
    private ArrayList<Observer> observer;
    
    public SortThread(Sort sort) {
        this.sort = sort;
        this.observer = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer.add(observer);
    }

    @Override
    public synchronized void notifyObserver(List<Object> object) {
        for(Observer o : this.observer) {
            ArrayList<Object> objectList = new ArrayList<>();
            objectList.add(this.sort);
            o.update(objectList);
        }
    }
    
    public void run() {
        this.sort.Sort();
        notifyObserver(null);
    }
}
