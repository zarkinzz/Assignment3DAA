package org.example;

import java.util.*;

/**
 * In-memory graph model used by algorithms.
 * Stores:
 *  - ordered list of vertex names (to preserve input order)
 *  - global list of edges
 *  - adjacency lists for fast neighborhood access
 * Also provides name<->index mapping for O(1) lookups.
 */
public class Graph {
    private final List<String> nodes;                 // vertex names in a stable order
    private final Map<String, Integer> idxOf;         // vertex name -> index in [0..n)
    private final List<Edge> edges;                   // all edges as provided by input
    private final List<List<Edge>> adj;               // adjacency list (undirected)

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = new ArrayList<>(nodes);
        this.idxOf = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) idxOf.put(nodes.get(i), i);

        this.edges = new ArrayList<>(edges);

        // Create empty adjacency lists
        this.adj = new ArrayList<>(Collections.nCopies(nodes.size(), null));
        for (int i = 0; i < nodes.size(); i++) adj.set(i, new ArrayList<>());

        // Fill adjacency lists. Since the graph is undirected, add symmetric entries.
        for (Edge e : edges) {
            Integer u = idxOf.get(e.from);
            Integer v = idxOf.get(e.to);
            if (u == null || v == null) {
                throw new IllegalArgumentException("Edge uses unknown node: " + e);
            }
            adj.get(u).add(e);                                   // u -> v (as given)
            adj.get(v).add(new Edge(e.to, e.from, e.weight));    // v -> u (mirror)
        }
    }

    // Basic accessors
    public int n() { return nodes.size(); }
    public int m() { return edges.size(); }
    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }
    public List<List<Edge>> getAdj() { return adj; }

    // Name/index conversion helpers
    public int indexOf(String node) { return idxOf.get(node); }
    public String nameOf(int idx) { return nodes.get(idx); }
}
