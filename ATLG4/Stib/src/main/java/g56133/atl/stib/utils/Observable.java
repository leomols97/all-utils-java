package g56133.atl.stib.utils;

import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public interface Observable {
    /**
     * Register an observer.
     * 
     * @param observer the observer.
     */
    public void registerObserver(Observer observer);
    
    /**
     * Notify the observers.
     * 
     * @param object the list of object that will be given to the observer.
     */
    public void notifyObserver(List<Object> object);
}
