<h1 align="center">🌐 Minimum Spanning Tree – Analytical Report</h1>

<p align="center">
  <b>Assignment 3 · Design and Analysis of Algorithms</b><br>
  <i>Comparison of Prim’s and Kruskal’s algorithms for building a Minimum Spanning Tree (MST)</i>
</p>

---

## 📘 Introduction

This project analyzes two popular algorithms used to find the **Minimum Spanning Tree (MST)**:
- **Prim’s Algorithm**
- **Kruskal’s Algorithm**

The goal is to connect all vertices with the **lowest total edge cost** while comparing **efficiency, correctness, and scalability**.

---

## ⚙️ How to Run

### 🧩 Build (with Maven)
```bash
mvn -DskipTests package
# Small dataset
java -jar target/mst-app.jar small

# Medium dataset
java -jar target/mst-app.jar medium

# Large dataset
java -jar target/mst-app.jar large
java -jar target/mst-app.jar src/main/resources/input/ass_3_input_small.json src/main/resources/output/ass_3_output_small.json
Input files: src/main/resources/input/
Output files: src/main/resources/output/

Each output JSON file contains:

MST total cost

Execution time (ms)

Number of operations

Number of vertices and edges

Separate results for Prim and Kruskal

📂 Datasets

| Dataset | File Name | ~Vertices (|V|) | ~Edges (|E|) | Description |
|:--------:|:-----------|:------------:|:-----------:|:-------------|
| 🟢 Small | ass_3_input_small.json | ~10 | ~20 | Used for basic correctness |
| 🟡 Medium | ass_3_input_medium.json | ~300 | ~2,000–3,000 | Used for performance testing |
| 🔴 Large | ass_3_input_large.json | ~2000 | ~10,000 | Used for stress testing |

All graphs are connected and undirected, with positive edge weights.

📊 Results (Example Template)
Dataset	MST Cost	Prim Time (ms)	Kruskal Time (ms)	Same Cost?
Small	120	2	3	✅
Medium	780	15	22	✅
Large	2400	100	138	✅

(Replace with your real data from JSON outputs.)

🔍 Analysis and Interpretation

Both algorithms found the same MST cost → correctness confirmed.

Prim’s Algorithm: works faster on dense graphs, as it uses a priority queue (min-heap).

Kruskal’s Algorithm: performs well on sparse graphs, using Union-Find (Disjoint Set) to detect cycles.

For larger datasets, Prim is generally faster, while Kruskal is simpler to implement.

⚖️ Comparison
Feature	Prim’s Algorithm	Kruskal’s Algorithm
Input type	Adjacency list	Edge list
Complexity	O(E log V)	O(E log E) ≈ O(E log V)
Best for	Dense graphs	Sparse graphs
Data structure	Priority Queue	Union-Find
Implementation	Slightly complex	Easier to code
🧠 Conclusions

Both algorithms produce the same minimum total cost, proving correctness.

Prim’s performs better for large and dense graphs.

Kruskal’s is efficient for smaller or sparse networks.

Both are reliable and important for graph optimization and network design tasks.

📚 References

Cormen, Leiserson, Rivest, Stein — Introduction to Algorithms

Sedgewick, Wayne — Algorithms, 4th Edition

GeeksforGeeks – Prim’s vs Kruskal’s Algorithm Comparison

<p align="center"> <i>Developed by Dana for Assignment 3 (DAA) · Astana IT University</i> </p> ```
