
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
public class MyConvexHull {
    private static int N, L;
    private static double radius = 0.01;
    private static Point2D[] a;
    
    public static String ConvexHullVertex(Point2D[] a) {
        String s = "";        
        // sorting
        Arrays.sort(a);
        Arrays.sort(a, a[0].POLAR_ORDER);
        
        int result, i1 = 0, i2 = 1, i3 = 2;
        LinkedList<Integer> list = new LinkedList<>();
        Point2D[] tmp = new Point2D[a.length + 2];
        for (int i = 0; i < a.length; i++)
            tmp[i] = a[i];
        tmp[tmp.length - 2] = a[0];
        tmp[tmp.length - 1] = a[1];
        while (i3 < tmp.length) {
            result = Point2D.ccw(tmp[i1], tmp[i2], tmp[i3]);
            // ccw
            if (result == 1) {
                if (list.isEmpty()) {
                    list.addLast(i1);
                }
                else {
                    if (list.getLast() != i1) {
                        list.addLast(i1);
                    }                    
                }
                i1 = i2;
                i2 = i3;
                i3++;
            }
            // cw or collinear
            else {
                if (list.isEmpty()) {
                    i2 = i3;
                    i3++;
                }
                else {
                    i2 = i1;
                    i1 = list.removeLast();
                }
            }
        }
        // display
        while (!list.isEmpty()) {
            s += list.removeFirst();
            s += " ";
        }
        
        return s;
    } 
    
    public static void main(String[] args) {
        N = Integer.valueOf(args[0]);
//        L = Integer.valueOf(args[1]);
        L = 512;
        a = new Point2D[N];
        
        // create window
        StdDraw.setCanvasSize(L, L);
        StdDraw.setScale(0, L);
        StdDraw.setPenRadius(radius);
        
        // create random points
        int x, y;
        for (int i=0; i<N; i++) {
            x = StdRandom.uniform(L);
            y = StdRandom.uniform(L);
            a[i] = new Point2D(x, y);
        }
        
        String s = "";
        // convex hull
        s = ConvexHullVertex(a);
                
        // draw line between points
        StdDraw.setPenColor(Color.green);
        StdDraw.setPenRadius();
        for (int i=1; i<a.length; i++)
            a[0].drawTo(a[i]);
        // draw red point
        StdDraw.setPenRadius(radius);
        StdDraw.setPenColor(Color.red);
        a[0].draw();
        // draw points
        StdDraw.setPenColor(Color.black);
        for (int i=1; i<a.length; i++)
            a[i].draw();
        // draw number
        StdDraw.setPenColor(Color.blue);
        for (int i=0; i<a.length; i++) {
            StdDraw.text(a[i].x(), a[i].y() + L*0.025, String.valueOf(i)); 
        }
        
        // draw convex hull
        String[] ss = s.split(" ");
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius();
        for (int i = 0; i < ss.length - 1; i++) {
            a[Integer.valueOf(ss[i])].drawTo(a[Integer.valueOf(ss[i + 1])]);
        }
        a[Integer.valueOf(ss[0])].drawTo(a[Integer.valueOf(ss[ss.length - 1])]);
        
        StdOut.print(s);
    }
}
