package g56133.atl.SortingRace.model;

/**
 * This class allows to sort an array with the Bubble Sort method.
 * 
 * @author Leong Paeg-Hing
 */
public class BubbleSort implements Sort {

    private String typeOfSort;
    private int[] array;
    private long nbOperation;
    private long begin;
    private long end;
    
    /**
     * Constructor of the class. 
     * 
     * @param array the array that will be sort.
     */
    public BubbleSort(int[] array) {
        this.typeOfSort = "BUBBLE_SORT";
        this.array = array;
    }
    
    /**
     * This bubble sort algorithm has been taken from 
     * https://www.geeksforgeeks.org/bubble-sort/
     */
    @Override
    public void Sort() {
        this.begin = System.nanoTime();
        int n = this.array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                this.nbOperation++;
                if (this.array[j] > this.array[j + 1])
                {
                    swap(j);
                }
            }
        }
        this.end = System.nanoTime();
    }
    
    /**
     * Swap two values in an array.
     * 
     * @param j the index.
     */
    private void swap(int j) {
        int temp = this.array[j];
        this.nbOperation++;
        this.array[j] = this.array[j + 1];
        this.nbOperation++;
        this.array[j + 1] = temp;
        this.nbOperation++;
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
