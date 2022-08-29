package g56133.atl.SortingRace.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to generate an array of random number and slice it in 
 * smaller part.
 * 
 * @author Leong Paeg-Hing
 */
public class Data {
    
    private ArrayList<int[]> listOfArray;
    private int[] randomArray;
    private int step;
    
    /**
     * Constructor of the class.
     * 
     * Generate an array of random number.
     * 
     * @param nbElems the number of elements in the array.
     * @param step the step.
     */
    public Data(int nbElems, int step) {
        this.listOfArray = new ArrayList<>();
        this.step = step;
        Random rd = new Random();
        this.randomArray = new int[nbElems];
        for (int i = 0; i < this.randomArray.length; i++) {
            this.randomArray[i] = rd.nextInt();
        }
        sliceArray();
    }
    
    /**
     * Slice the original array in different smaller array.
     * The size of the smaller array increase based on the step.
     */
    private void sliceArray() {
        int currentStep = 0;
        for(int i = 0; i < 11; i++) {
            int [] tmpArray = new int[currentStep];
            for(int j = 0; j < currentStep; j++) {
                tmpArray[j] = this.randomArray[j];
            }
            this.listOfArray.add(tmpArray);
            currentStep += this.step;
        }
    }
    
    /**
     * Get the n array.
     * 
     * @param index the n array in the list of array.
     * @return the array at the index.
     */
    public int[] getArray(int index) {
        return this.listOfArray.get(index);
    }
    
    /**
     * Get the number of array in the list.
     * 
     * @return 
     */
    public int getNumberOfArray() {
        return this.listOfArray.size();
    }
}
