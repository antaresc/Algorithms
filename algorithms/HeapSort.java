package main.com.acscooter.algorithms;

import main.com.acscooter.datastructures.BinaryHeap;
import main.com.acscooter.datastructures.LinkedList;

import java.util.List;

/**
 * Created by antares on 7/30/15.
 */
public class HeapSort extends AbstractSort
{

    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list)
    {
        BinaryHeap<T> heap = new BinaryHeap<>(list);
        LinkedList<T> sorted = new LinkedList<>();

        for (int i = 0; i < heap.size(); i ++)
            sorted.add(0, heap.remove());

        return sorted;
    }
}
