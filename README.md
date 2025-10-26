# Analytical Report
**Assignment 3 – Optimization of a City Transportation Network (MST)**

## 1. Introduction
This report presents the results of applying **Prim’s** and **Kruskal’s** algorithms to find the **Minimum Spanning Tree (MST)** in a city transportation network.  
The goal of this work is to connect all city districts with the lowest total road construction cost and compare the performance of both algorithms on three different datasets.

---
▶️ Run (predefined datasets)
# Small dataset
java -jar target/mst-app.jar small

# Medium dataset
java -jar target/mst-app.jar medium

# Large dataset
java -jar target/mst-app.jar large

📝 Run (manual input/output)

java -jar target/mst-app.jar src/main/resources/input/ass_3_input_small.json src/main/resources/output/ass_3_ou
---

## 2. Input Data Summary
Three datasets were used to test the program:

| Dataset | File Name | Vertices | Edges | Description |
|----------|------------|----------|-------|--------------|
| Small | `ass_3_input_small.json` | ~10 | ~20 | Small test graph |
| Medium | `ass_3_input_medium.json` | ~100 | ~500 | Medium graph |
| Large | `ass_3_input_large.json` | ~500 | ~2000 | Large graph for performance analysis |

---

## 3. Algorithm Results

| Dataset | MST Total Cost | Prim Time (ms) | Kruskal Time (ms) | Prim Operations | Kruskal Operations |
|----------|----------------|----------------|-------------------|------------------|--------------------|
| Small | same | very fast | very fast | low | low |
| Medium | same | faster | slower | medium | high |
| Large | same | faster | slower | higher | highest |

---

## 4. Interpretation of Results
- Both **Prim’s** and **Kruskal’s** algorithms always produce the same MST total cost.  
  This means both algorithms are **correct**.
- **Prim’s algorithm** was faster on the **medium** and **large** datasets because it uses a **priority queue**, which efficiently finds the next smallest edge.
- **Kruskal’s algorithm** performed slightly slower for dense graphs since it needs to **sort all edges first** and then use **Union-Find** operations.

---

## 5. Comparison
| Criterion | Prim’s Algorithm | Kruskal’s Algorithm |
|------------|------------------|---------------------|
| Best for | Dense graphs | Sparse graphs |
| Complexity | O(E log V) | O(E log E) |
| Implementation | Uses priority queue | Uses sorting and union-find |
| Speed on large graphs | Faster | Slower |
| MST cost | Same | Same |

---

## 6. Conclusions
- Both algorithms are **accurate and efficient** for finding MSTs.
- For **dense graphs** (many connections), **Prim’s algorithm** performs better and runs faster.
- For **sparse graphs** (few edges), **Kruskal’s algorithm** is easier to implement and still efficient.
- The total MST cost is identical in both methods, which confirms their correctness.

**Final conclusion:**  
For real-world networks with many connections (like city roads), **Prim’s algorithm** is more suitable due to better performance.  
For smaller or sparse networks, **Kruskal’s algorithm** remains a simple and reliable choice.

---

**Prepared by:** Dana Shokobalinova
**Course:** Design and Analysis of Algorithms 
**Group:** SE-2402
**Instructor:** Khaimuldin Nursultan  
**Date:** 26.10.25
