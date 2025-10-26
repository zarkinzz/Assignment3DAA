package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON I/O utilities (Gson-based).
 * - readInput: deserializes input file into InputRoot
 * - writeOutput: serializes OutputRoot into a pretty-printed JSON file
 * - helpers convert InputGraph -> Graph and wrap algorithm results into DTOs
 */
public class JsonHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /** Read and parse input JSON. */
    public static InputRoot readInput(String path) throws IOException {
        try (Reader r = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {
            return GSON.fromJson(r, InputRoot.class);
        }
    }

    /** Write output JSON with pretty printing. */
    public static void writeOutput(String path, OutputRoot out) throws IOException {
        try (Writer w = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8)) {
            GSON.toJson(out, w);
        }
    }

    /** Convert input DTO to internal Graph model. */
    public static Graph toGraph(InputGraph ig) {
        List<Edge> edges = new ArrayList<>();
        for (InputEdge e : ig.edges) {
            edges.add(new Edge(e.from, e.to, e.weight));
        }
        return new Graph(ig.nodes, edges);
    }

    /** Wrap Prim's result into output DTO. */
    public static AlgoResult toAlgoResultPrim(PrimAlgorithm.Result r) {
        AlgoResult a = new AlgoResult();
        a.mst_edges = new ArrayList<>();
        for (Edge e : r.mstEdges) a.mst_edges.add(new OutEdge(e.from, e.to, e.weight));
        a.total_cost = r.totalCost;
        a.operations_count = r.operations;
        a.execution_time_ms = r.execTimeMs;
        return a;
    }

    /** Wrap Kruskal's result into output DTO. */
    public static AlgoResult toAlgoResultKruskal(KruskalAlgorithm.Result r) {
        AlgoResult a = new AlgoResult();
        a.mst_edges = new ArrayList<>();
        for (Edge e : r.mstEdges) a.mst_edges.add(new OutEdge(e.from, e.to, e.weight));
        a.total_cost = r.totalCost;
        a.operations_count = r.operations;
        a.execution_time_ms = r.execTimeMs;
        return a;
    }
}
