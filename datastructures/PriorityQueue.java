package main.com.acscooter.datastructures;

/**
 * @author      Antares Chen
 * @since       2015-07-30
 *
 * The max-priority queue is an ordered queue that optimizes for the removal of the element with highest priority. It is
 * an abstract data structure that needs to be backed up by more concrete data structures such as Binary Heaps,
 * Fibonacci Heaps, self balancing trees, and others. The basic max-queue should be able to support operations such as
 * add, isEmpty, size, peek, remove (max), and increaseKey.
 */
public interface PriorityQueue<E>
{
    /**
     * Inserts an element into the priority queue (or underlying data structure).
     * @param value
     * @return if added
     */
    boolean add(E value);

    /**
     * Determines if the queue is empty.
     * @return if empty
     */
    boolean isEmpty();

    /**
     * Returns the size of the priority queue.
     * @return the size
     */
    int size();

    /**
     * Returns the current element with highest priority without removing it
     * @return max element
     */
    E peek();

    /**
     * Removes the current element with highest priority.
     * @return max element
     */
    E remove();

    /**
     * Increases the priority of element e to the same value as key
     * @param e
     * @param key
     * @return the previous e
     */
    E increaseKey(E e, E key);
}
