package org.example;

import java.util.*;

public class MstValidator {

    /** Проверка: MST содержит n-1 рёбер и образует связное дерево. */
    public static void validate(Graph g, List<Edge> mstEdges) {
        int n = g.n();
        if (n == 0) return;
        if (mstEdges.size() != n - 1) {
            throw new IllegalStateException("MST must have n-1 edges, got " + mstEdges.size());
        }
        Map<String, List<String>> adj = new HashMap<>();
        for (String v : g.getNodes()) adj.put(v, new ArrayList<>());
        for (Edge e : mstEdges) {
            adj.get(e.from).add(e.to);
            adj.get(e.to).add(e.from);
        }
        Set<String> vis = new HashSet<>();
        Deque<String> dq = new ArrayDeque<>();
        String start = g.getNodes().get(0);
        vis.add(start); dq.add(start);
        while (!dq.isEmpty()) {
            String u = dq.poll();
            for (String w : adj.get(u)) if (vis.add(w)) dq.add(w);
        }
        if (vis.size() != n) {
            throw new IllegalStateException("MST is not connected: visited=" + vis.size() + " of " + n);
        }
    }

    public static long totalCost(List<Edge> edges) {
        long c = 0;
        for (Edge e : edges) c += e.weight;
        return c;
    }
}
