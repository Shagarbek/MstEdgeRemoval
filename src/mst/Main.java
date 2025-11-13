package mst;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 2, 1);
        g.addEdge(1, 3, 2);
        g.addEdge(2, 3, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 5, 6);

        List<Edge> mst = KruskalMST.buildMST(g);
        System.out.println("Original MST:");
        KruskalMST.printMST(mst);

        Edge removed = mst.remove(2);
        System.out.println("Removed edge: " + removed + "\n");

        List<List<Integer>> components = findComponents(g.V, mst);
        System.out.println("Components after removal:");
        for (List<Integer> comp : components) System.out.println("  " + comp);
        System.out.println();

        Edge replacement = findReplacementEdge(g, components);
        if (replacement != null) {
            System.out.println("Replacement edge found: " + replacement + "\n");
            mst.add(replacement);
        } else {
            System.out.println("No replacement edge available!");
        }

        System.out.println("New MST after reconnection:");
        KruskalMST.printMST(mst);
    }

    static List<List<Integer>> findComponents(int V, List<Edge> mst) {
        List<List<Integer>> comps = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (Edge e : mst) {
            adj.computeIfAbsent(e.src, k -> new ArrayList<>()).add(e.dest);
            adj.computeIfAbsent(e.dest, k -> new ArrayList<>()).add(e.src);
        }

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                dfs(i, visited, adj, comp);
                comps.add(comp);
            }
        }
        return comps;
    }

    static void dfs(int v, boolean[] visited, Map<Integer, List<Integer>> adj, List<Integer> comp) {
        visited[v] = true;
        comp.add(v);
        if (adj.containsKey(v)) {
            for (int nei : adj.get(v))
                if (!visited[nei])
                    dfs(nei, visited, adj, comp);
        }
    }

    static Edge findReplacementEdge(Graph g, List<List<Integer>> comps) {
        if (comps.size() != 2) return null;

        Set<Integer> compA = new HashSet<>(comps.get(0));
        Set<Integer> compB = new HashSet<>(comps.get(1));

        Edge best = null;
        for (Edge e : g.edges) {
            boolean connects = (compA.contains(e.src) && compB.contains(e.dest)) ||
                    (compA.contains(e.dest) && compB.contains(e.src));
            if (connects) {
                if (best == null || e.weight < best.weight)
                    best = e;
            }
        }
        return best;
    }
}
