/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AllUtils;

/**
 *
 * @author Leong Paeg-Hing
 */
public class ArrayUtils {
    /**
     * Search for a value in a sorted array using the binary search algorithm.
     * 
     * Binary Search (recherche dichotomique ou "par dichotomie").
     * 
     * The array is assumed to be sorted.
     * @param array The sorted array of values to search into.
     * @param value The value to search for.
     * 
     * @return The position of the value in the array if found, or -1 otherwise.
     */
    public static int binarySearch(int[] array, int value){
        int leftIndex = 0;
        int medianIndex = -1;
        int rightIndex = array.length - 1;
        int candidate;
        while (leftIndex <= rightIndex){
            medianIndex = (leftIndex + rightIndex)/2;
            candidate = array[medianIndex];
            if(candidate == value){
                return medianIndex;
            } else if(candidate > value){
                rightIndex = medianIndex -1;
            } else {
                leftIndex = medianIndex + 1;
            }
        }
        return -1;
    }
    
    public static int binaruSearch2(String[] array, String value){
        int leftIndex = 0;
        int medianIndex = -1;
        int rightIndex = array.length - 1;
        String candidate;
        int comp;
        while (leftIndex <= rightIndex){
            medianIndex = (leftIndex + rightIndex)/2;
            candidate = array[medianIndex];
            comp = candidate.compareTo(value);
            if(candidate == value){
                return medianIndex;
            } else if(comp > 0){
                rightIndex = medianIndex -1;
            } else { /* comp < 0 */
                leftIndex = medianIndex + 1;
            }
        }
        return -1;
    }
    
    public static void insertionSort(int[] array){
        // O(1 + 2 + 3 + 4 + ... + n) = O(n*(n+1)/2) = O(n^2)
        for(int nbSorted = 1; nbSorted < array.length; nbSorted++){
            int value = array[nbSorted];
            int pos = nbSorted -1;
            //Shift right till we found the insert position.
            while (pos >= 0 && value < array[pos]){ // O(nbSorted) 
                // swap values
                array[pos + 1] = array[pos];
                pos--;   
            }
            // Place the value at the right position.
            array[pos + 1] = value;
        }
    }
    
    public static void printArray(int[] array){
        System.out.print("[ ");
        for (int v : array){
            System.out.print(v + " ");
        }
        System.out.print("]");
    }
    
    public static void selectionSort(int[] array){
        for (int i = 0; i < array.length; i++){
            int minIdx = i;
            for (int j = i; j < array.length; j++){
                if (array[j] < array[minIdx]){
                    minIdx = j;
                }
            }
            int tmp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = tmp;
        }
    }
    
    public static void bubbleSort(int[] array){
        // Initialisation = O(1)
        // Chaque étape de la boucle est en O(array.length - 1 -i) (+ O(1) on peut le virer)
        // .. en O(array.length - i)
        // combien d'étape ?? -> array.length
        // O(array.length) + O(array.length - 1) + O(array.length -  2) + .. + O(2)
        // O(somme de 2 à array.length) = O(n*(n-1) / 2 - 1)
        // O(n^2/2 - n/2 -1) = O(n^2)
        // (O(1) = O(2))
        for (int i = 0; i < array.length -1; i++){
            // initialisation = O(1).
            // chauqe étape en boucle est du O(1).
            // La complexité du for dépend du nombre d'étape de la boucle.
            // Ici, il y en a (array.length - 1 - i)
            // C'est du O(array.length - 1 - i)
            // xxxxxxxxxxxx i i+1 i+2 i+3 ... array.length-1
            // xxxxxxxxxxxx 5 6 7 8 9 (array.length == 10, i = 5)
            boolean sorted = true;
            for (int bubblePos = array.length - 1;bubblePos > i; bubblePos --){
                if(array[bubblePos - 1] > array[bubblePos]){ // O(1)
                    int tmp = array[bubblePos]; //O(1)
                    array[bubblePos] = array[bubblePos - 1]; //O(1) (en fonction de la taille de array)
                    array[bubblePos - 1] = tmp; // O(1)
                }
            }
            if (sorted)
                return;
        }
        
    }
    
    public static void bubbleSortAlt(int[] array){
        boolean sorted = false;
        int i = 0;
        while(!sorted){
            // Faire remonter la bulle
            sorted = true;
            for(int bubblePos = array.length - 1; bubblePos > i; --bubblePos){
                // Swap in the bubble
                int tmp = array[bubblePos];
                array[bubblePos] = array[bubblePos - 1];
                array[bubblePos - 1] = tmp;
                sorted =false;
            }
        }
        i++;
    }
    
    public static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    public static void swap(String[] array, int i, int j){
        String tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
    
    public static void main(String[] args) {
        int[] pog = {1, 5, 7, 9, 15, 21, 56};
        
    }
    
    public static int[] bubbleSort2(int[] array){
        for(int bubbleIndex = 0; bubbleIndex < array.length - 1; bubbleIndex++){
            for(int i = array.length - 2; i >= bubbleIndex; i--){
                if(array[i] > array[i+1]){
                    int value = array[i];
                    array[i] = array[i+1];
                    array[i+1] = value;
                }
            }
        }
        return array;
    }
    
    public static void insertionInArray(int[] array, int nbValues, int value){
        int pos = findPosition(array, nbValues, value);
        shiftRight(array, nbValues, pos);
        array[pos] = value;
    }
    
    public static int findPosition(int[] array, int nbValues, int value){
        for(int i = 0; i < nbValues; i++){
            if(value < array[i]){
                return i;
            }
        }
        return -1;
    }
    
    public static void shiftRight(int[] array, int nbValues, int position){
        for(int i = nbValues -1; i >= position; i--){
            array[i + 1] = array[i];
        }
    }
    
}
