package main.com.acscooter.datastructures;

import sun.tools.tree.BinaryArithmeticExpression;

import java.util.Collection;
import java.util.NoSuchElementException;


/**
 * @author      Antares Chen
 * @since       2015-07-30
 *
 * The Binary Heap is a data structure that always maintains two properties that can be succinctly described as the
 * "shape" and "heap" property.
 *
 *      1.  A binary heap is a complete binary tree implying that the tree, at all depths, is always filled with the
 *          exception of the last level. Each parent node may have at max two children nodes.
 *
 *      2.  All nodes are either greater than or equal to or less than or equal to each of its children. The decision
 *          of which predicate creates what is called a max-heap and min-heap respectively.
 *
 * Because of the shape property, the Binary Heap can be stored in an array. That means no private nested classes
 * holding tree nodes and such! Where the parent and left and right children are depends if the array is 0-indexed or
 * 1-indexed. In many implementations online (as well as in CLRS), one will find that Heaps are 1-indexed. Given that is
 * the case, then the parent node located at floor(N / 2), left child at N * 2, and right child at N * 2 + 1. However,
 * if the heap is 0-indexed, then the parent node is at Ceil(N / 2) - 1, left child at N * 2 + 1, and right child at
 * N * 2 + 2. Implementation-wise the difference of 1-indexed to 0-indexed is the addition of a dummy node in the array
 *
 * To maintain the heap property, we have two operations: heapUp and heapDown (these are also called bubble and
 * percolate up and down respectively). Likewise, to initialize the heap from an array of values, we have the heapify
 * function. How these functions work are explained in the below comments. Because of the heap property, the minimum
 * or maximum of the heap will always be the root node. This allows for fast removal of the min and further makes it an
 * efficient data structure to back priority queues.
 *
 * The below implementation backs the heap with a DynamicArray array.
 */
public class BinaryHeap <E extends Comparable<E>> implements PriorityQueue<E>
{
    private DynamicArray<E> heap;

    public BinaryHeap()
    {
        heap = new DynamicArray<>();
    }

    public BinaryHeap(E[] array)
    {
        heap = new DynamicArray<>(array);
        heapify();
    }

    public BinaryHeap(Collection<E> c)
    {
        heap = new DynamicArray<>(c);
        heapify();
    }

    /**
     * Comments to come later
     * @param value
     * @return if added
     */
    @Override
    public boolean add(E value)
    {
        heap.add(value);
        heapUp(heap.size() - 1);
        return true;
    }

    @Override
    public boolean isEmpty()
    {
        return heap.size() == 0;
    }

    @Override
    public int size()
    {
        return heap.size();
    }

    @Override
    public E peek()
    {
        return heap.get(0);
    }

    /**
     * Comments to come later
     * @return max element in the heap
     */
    @Override
    public E remove()
    {
        if (this.isEmpty())
            throw new NoSuchElementException();

        E value = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        heapDown(0);
        return value;
    }

    /**
     * Comments to come later
     * @param value
     * @param key
     * @return previous element
     */
    @Override
    public E increaseKey(E value, E key)
    {
        if (key.compareTo(value) < 0)
            throw new IllegalStateException("Key must be greater than current value.");

        int targetIndex = heap.indexOf(value);
        heap.set(targetIndex, key);
        heapUp(targetIndex);
        return value;
    }

    /**
     * Comments to come later
     * @param index
     */
    private void heapUp(int index)
    {
        int parentIndex = parent(index);
        if (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) > 0)
        {
            swap(index, parent(index));
            heapUp(parentIndex);
        }
    }

    /**
     * Comments to come later
     * @param index
     */
    private void heapDown(int index)
    {
        int leftIndex = left(index);
        int rightIndex = right(index);
        int largestIndex;

        if (leftIndex < heap.size() && heap.get(leftIndex).compareTo(heap.get(index)) > 0)
            largestIndex = leftIndex;
        else
            largestIndex = index;

        if (rightIndex < heap.size() && heap.get(rightIndex).compareTo(heap.get(largestIndex)) > 0)
            largestIndex = rightIndex;

        if (largestIndex != index)
        {
            swap(index, largestIndex);
            heapDown(largestIndex);
        }
    }

    /**
     * Comments to come later
     */
    private void heapify()
    {
        for (int i = heap.size() / 2; i >= 0; i --)
            heapDown(i);
    }

    private void swap(int toSwap, int target)
    {
        E temp = heap.get(target);
        heap.set(target, heap.get(toSwap));
        heap.set(toSwap, temp);
    }

    private int parent(int index)
    {
        return (int) (Math.ceil(index / 2.0) - 1);
    }

    private int left(int index)
    {
        return (2 * index + 1);
    }

    private int right(int index)
    {
        return (2 * index + 2);
    }
}
