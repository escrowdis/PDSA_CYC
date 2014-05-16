
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public class MyMultiConvexHull {
    private static int N, L;
    private static double radius = 0.02, dis_thresh = 100.0;
    private static Point2D[] a;
    
     public static void main(String[] args) {        
        N = Integer.valueOf(args[0]);
//        L = Integer.valueOf(args[1]);
        L = 512;
        a = new Point2D[N];
        
        // create window
        StdDraw.setCanvasSize(L, L);
        StdDraw.setScale(0, L);
        
        // create random points
        int x, y;
        for (int i=0; i<N; i++) {
            x = StdRandom.uniform(L);
            y = StdRandom.uniform(L);
            a[i] = new Point2D(x, y);
        }
        
        // draw points
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(radius);
        for (int i=0; i<a.length; i++)
            a[i].draw();
        
        String s = "";
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        double dis;
        // draw line between points
        StdDraw.setPenColor(Color.green);
        StdDraw.setPenRadius();
        // union find
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                dis = a[i].distanceTo(a[j]);
                if (dis < dis_thresh) {
                    a[i].drawTo(a[j]);
                    if (uf.connected(i, j)) continue;
                    uf.union(i, j);
                }
            }
        }
        
        // convex hull
        int count = 0;
        
        int[] check = new int[N];
        for (int i = 0; i < N; i++) 
            check[i] = 1;
        for (int i = 0; i < N; i++) {
            if (check[i] == 1) {
                count = 0;
                for (int j = i; j < N; j++) {
                    if (uf.connected(i, j)) {
                        count++;                    
                    }
                }
                Point2D[] a_group = new Point2D[count];
                count = 0;
                for (int j = i; j < N; j++) {
                    if (uf.connected(i, j)) {
                        a_group[count] = a[j];
                        check[j] = -1;
                        count++;
                    }
                }

                if (a_group.length >= 3) {
                    s = MyConvexHull.ConvexHullVertex(a_group);
                    // draw red point
                    StdDraw.setPenRadius(radius);
                    StdDraw.setPenColor(Color.red);
                    a_group[0].draw();            

                    // draw convex hull
                    String[] ss = s.split(" ");
                    StdDraw.setPenColor(Color.black);
                    StdDraw.setPenRadius();
                    for (int j = 0; j < ss.length - 1; j++) {
                        a_group[Integer.valueOf(ss[j])].drawTo(a_group[Integer.valueOf(ss[j + 1])]);
                    }
                    a_group[Integer.valueOf(ss[0])].drawTo(a_group[Integer.valueOf(ss[ss.length - 1])]);
                }
            }
            
        }           
     }
}
