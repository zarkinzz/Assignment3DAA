package org.example;

/**
 * Lightweight counter for algorithmic "operations".
 * We count key actions such as comparisons, DSU operations, and PQ ops.
 * This is not a precise CPU instruction counter; it is a pedagogical metric.
 */
public class OperationCounter {
    private long value = 0L;

    public void inc() { value++; }
    public void add(long delta) { value += delta; }
    public long get() { return value; }

    // Semantic markers to increment the counter in a readable way:
    public void compare() { value++; }  // comparisons in sort/heap/conditions
    public void union() { value++; }    // union call
    public void find() { value++; }     // find call
    public void pqOp() { value++; }     // priority queue push/poll/peek
}
