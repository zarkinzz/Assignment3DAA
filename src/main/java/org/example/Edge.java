package org.example;

/**
 * Immutable representation of an undirected edge between two named vertices.
 * We keep vertex names as strings to match JSON input directly.
 */
public class Edge {
    public final String from;   // name of the source vertex
    public final String to;     // name of the destination vertex
    public final int weight;    // non-negative edge weight (road construction cost)

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + from + " - " + to + ", w=" + weight + ")";
    }
}
