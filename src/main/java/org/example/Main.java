package org.example;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Entry point:
 *  - Reads input graphs from JSON
 *  - Runs Prim and Kruskal for each graph
 *  - Validates MSTs
 *  - Writes a consolidated JSON report
 *
 * Run options:
 *   1) Presets (recommended):
 *      java -jar target/mst-app.jar           // uses "small"
 *      java -jar target/mst-app.jar medium
 *      java -jar target/mst-app.jar large
 *
 *   2) Explicit paths:
 *      java -jar target/mst-app.jar <inputPath> <outputPath>
 */
public class Main {

    public static void main(String[] args) {
        // If user passes two args -> interpret as explicit input/output paths.
        // Else -> use presets small|medium|large (default: small).
        String in, out;
        if (args.length >= 2) {
            in = args[0];
            out = args[1];
        } else {
            String preset = (args.length >= 1) ? args[0] : "small";
            in = switch (preset.toLowerCase()) {
                case "medium" -> "src/main/resources/input/ass_3_input_medium.json";
                case "large"  -> "src/main/resources/input/ass_3_input_large.json";
                default       -> "src/main/resources/input/ass_3_input_small.json";
            };
            out = "src/main/resources/output/ass_3_output_" + (args.length >= 1 ? args[0].toLowerCase() : "small") + ".json";
        }

        // Ensure output directory exists
        java.io.File outFile = new java.io.File(out);
        java.io.File outDir = outFile.getParentFile();
        if (outDir != null) outDir.mkdirs();

        try {
            // 1) Read input graphs
            InputRoot root = JsonHandler.readInput(in);

            // 2) Prepare output root
            OutputRoot outRoot = new OutputRoot();
            outRoot.results = new ArrayList<>();

            // 3) Process each graph independently
            for (InputGraph ig : root.graphs) {
                Graph g = JsonHandler.toGraph(ig);

                // Run algorithms
                PrimAlgorithm.Result pr = PrimAlgorithm.run(g);
                KruskalAlgorithm.Result kr = KruskalAlgorithm.run(g);

                // === Validate MSTs (n-1 edges, connectivity) and equal total cost ===
                // If you haven't added MstValidator.java yet, paste it from the message.
                MstValidator.validate(g, pr.mstEdges);
                MstValidator.validate(g, kr.mstEdges);
                if (pr.totalCost != kr.totalCost) {
                    throw new IllegalStateException(
                            "MST total cost mismatch on graph id=" + ig.id +
                                    " (Prim=" + pr.totalCost + ", Kruskal=" + kr.totalCost + ")"
                    );
                }

                // Package per-graph results
                GraphResult gr = new GraphResult();
                gr.graph_id = ig.id;

                gr.input_stats = new InputStats();
                gr.input_stats.vertices = g.n();
                gr.input_stats.edges = g.m();

                gr.prim = JsonHandler.toAlgoResultPrim(pr);
                gr.kruskal = JsonHandler.toAlgoResultKruskal(kr);

                outRoot.results.add(gr);
            }

            // 4) Write output report
            JsonHandler.writeOutput(out, outRoot);
            System.out.println("Done. Output written to: " + out);

        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }
}
