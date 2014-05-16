
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public class MyTwoSumFast {    
    /**
     * Returns if the array is NOT sorted
     * @param a the array of inttegers
     * @return 
     */
    private static boolean haveNotSorted(int[] a) { 
        for(int i=1; i<a.length; i++)
            if(a[i] < a[i-1])
                return true;
        return false;
    }
    /**
     * Returns true if the sorted array a[] contains any duplicated integers
     * @param a the array of inttegers
     * @return 
     */
    private static boolean containsDuplicates(int[] a) { 
        for(int i=1; i<a.length; i++)
            if(a[i] == a[i-1])
                return true;
        return false;
    }
    /**
     * Returns the number of pairs
     * @param a the array of inttegers
     * @return 
     */
    public static int count(int[] a) { 
        if(haveNotSorted(a))
            throw new IllegalArgumentException("array has not sorted in ascending order");
        if(containsDuplicates(a))
            throw new IllegalArgumentException("array contains duplicate integers");
        int pair = 0;
        int N = a.length;
        int tmp = N-1;
        for(int i=0; i<N; i++){
            for(int j=tmp; j>i; j--){
                if(a[i] + a[j] == 0)
                    pair++;
                if(a[i] + a[j] < 0){
                    tmp = j;
                    break;
                }                
            }            
        }
        
        return pair;
    }
    /**
     * 
     * @param args input file
     */
    public static void main(String[] args) { 
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        int pair = count(a);
        StdOut.println(pair);
    }
    
}
