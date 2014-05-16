/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public class MyThreeSumQuadratic {
    private static boolean haveNotSorted(int[] a) { 
        for (int i=1; i<a.length; i++)
            if (a[i] < a[i-1])
                return true;
        return false;
    }
    
    private static boolean containsDuplicates(int[] a) { 
        for (int i=1; i<a.length; i++)
            if (a[i] == a[i-1])
                return true;
        return false;
    }
    
    public static int count(int[] a) { 
        if (haveNotSorted(a))
            throw new IllegalArgumentException("array has not sorted in ascending order");
        if (containsDuplicates(a))
            throw new IllegalArgumentException("array contains duplicate integers");
        int pair = 0;
        int N = a.length;
        int k, j, sum;
        for (int i=0; i<N - 3; i++) {
            k = i + 1;
            j = N - 1;
            while (k < j) {
                sum = a[i] + a[j] + a[k];
                if (sum == 0) {
                    pair++;
                    j--;
                }
                else if (sum < 0)
                    k++;
                else
                    j--;
            }
        } 
        
        return pair;
    }
    
    public static void main(String[] args) { 
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        int pair = count(a);
        StdOut.println(pair);
    }
}
