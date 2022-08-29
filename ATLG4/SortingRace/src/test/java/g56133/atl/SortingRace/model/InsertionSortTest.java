/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.atl.SortingRace.model;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Leong Paeg-Hing
 */
public class InsertionSortTest {
    
    public InsertionSortTest() {
    }

    @Test
    public void testBubbleSort() {
        Random rd = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt();
        }
        InsertionSort sort = new InsertionSort(arr);
        sort.Sort();
        for(int i : arr) {
            System.out.println(i);
        }
    }
    
}
