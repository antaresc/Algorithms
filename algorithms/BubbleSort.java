package main.com.acscooter.algorithms;

import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-28
 *
 * Bubble sort is perhaps the weirdest out of all O(n^2) sorting algorithms. It repeatedly passes through the list
 * comparing each adjacent element, and swaps the elements if they are out of order. It continues to do this until no
 * more swaps are needed. It is provable that this algorithm requires at most n passes because in the worst case, the
 * smallest value will be at the end of the list. Because it will be swapped only one position per pass, it will require
 * n iterations to swap n spaces to the front (this kind of element is colloquially known as a turtle).
 *
 * Interestingly enough this algorithm gets a lot of hate from Computer Scientists. As Donald Knuth (praise be to him)
 * once said, "the bubble sort seems to have nothing to recommend it." and the Jargon File states that Bubble Sort is
 * the "generic bad algorithm." Poor bubble sort, I still believe in you if only for your eccentric sorting methods!
 */
public class BubbleSort extends Sort
{
    /**
     * A short implementation of Bubble Sort. There are some interesting methods of optimizing Bubble Sort. For example,
     * during Bubble Sort, it can happen that more than one element is placed in its correct position by adding an extra
     * boolean, we can allow the algorithm to skip over many elements. The code below, however, is just the naive
     * implementation.
     * @param list
     * @param <T>
     * @return sorted list
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list)
    {
        boolean swapped = true;

        while (swapped)
        {
            swapped = false;

            for (int j = 0; j < list.size() - 1; j ++)
            {
                if (list.get(j).compareTo(list.get(j + 1)) > 0)
                {
                    T holder = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, holder);
                    swapped = true;
                }
            }
        }
        return list;
    }
}
