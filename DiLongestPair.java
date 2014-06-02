/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public class DiLongestPair {
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        int Smax = 0;
        for (int s = 0; s < G.V(); s++) {
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);
            for (int v = 0; v < G.V(); v++) {
                if (bfs.hasPathTo(v)) {
                    if (bfs.distTo(v) > Smax)
                        Smax = bfs.distTo(v);
                }
            }
        }
        StdOut.println(Smax);
    }
}
