
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tony
 */
public class MyMST {

    public static void main(String[] args) {
        In in = new In(args[0]);
        int V = in.readInt();
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }

        // read data
        KdTree kdtree = new KdTree();
        Point2D[] pts = new Point2D[V];
        SeparateChainingHashST hash = new SeparateChainingHashST();
        for (int i = 0; i < V; i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            pts[i] = p;
            hash.put(p, i);
        }

//        Point2D[] pts = new Point2D[V];
//        for (int i = 0; i < V; i++) {
//            double x = in.readDouble();
//            double y = in.readDouble();
//            pts[i] = new Point2D(x, y);
//        }
        
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        double band = 2/Math.sqrt(V);
        for (int i = 0; i < pts.length; i++) {
            RectHV r = new RectHV(pts[i].x() - band, pts[i].y() - band,
                    pts[i].x() + band, pts[i].y() + band);
            Iterator itr = kdtree.range(r).iterator();
            while (itr.hasNext()) {
                Point2D p = (Point2D) itr.next();
                if (p.equals(pts[i]))
                    continue;
                Edge e = new Edge(i, (int) hash.get(p), pts[i].distanceTo(p));
                G.addEdge(e);
//                StdOut.println(e);
            }
        }

//        for (int i = 0; i < V; i++) {
//            for (int j = i + 1; j < V; j++) {
//                Edge e = new Edge(i, j, pts[i].distanceTo(pts[j]));
//                G.addEdge(e);
//            }
//        }
        KruskalMST mst = new KruskalMST(G);
        StdOut.println(String.format("%.5f", mst.weight()));

//        // Draw
//        StdDraw.setPenRadius(0.01);
//        for (int i = 0; i < V; i++) {
//            pts[i].draw();
//        }
//        StdDraw.setPenRadius();
//        for (Edge e : mst.edges()) {
////            StdOut.println(e);
//            String splitString = e.toString();
//            String[] id = splitString.split("-");
//            String[] id1 = id[1].split(" ");
//            int m = Integer.valueOf(id[0]);
//            int n = Integer.valueOf(id1[0]);
//            pts[m].drawTo(pts[n]);
//        }
    }
}
