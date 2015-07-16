package main.com.acscooter.algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-14
 * Quicksort implements the quicksort algorithm
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

        int pivotIndex = getPivotIndex(list);
        T pivot = list.get(pivotIndex);
        list.remove(pivotIndex);

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
     * Gets the pivot element from the list
     * @param list
     * @param <T>
     * @return pivot index
     */
    protected <T extends Comparable<T>> int getPivotIndex(List<T> list)
    {
        return list.indexOf(list.get(0));
    }
}
