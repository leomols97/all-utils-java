package g56133.atl.SortingRace.model;

import g56133.atl.SortingRace.utils.Observable;
import g56133.atl.SortingRace.utils.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Thiss class create all the threads and sort.
 *
 * @author Leong Paeg-Hing
 */
public class Facade implements Model, Observer, Observable {

    private ArrayList<Observer> observer;
    private Pool pool;
    private Data data;

    /**
     * Constructor of the class.
     */
    public Facade() {
        this.observer = new ArrayList<>();
    }

    @Override
    public void start(TypeOfSort sort, int nbElems, int nbThreads, int step) {
        this.data = new Data(nbElems, step);
        this.pool = new Pool(nbThreads);
        createThread(sort);
        this.pool.start();
    }

    /**
     * Create the differents thread that will run to sort the array.
     *
     * @param typeOfsort the type of sorting.
     */
    public void createThread(TypeOfSort typeOfsort) {
        for (int i = 0; i < this.data.getNumberOfArray(); i++) {
            Sort sort = createSort(typeOfsort, i);
            SortThread thread = new SortThread(sort);
            thread.registerObserver(this);
            this.pool.addThreads(thread);
        }
    }

    /**
     * Create the sort with an array from the data object.
     *
     * @param sort the type of sorting.
     * @param arrayNumber the n array from the object data that will be given to
     * the sort.
     * @return an object sort.
     */
    public Sort createSort(TypeOfSort sort, int arrayNumber) {
        switch (sort) {
            case BUBBLE:
                return new BubbleSort(this.data.getArray(arrayNumber));
            case MERGE:
                return new MergeSort(this.data.getArray(arrayNumber));
            case QUICK:
                return new QuickSort(this.data.getArray(arrayNumber));
            case INSERTION:
                return new InsertionSort(this.data.getArray(arrayNumber));
            default:
                return null;
        }
    }

    @Override
    public void update(List<Object> object) {
        notifyObserver(object);
        this.pool.startNext();
    }

    @Override
    public void registerObserver(Observer observer) {
        this.observer.add(observer);
    }

    @Override
    public void notifyObserver(List<Object> object) {
        for (Observer o : this.observer) {
            object.add(pool.IsCompleted());
            o.update(object);
        }
    }

}
