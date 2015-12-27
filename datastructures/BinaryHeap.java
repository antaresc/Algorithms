package main.com.acscooter.datastructures;

/** The Binary Heap is a tree data structure that maintains the two properties:
 *
 *  (1) A binary heap is a complete binary tree. At all depths, it is always
 *  filled with nodes with the exception of the last level. Each parent node may
 *  have at max two children nodes.
 *
 *  (2) All nodes are greater than or equal to each of its children. This
 *  defines a max-heap. If it were less than or equal to, you would have a min-
 *  heap.
 *
 *  To maintain the heap property, we have two operations: heapUp and heapDown
 *  (these are also called bubble and percolate up and down respectively). To
 *  initialize the heap from an array of values, we have the heapify function.
 *  The max value of a heap will always be the root node.
 *
 *  Because it is a complete binary tree, the binary heap can be stored in an
 *  array. The positions of the parent and children elements are well-defined.
 *  Given that our array is 0-indexed, for a node index N, its right and left
 *  children are at index 2 * N + 1 and 2 * N + 2 respectively. Its parent is
 *  at index Ceil(N / 2) - 1.
 *
 *  @author Antares Chen
 *  @since  2015-07-30
 */
public class BinaryHeap <Value extends Comparable<Value>>
    implements Heap<Value> {

    /** The Dynamic Array holding all the heap's elements. */
    private DynamicArray<Value> _heap;

    /** Constructs an empty heap. */
    public BinaryHeap() {
        _heap = new DynamicArray<Value>();
    }

    @Override
    public void insert(Value value) {
        _heap.add(value);
        heapUp(_heap.size() - 1);
    }

    @Override
    public Value findMax() {
        return _heap.get(0);
    }

    @Override
    public Value removeMax() {
        if (_heap.isEmpty()) {
            return null;
        }

        Value value = _heap.get(0);
        swap(0, _heap.size() - 1);
        _heap.remove(_heap.size() - 1);
        heapDown(0);
        return value;
    }

    @Override
    public Value increaseKey(Value value, Value newValue) {
        if (newValue.compareTo(value) < 0) {
            throw new IllegalStateException(
                            "Key must be greater than current value.");
        }

        int index = _heap.indexOf(value);
        _heap.set(index, newValue);
        _heapUp(index);
        return value;
    }

    /** Performs a heap up on the element at INDEX which swaps the element at
     *  INDEX with its parent if the element is greater than the parent. */
    private void heapUp(int index) {
        int parentIndex = (int) (Math.ceil(index / 2.0) - 1);

        Value curr = _heap.get(index);
        Value parent = _heap.get(parentIndex);

        if (index > 0 &&  curr.compareTo(parent) > 0) {
            _heap.set(parentIndex, curr);
            _heap.set(index, curr);

            heapUp(parentIndex);
        }
    }

    /** Performs a heap down on the element at index which swaps the element
     *  with the largest of its children. */
    private void heapDown(int index) {
        int lIndex = 2 * index + 1;
        int rIndex = 2 * index + 2;
        int largestIndex;

        Value curr = _heap.get(index);

        if (lIndex < _heap.size() && curr.compareTo(_heap.get(lIndex)) < 0) {
            largestIndex = lIndex;
        } else if (rIndex < _heap.size()
            && curr.compareTo(_heap.get(rIndex)) < 0) {
            largestIndex = rIndex;
        } else {
            largestindex = index;
        }
        if (largestIndex != index) {
            _heap.set(index, _heap.get(largestIndex));
            _heap.set(largestIndex, curr);
            heapDown(largestIndex);
        }
    }

    /** Constructs the heap by performing a heap down from the lowest level to
     *  the top level. This performs heap construction from an unsorted array
     *  in linear time. */
    private void heapify() {
        for (int i = _heap.size() / 2; i >= 0; i -= 1) {
            heapDown(i);
        }
    }

}
