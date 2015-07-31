package main.com.acscooter.algorithms;

import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-27
 *
 * Selection sort is a sequential sorting algorithm that functions as follows. Given an array of N elements, first find
 * the smallest element of the array and exchanging it with the first element in the array. Lock that element in place
 * and then search the rest of the array for the next smallest element and swap it with the second element in the array
 * locking that in place as well. Continue this process until the array is sorted. Notice that with the progression of
 * this algorithm, all elements that are locked will be sorted as each iteration the algorithm exchanges the ith
 * smallest element with the ith element in the list.
 *
 * For runtime, consider the worst possible case where the array is in reverse order. For each iteration the algorithm
 * is forced to traverse each unlocked element. That means for an N element array, the total number of comparisons is
 * going to be N + (N-1) + (N-2) + ... + 1 or the sum from 1 to N which is equal to N(N + 1)/2 = O(N^2) which is pretty
 * slow.
 *
 * To be honest, this algorithm kind of sucks. Its time complexity makes it extremely unfit for sorting large arrays,
 * and when compared to other O(n^2) algorithms such as insertion and bubble sort, it tends to be the slowest. On top
 * of that, the canonical implementation involving element swapping makes the sort unstable. Even if this is an in place
 * sort (a sort that functions within the bonds of the input array) requiring only O(1) auxiliary memory, memory is
 * pretty cheap (have you seen the servers with like half a TB of RAM? You could store the cloud in RAM (okay fine thats
 * not the point but still)). Perhaps the only redeeming quality of this sort is the fact that its one of the easier
 * sorting algorithms to intuit. Oh well, poor selection sort.
 */
public class SelectionSort extends AbstractSort
{
    /**
     * A non-stable implementation of Selection sort. A stable version can be implemented if instead of swapping
     * elements, simply move the ith smallest element to the ith position and shifting all elements down by one space.
     * This implementation is chosen simply because CLRS tells me so (Check page 29 of the third edition).
     * @param list
     * @param <T>
     * @return sorted list
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list)
    {
        for (int i = 0; i < list.size(); i ++)
        {
            int minIndex = 0;

            for (int j = i; j < list.size(); j ++)
            {
                if (list.get(j).compareTo(list.get(minIndex)) < 0)
                    minIndex = j;
            }

            T minElement = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i, minElement);
        }

        return list;
    }
}
