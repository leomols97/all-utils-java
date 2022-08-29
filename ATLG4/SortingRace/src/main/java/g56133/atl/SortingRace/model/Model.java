package g56133.atl.SortingRace.model;

/**
 * Interface that indicates the methods that must be implemented in order to do the sorts.
 * 
 * @author Leong Paeg-Hing
 */
public interface Model {
    
    /**
     * Create the threads that will sort the arrays and launch them.
     * 
     * @param sort the type of sorting.
     * @param nbElems the number of elements that will be sorted.
     * @param nbThreads the number maximum of thread that can run simultaneously.
     * @param step number that indicates the increase in size for the array 
     * that will be sorted. 
     */
    public void start(TypeOfSort sort, int nbElems, int nbThreads, int step);
}
