package main.com.acscooter.algorithms;

import main.com.acscooter.datastructures.LinkedList;

import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-27
 *
 * The merge sort algorithm is a classic example of a divide and conquer sorting algorithm that functions as follows. It
 * first divides the array by two until each subarray has only one element. The algorithm then repeatedly merges
 * subarrays until an array with the original elements all sorted is constructed. The merge operation simply takes two
 * subarrays and systematically combines elements from both into one single sorted array.
 *
 * At first glance, there doesn't seem to be any intuitive benefit to the overall runtime of the sort. The classic
 * example to refute this is the following. Suppose we perform insertion sort on a set of N elements. The worst case
 * runtime cost is going to be O(N^2). Now consider perform two insertion sorts on two sets of N/2 elements. The worst
 * case runtime cost is now going to be O((N/2)^2) + O((N/2)^2) = O(N^2/2). Due to a blatant abuse of notation and some
 * sketchy mathematics we've now shaved off half our runtime (Big-O math doesn't really work like the way I just did)!
 *
 * But that leaves the question of what is the average case runtime? To calculate runtime, simply generalize the
 * recurrence relation for a hypothetical function T(n) where T(n) is defined as the running time of the merge sort
 * algorithm applied to a list of n elements and then use master's theorem to derive a closed form version of T(n). If
 * none of that made sense, don't worry! Just trust that the result is T(n) = O(n log n). Also wikipedia might be a nice
 * place to start.
 */
public class MergeSort extends AbstractSort
{
    /**
     * Basic implementation of the Merge sort algorithm. There are some small optimizing tricks you can use such as
     * checking if subarrays are already sorted to halt needless recursion, but that implementation is left up to the
     * reader. To make it easier you can just subclass this class. Oh the joys of Object Orientation!
     * @param list
     * @param <T>
     * @return sorted array
     */
    @Override
    public  <T extends Comparable<T>> List<T> sort(List<T> list)
    {
        int middle = list.size() / 2;
        List<T> left = list.subList(0, middle);
        List<T> right = list.subList(middle, list.size());

        sort(left);
        sort(right);

        List<T> sorted = merge(left, right);

        return sorted;
    }

    /**
     * Merge algorithm that keeps two running cursors leftIndex and rightIndex, that runs along the list until it
     * reaches its respective size-limit. This ensures that all elements are added to the merged list.
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    private <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right)
    {
        int leftIndex = 0;
        int rightIndex = 0;
        LinkedList<T> merge = new LinkedList<>();
        while (leftIndex != left.size() && rightIndex != right.size())
        {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0)
            {
                merge.add(left.get(leftIndex));
                leftIndex ++;
            }
            else
            {
                merge.add(right.get(rightIndex));
                rightIndex ++;
            }
        }
        if (leftIndex != left.size())
            merge.addAll(left.subList(leftIndex, left.size()));
        if (rightIndex != right.size())
            merge.addAll(right.subList(rightIndex, right.size()));
        return merge;
    }

}
