package org.example;

/**
 * Disjoint Set Union (Union-Find) with path compression and union by rank.
 * Used by Kruskal's algorithm to detect cycles efficiently.
 */
public class UnionFind {
    private final int[] parent;     // parent representative
    private final int[] rank;       // tree rank (approximate height)
    private final OperationCounter op; // shared operation counter

    public UnionFind(int n, OperationCounter op) {
        this.parent = new int[n];
        this.rank = new int[n];
        this.op = op;
        for (int i = 0; i < n; i++) parent[i] = i;   // each set is its own parent initially
    }

    // Find with path compression; we increment "find" on each entry
    private int find(int x) {
        op.find();
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    /**
     * Union two sets; returns true if union actually merged two components,
     * false if they were already in the same set.
     */
    public boolean union(int a, int b) {
        op.union();
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return false;

        // Attach smaller rank tree under larger rank tree
        if (rank[ra] < rank[rb]) {
            parent[ra] = rb;
        } else if (rank[ra] > rank[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank[ra]++;
        }
        return true;
    }

    // Convenience: are two elements in the same set?
    public boolean same(int a, int b) {
        return find(a) == find(b);
    }
}
