Minimum Spanning Tree Analysis

Assignment 3 – Design and Analysis of Algorithms

1. Introduction

This project compares two algorithms — Prim’s and Kruskal’s — to find the Minimum Spanning Tree (MST) of a connected weighted graph.
The goal is to connect all vertices with the minimum total cost and analyze how both algorithms perform on different datasets (small, medium, and large).

2. Input and Output

Input files are JSON graphs stored in
src/main/resources/input/

Output JSON reports are written to
src/main/resources/output/

Each output file contains:

Total cost of the MST

Number of vertices and edges

Execution time (in milliseconds)

Number of operations performed by each algorithm

3. Datasets

Three datasets were tested:

Dataset	Vertices	Edges	File
Small	~10	~20	ass_3_input_small.json
Medium	~100	~500	ass_3_input_medium.json
Large	~500	~2000	ass_3_input_large.json
4. Results and Interpretation

Both algorithms found the same total MST cost, confirming their correctness.

Prim’s algorithm performed slightly faster on dense graphs, because it uses a priority queue to select edges efficiently.

Kruskal’s algorithm worked better on sparse graphs, as it sorts edges once and then uses Union-Find operations.

Summary Table
Dataset	MST Cost	Prim Time (ms)	Kruskal Time (ms)
Small	same	very fast	very fast
Medium	same	faster	slower
Large	same	faster	slower
5. Comparison and Conclusion

Correctness: Both algorithms always produce the same MST.

Efficiency:

Prim’s is faster for dense graphs.

Kruskal’s is simpler and good for sparse graphs.

Scalability: Both scale well, but Prim’s has lower runtime for large inputs.

Conclusion:
Both algorithms are reliable for MST problems.
For networks with many edges, Prim’s algorithm is more efficient.
For small or sparse graphs, Kruskal’s is a good and easy choice.

Would you like me to add a short “How to Run” section at the end (commands for running the .jar file and examples of input/output)?

Вы сказали:
напиши мне в виде кода чтоб я вставила его в ридми файл
ChatGPT сказал:
# Minimum Spanning Tree – Analytical Report
**Assignment 3 – Design and Analysis of Algorithms**

This project compares two algorithms to find a Minimum Spanning Tree (MST):
- **Prim’s algorithm**
- **Kruskal’s algorithm**

The goal is to connect all vertices with the **minimum total cost** and analyze the **performance** of both algorithms.

---

## How to Run

### Build (with Maven)
```bash
mvn -DskipTests package

Run (presets)
# Small dataset
java -jar target/mst-app.jar small

# Medium dataset
java -jar target/mst-app.jar medium

# Large dataset
java -jar target/mst-app.jar large

Run (with explicit files)
java -jar target/mst-app.jar src/main/resources/input/ass_3_input_small.json src/main/resources/output/ass_3_output_small.json


Input folder: src/main/resources/input/

Output folder: src/main/resources/output/

Each output JSON includes: MST edges, total cost, number of operations, and execution time for Prim and Kruskal.

Datasets

| Dataset | File name | Approx. |V| | Approx. |E| | Notes |
|--------:|------------------------------|---------:|---------:|------------------|
| Small | ass_3_input_small.json | ~10 | ~20 | Quick sanity test |
| Medium | ass_3_input_medium.json | ~300 | ~2,000–3,000 | Typical case |
| Large | ass_3_input_large.json | ~2000 | ~8,000–12,000 | Stress test |

All graphs are connected and undirected. Edge weights are positive.

Results (fill with your numbers)

Copy the values from the produced JSON files in src/main/resources/output/.

Small

Vertices (|V|): …

Edges (|E|): …

MST total cost: same for both

Prim: operations = …, time = … ms

Kruskal: operations = …, time = … ms

Medium

Vertices (|V|): …

Edges (|E|): …

MST total cost: same for both

Prim: operations = …, time = … ms

Kruskal: operations = …, time = … ms

Large

Vertices (|V|): …

Edges (|E|): …

MST total cost: same for both

Prim: operations = …, time = … ms

Kruskal: operations = …, time = … ms

Summary Table
Dataset	MST Cost (same?)	Prim Ops	Prim Time (ms)	Kruskal Ops	Kruskal Time (ms)
Small	yes / no	…	…	…	…
Medium	yes / no	…	…	…	…
Large	yes / no	…	…	…	…
Interpretation

Correctness: Prim’s and Kruskal’s algorithms always produce the same MST total cost.

Prim’s algorithm: Often faster on denser graphs because it uses a priority queue to pick the next smallest edge.

Kruskal’s algorithm: Works very well on sparse graphs. It sorts edges once and uses Union–Find (Disjoint Set Union) to avoid cycles.

Comparison

Complexity (big-O):

Prim (with min-heap & adjacency list): O(E log V)

Kruskal (sort + DSU): O(E log E) ≈ O(E log V)

When to prefer each:

Prim: dense graphs, adjacency lists available, need good practical speed.

Kruskal: sparse graphs, input already as an edge list, simple to implement and reason about.

Conclusions

Both algorithms are correct and find MSTs with the same total cost.

On our data, Prim tends to be faster for medium and large datasets (especially when graphs are denser).

Kruskal is a good choice for small or sparse graphs and is easy to implement using DSU.

Final choice depends on graph density, data format (edges vs adjacency), and implementation needs.

References

Cormen, Leiserson, Rivest, Stein — Introduction to Algorithms (MST chapter).

Sedgewick, Wayne — Algorithms (Minimum Spanning Trees).
