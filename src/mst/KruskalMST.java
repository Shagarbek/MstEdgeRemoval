package mst;

import java.util.*;

public class KruskalMST {
    static class DisjointSet {
        int[] parent, rank;
        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) return;
            if (rank[xr] < rank[yr]) parent[xr] = yr;
            else if (rank[yr] < rank[xr]) parent[yr] = xr;
            else {
                parent[yr] = xr;
                rank[xr]++;
            }
        }
    }

    public static List<Edge> buildMST(Graph g) {
        List<Edge> result = new ArrayList<>();
        Collections.sort(g.edges);
        DisjointSet ds = new DisjointSet(g.V);

        for (Edge e : g.edges) {
            if (ds.find(e.src) != ds.find(e.dest)) {
                result.add(e);
                ds.union(e.src, e.dest);
            }
        }
        return result;
    }

    public static void printMST(List<Edge> mst) {
        int total = 0;
        System.out.println("MST edges:");
        for (Edge e : mst) {
            System.out.println("  " + e);
            total += e.weight;
        }
        System.out.println("Total weight: " + total + "\n");
    }
}
