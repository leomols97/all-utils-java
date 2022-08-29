package g56133.atl.SortingRace.model;

/**
 * This class allows to sort an array with the quick sort method.
 *
 * @author Leong Paeg-Hing
 */
public class QuickSort implements Sort {

    private String typeOfSort;
    private int[] array;
    private long nbOperation;
    private long begin;
    private long end;

    public QuickSort(int[] array) {
        this.typeOfSort = "QUICK_SORT";
        this.array = array;
    }

    /**
     * This quick sort algorithm has been taken from
     * https://www.geeksforgeeks.org/quick-sort/
     */
    @Override
    public void Sort() {
        this.begin = System.nanoTime();
        quickSort(0, this.array.length - 1);
        this.end = System.nanoTime();
    }

    private void quickSort(int low, int high) {
        if (low < high) {

            int pi = partition(low, high);

            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = this.array[high];
        this.nbOperation++;

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (this.array[j] < pivot) {
                this.nbOperation++;
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    private void swap(int i, int j) {
        int swapTemp = this.array[i];
        this.nbOperation++;
        this.array[i] = this.array[j];
        this.nbOperation++;
        this.array[j] = swapTemp;
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
