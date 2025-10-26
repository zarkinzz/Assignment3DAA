package org.example;

import java.util.*;

/**
 * Classic Kruskal's algorithm.
 * - Sort all edges by weight (ascending).
 * - Iterate edges; include an edge if it connects two different components (DSU).
 * - Track comparisons (sort) and DSU operations.
 */
public class KruskalAlgorithm {
    public static class Result {
        public final List<Edge> mstEdges;   // edges selected for the MST
        public final long totalCost;        // sum of weights
        public final long operations;       // counted algorithmic actions
        public final double execTimeMs;     // wall-clock time in milliseconds

        public Result(List<Edge> mstEdges, long totalCost, long operations, double execTimeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operations = operations;
            this.execTimeMs = execTimeMs;
        }
    }

    public static Result run(Graph g) {
        OperationCounter op = new OperationCounter();
        long t0 = System.nanoTime();

        // 1) Sort edges by weight; count comparisons through the comparator.
        List<Edge> sorted = new ArrayList<>(g.getEdges());
        sorted.sort((a, b) -> {
            op.compare();
            return Integer.compare(a.weight, b.weight);
        });

        // 2) Prepare DSU over vertex indices
        UnionFind uf = new UnionFind(g.n(), op);

        // Build name->index map once for fast lookup
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < g.n(); i++) idx.put(g.nameOf(i), i);

        List<Edge> mst = new ArrayList<>();
        long cost = 0;

        // 3) Scan edges in ascending weight order
        for (Edge e : sorted) {
            int u = idx.get(e.from);
            int v = idx.get(e.to);
            // If union succeeds, edge connects two different components => add to MST
            if (uf.union(u, v)) {
                mst.add(e);
                cost += e.weight;
                if (mst.size() == g.n() - 1) break; // Early exit: MST is complete
            }
        }

        long t1 = System.nanoTime();
        return new Result(mst, cost, op.get(), (t1 - t0) / 1_000_000.0);
    }
}
