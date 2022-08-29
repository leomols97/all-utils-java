package g56133.atl.SortingRace.model;

/**
 * This class allows to sort an array with the insertion sort method.
 *
 * @author Leong Paeg-Hing
 */
public class InsertionSort implements Sort{
    
    private String typeOfSort;
    private int[] array;
    private long nbOperation;
    private long begin;
    private long end;
    
    /**
     * Constructor of the class 
     * 
     * @param array the array that will be sort.
     */
    public InsertionSort(int[] array) {
        this.typeOfSort = "INSERTION_SORT";
        this.array = array;
    }
    
    /**
     * This insertion sort algorithm has been taken from 
     * https://www.geeksforgeeks.org/insertion-sort/
     */
    @Override
    public void Sort() {
        this.begin = System.nanoTime();
        int n = this.array.length;
        for (int i = 1; i < n; ++i) {
            int key = this.array[i];
            this.nbOperation++;
            int j = i - 1;

            while (j >= 0 && this.array[j] > key) {
                this.nbOperation++;
                this.array[j + 1] = this.array[j];
                this.nbOperation++;
                j = j - 1;
            }
            this.array[j + 1] = key;
            this.nbOperation++;
        }
        this.end = System.nanoTime();
    }

    @Override
    public long getNumberOfOperation() {
        return this.nbOperation;
    }

    @Override
    public long getBegin() {
        return this.begin;
    }

    @Override
    public long getEnd() {
        return this.end;
    }

    @Override
    public long getDuration() {
        return this.end - this.begin;
    }

    @Override
    public int getArraySize() {
        return this.array.length;
    }

    @Override
    public String getTypeOfSort() {
        return this.typeOfSort;
    }
}
