package g56133.atl.SortingRace.model;

/**
 * This class allows to sort an array with the merge sort method.
 * 
 * @author Leong Paeg-Hing
 */
public class MergeSort implements Sort {

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
    public MergeSort(int[] array) {
        this.typeOfSort = "MERGE_SORT";
        this.array = array;
    }
    
    /**
     * This merge sort algorithm has been taken from
     * https://www.baeldung.com/java-merge-sort
     */
    @Override
    public void Sort() {
        this.begin = System.nanoTime();
        mergeSort(this.array, this.array.length);
        this.end = System.nanoTime();
    }
    
    /**
     * Sort the array with the merge sort algorithm.
     * 
     * @param a the array that will be sort..
     * @param n the size of the array.
     */
    public void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
            this.nbOperation++;
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
            this.nbOperation++;
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    
    /**
     * 
     * @param a the array.
     * @param l the left side of the array.
     * @param r the right side of the array.
     * @param left the size of the left array.
     * @param right the size of the right array
     */
    public void merge(
            int[] a, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                this.nbOperation++;
                a[k++] = l[i++];
                this.nbOperation++;
            } else {
                a[k++] = r[j++];
                this.nbOperation++;
            }
        }
        while (i < left) {
            a[k++] = l[i++];
            this.nbOperation++;
        }
        while (j < right) {
            a[k++] = r[j++];
            this.nbOperation++;
        }
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
