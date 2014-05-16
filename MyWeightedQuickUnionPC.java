/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tony
 */
public class MyWeightedQuickUnionPC {
    private int[] id;
    private int[] sz;
    private int count;
    
    /**
     * Initializes an empty union-find data structure with N isolated components
     * @param N number of objects
     */
    public MyWeightedQuickUnionPC(int N){
        count = N;
        id = new int[N];
        sz = new int[N];
        for(int i=0; i<N; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }
    
    /**
     * 
     * @param p
     * @return 
     */
    public int find(int p){
        while (p != id[p])
            p = id[p];
        
        return p;
    }
    
    /**
     * 
     * @param p
     * @param q
     * @return 
     */
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    
    /**
     * 
     * @param p
     * @param q 
     */
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ){
            return;
        }
        int root_new;
        if(sz[rootP] < sz[rootQ]){
            id[rootP] = id[rootQ];
            sz[rootQ] +=sz[rootP];
            root_new = rootQ;
        }
        else{
            id[rootQ] = id[rootP];
            sz[rootP] +=sz[rootQ];
            root_new = rootP;
        }
        
        while(id[p] != root_new){
            int tmp = id[p];
            id[p] = root_new;
            p = tmp;
        }
        while(id[q] != root_new){
            int tmp = id[q];
            id[q] = root_new;
            q = tmp;
        }
        
        count--;
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args){
        In in = new In(args[0]);
        int N = in.readInt();
        MyWeightedQuickUnionPC pc = new MyWeightedQuickUnionPC(N);
        while(!in.isEmpty()){
            int p = in.readInt();
            int q = in.readInt();
            if(pc.connected(p, q))
                continue;
            pc.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(pc.count + " components");
        for(int i=0; i<N; i++){
            StdOut.println(pc.id[i]);
        }
    }
}
