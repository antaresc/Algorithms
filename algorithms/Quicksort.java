package main.com.acscooter.algorithms;

import main.com.acscooter.datastructures.LinkedList;
import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-14
 *
 * The quicksort algorithm (also called the partition-exchange sort) is one of the most efficient sorting algorithms
 * known with average case performance O(n log n) where n is the size of the array. The algorithm is as follows. Given
 * an array, choose and remove the pivot point. Then partition the array into two parts where the first partition
 * receives all elements less than the pivot and the latter receives all elements greater than or equal to (it actually
 * doesn't matter which is equal to or whatever). Finally recurse on each partition and reconstruct the sorted array as
 * first partition + pivot + second partition.
 *
 * Note that the choice of pivot actually dictates how well the algorithm performs. For example, imagine a case where
 * the algorithm always chooses a pivot such that all elements are strictly greater, then the algorithm would
 * essentially function the same way insertion sort would resulting in the worst case time bound of O(n^2). This
 * actually leads to multiple variants of quicksort: randomized, median of medians, etc. However for now, we provide
 * a naive implementation of quicksort which uses the first element of the list as a pivot.
 */
public class Quicksort extends Sort
{
    /**
     * A naive implementation of quicksort that uses the first element as a pivot.
     * @param list
     * @param <T>
     * @return sorted list
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list) {
        if (list.size() <= 1)
            return list;

        LinkedList<T> right = new LinkedList<>();
        LinkedList<T> left = new LinkedList<>();

        T pivot = getPivot(list);
        list.remove(pivot);

        for (T element : list) {
            if (element.compareTo(pivot) <= 0)
                right.add(element);
            else
                left.add(element);
        }

        right = new LinkedList<>(sort(right));
        left = new LinkedList<>(sort(left));

        right.add(pivot);
        right.addAll(left);
        return right;
    }

    /**
     * Gets the pivot element from the list. Since the pivot getting algorithm is subject to change, we make it
     * protected so that subclasses can later override it.
     * @param list
     * @param <T>
     * @return pivot index
     */
    protected <T extends Comparable<T>> T getPivot(List<T> list)
    {
        return list.get(0);
    }
}
