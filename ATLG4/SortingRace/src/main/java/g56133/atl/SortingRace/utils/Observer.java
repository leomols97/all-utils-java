package g56133.atl.SortingRace.utils; // dans un package util avec Observable

import java.util.List;

/**
 * Interface that indicate the methods that must be implemented to be an observer.
 * 
 * @author Leong Paeg-Hing
 */
public interface Observer {
    
    public void update(List<Object> object);
}
