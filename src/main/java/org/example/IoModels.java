package org.example;

import java.util.List;

/**
 * DTO classes for JSON input/output.
 * These classes mirror the structure of your input and expected output files.
 * Gson will serialize/deserialize them automatically by field names.
 */

// ----- INPUT -----

// One graph entry in the input file
class InputGraph {
    int id;                 // graph identifier
    List<String> nodes;     // list of vertex names
    List<InputEdge> edges;  // edges with weights
}

// Edge entry used in the input JSON
class InputEdge {
    String from;
    String to;
    int weight;
}

// Root object of the input JSON file
class InputRoot {
    List<InputGraph> graphs;
}

// ----- OUTPUT -----

// Root object for the output JSON file
class OutputRoot {
    List<GraphResult> results;
}

// One result block per input graph
class GraphResult {
    int graph_id;
    InputStats input_stats;
    AlgoResult prim;
    AlgoResult kruskal;
}

// Basic input statistics for reporting
class InputStats {
    int vertices;
    int edges;
}

// Algorithm result section (used for both Prim and Kruskal)
class AlgoResult {
    List<OutEdge> mst_edges;
    long total_cost;
    long operations_count;
    double execution_time_ms;
}

// Edge DTO used in the output JSON
class OutEdge {
    String from;
    String to;
    int weight;

    OutEdge(String f, String t, int w) {
        this.from = f; this.to = t; this.weight = w;
    }
}
