/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public class MyDoublingRatio {
    // This class should not be instantiated.
    private MyDoublingRatio() { }

    /**
     * Returns the amount of time to call <tt>ThreeSum.count()</tt> with <em>N</em>
     * random 6-digit integers.
     * @param N the number of integers
     * @return amount of time (in seconds) to call <tt>ThreeSum.count()</tt>
     *   with <em>N</em> random 6-digit integers
     */
    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int cnt = ThreeSum.count(a);
        return timer.elapsedTime();
    }
    
    public static double timeTrial(int[] a) {
        Stopwatch timer = new Stopwatch();
//        int cnt = ThreeSum.count(a);
        int cnt = ThreeSumFast.count(a);
        return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call <tt>ThreeSum.count()</tt>
     * for arrays of size 250, 500, 1000, 2000, and so forth, along
     * with ratios of running times between successive array sizes.
     * @param args
     */
    public static void main(String[] args) { 
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        double prev = timeTrial(a);
        StdOut.printf("%6d %7.1f\n", a.length, prev);
        
//        double prev = timeTrial(125);
//        for (int N = 250; true; N += N) {
//            double time = timeTrial(N);
//            StdOut.printf("%6d %7.1f %5.1f\n", N, time, time/prev);
//            prev = time;
//        } 
    } 
}
