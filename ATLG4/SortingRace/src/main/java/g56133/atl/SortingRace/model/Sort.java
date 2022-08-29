package g56133.atl.SortingRace.model;

/**
 *
 * @author Leong Paeg-Hing
 */
public interface Sort {
    
    /**
     * Sort an array. The type of sorting depends of the implementation 
     * of the sort.
     */
    public void Sort(); // en minuscule le nom de méthode.
    
    /**
     * Get the number of operation needed to sort the array.
     * 
     * @return the number of sort needed.
     */
    public long getNumberOfOperation();
    
    /**
     * Get the moment when the sorting started.
     * 
     * @return the start.
     */
    public long getBegin();
    
    /**
     * Get the moment when the sorting ended.
     * 
     * @return the end.
     */
    public long getEnd();
    
    /**
     * Get the time needed to sort the array.
     * 
     * @return the duration of the sorting.
     */
    public long getDuration();
    
    /**
     * Get the size of the array sorted.
     * 
     * @return the size of the array.
     */
    public int getArraySize();
    
    /**
     * Get the type of sorting.
     * 
     * @return the type of sorting.
     */
    public String getTypeOfSort();
}
