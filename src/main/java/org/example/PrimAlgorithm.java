package org.example;

import java.util.*;

/**
 * Classic Prim's algorithm (eager variant using a min-heap).
 * - Starts from the first vertex in the input order.
 * - Grows the MST by repeatedly selecting the minimum outgoing edge.
 * - Tracks operations (comparisons and PQ operations).
 */
public class PrimAlgorithm {
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

        int n = g.n();
        if (n == 0) return new Result(Collections.emptyList(), 0, op.get(), 0);

        boolean[] used = new boolean[n];

        // Comparator that bumps the operation counter for each comparison.
        Comparator<Edge> cmp = (e1, e2) -> {
            op.compare();
            return Integer.compare(e1.weight, e2.weight);
        };
        PriorityQueue<Edge> pq = new PriorityQueue<>(cmp); // min-heap by weight

        List<Edge> mst = new ArrayList<>();
        long cost = 0;

        // Start from vertex 0 (first in the input list)
        used[0] = true;
        // Push all outgoing edges of the start vertex
        for (Edge e : g.getAdj().get(0)) { pq.add(e); op.pqOp(); }

        // While we haven’t connected all vertices and heap is not empty
        while (!pq.isEmpty() && mst.size() < n - 1) {
            Edge e = pq.poll(); op.pqOp();
            int v = g.indexOf(e.to);
            if (used[v]) continue; // skip obsolete edges to already-visited vertices

            // Accept this edge into MST
            mst.add(e);
            cost += e.weight;
            used[v] = true;

            // Push new frontier edges from vertex v
            for (Edge ne : g.getAdj().get(v)) {
                int to = g.indexOf(ne.to);
                op.compare();                 // comparison for the "if (!used[to])"
                if (!used[to]) { pq.add(ne); op.pqOp(); }
            }
        }

        long t1 = System.nanoTime();
        return new Result(mst, cost, op.get(), (t1 - t0) / 1_000_000.0);
    }
}
