package main.com.acscooter.algorithms;

import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-27
 *
 * The insertion sort is actually a pretty intuitive sort. Chances are you've done it many times when playing card games
 * such as President or 5-card Poker. The technical description of insertion sort is as follows. Given an array of N
 * elements, iterate from i = 1 ... N and for element at position i (denoted A[i]), shift A[i] toward the left (ie from
 * position i-1 to 1) until all elements before A[i] are less than A[i].
 *
 * This works on the principle that at the end of each iteration of the loop, the subarray from A[1 ... i] consists only
 * of original elements from A[1 ... i] that are in sorted order. To justify this we use some simple induction. The base
 * case is if i=1 which is trivial because A[1 ... 1] is a subarray with one element and that one element isn't shifted.
 * Now for our inductive hypothesis, we assume that our principle holds for i = n for any natural number n and prove
 * i = n+1. Well if n holds, that means our subarray A[1 ... n] is sorted and consists only of elements originally in
 * A[1 ... n]. If we now consider the next element at A[n+1] then that will shift it to the left until there are no
 * elements to the left greater than A[n+1] implying that if A[n+1] is now at position k then A[1 ... k-1] are all less
 * than A[n+1] and A[k+1 ... n+1] are greater than or equal to A[n+1] thereby preserving order. All elements in A[1 ...
 * n+1] still consists of element originally in that subarray. Because our inductive property holds we can say that
 * the array, at iteration N must thus be sorted.
 *
 * For time complexity this sort runs in O(n^2) for the same reason that Selection sort runs O(n^2). Simply consider
 * the worst case scenario where the input array is in reverse sorted order.
 */
public class InsertionSort extends Sort
{
    /**
     * Basic implementation of insertion sort.
     * @param list
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list)
    {
        for (int i = 1; i < list.size(); i ++)
        {
            T key = list.get(0);
            int tempIndex = i - 1;
            T temp = list.get(tempIndex);
            while (tempIndex >= 0 && key.compareTo(list.get(tempIndex)) > 0)
            {
                list.set(i, temp);
                list.set(tempIndex, key);
                tempIndex --;
            }
        }

        return list;
    }
}
