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
public class InscriptionTableau {
    /**
     * Returns the position of the student's number in the registered array.
     * @param registered
     * @param nRegistered
     * @param number
     * @return the position if found or -1 if not
     */
    public static int find(int[] registered, int nRegistered, int number){
        int i = findPosition(registered, nRegistered, number);
        if (i < registered.length && registered[i] == number) {
            return i;
        } else {
            return -1;
        }
    }
    
    /**
     * return the position where the student's number should be inserted.
     * @param registered
     * @param nRegistered
     * @param number
     * @return the position.
     */
    public static int findPosition(
            int[] registered, int nRegistered, int number){
        int i = 0;
        while(i < nRegistered && registered[i] != number){
            i++;
        }
        if (i < nRegistered){
            return i;
        } else {
            return i++;
        }
    }
    
    public static void shiftLeft(int[] registered, int nRegistered, int pos){
        // pos < nRegistered
        for (int i = pos; i < nRegistered ; i++){
            registered[i] = registered[i-1];
        }
    }
    
    public static void shiftRight(int[] registered, int nRegistered, int pos){
        if(nRegistered >= registered.length){
            throw new IllegalArgumentException("I would shift outside of the array");
        }
        for (int i = nRegistered; i > pos  ;i--){
            registered[i] = registered[i-1];
        }
    }
    
    /**
     * Register a student
     * 
     * The array of registration (the argument registered) is modified.
     * @param registered the array of registration.
     * @param nRegistered the number of registered student
     * before teh registration.
     * @param number the number of registered students
     * @return the number of registered students.
     */
    public static int register(int[] registered, int nRegistered, int number){
        int i = findPosition((registered), nRegistered, number);
        if (check(registered, nRegistered, number) != -1){
            throw new IllegalArgumentException("Student already registered");
        }
        
        //Check if the registration array is big enough to allow for
        // one more student.
        if(registered.length == nRegistered){
            throw new IllegalArgumentException(
                    "The registration array is full");
        }
        shiftRight(registered, nRegistered, i);
        
        registered[i] = number;
        return nRegistered++;
    }
    
    /**
     * Checks whether the student number is already registered.
     * 
     * @param registered the registration array.
     * @param nRegistered the number of registered students.
     * @param number the student's number.
     * @return -1 if the student isn't registered yet, and its position in the 
     * registrations array.
     */
    public static int check(int[] registered, int nRegistered, int number){
        int i = findPosition(registered, nRegistered, number);
        if (i < registered.length && registered[i] == number){
            return i;
        } else {
            return -1;
        }
    }
    
    /**
     * Unsubscribes the student number.
     * 
     * @param registered the registration array.
     * @param nRegistered the number of registered student.
     * @param number the student's number.
     * @return 
     */
    public static int unsubscribe(
            int[] registered, int nRegistered, int number){
        int pos = findPosition(registered, nRegistered, number);
        if(pos >= registered.length && registered[pos] != number){
            throw new IllegalArgumentException(
                    "Cannot unsubscribe an unregistered student");
        }
        //on assume pos >= 0
        shiftLeft(registered, nRegistered, pos);
        return nRegistered - 1;
    }
    
}
