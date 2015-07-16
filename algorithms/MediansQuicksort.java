package main.com.acscooter.algorithms;

import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-14
 * MediansQuicksort implements quicksort with the pivot chosen by the median of medians algorithm
 */

public class MediansQuicksort extends Quicksort
{
    /**
     * Chooses the pivot using the median of medians algorithm
     * @param list
     * @param <T>
     * @return pivot index
     */
    @Override
    protected <T extends Comparable<T>> int getPivotIndex(List<T> list)
    {
//        if less than or equal to five find the median
//        split into groups of five
//        calculate median
//        create list of medians
//        recurse
        return -1;
    }

    private <T extends Comparable<T>> int getMedianIndex(List<T> list)
    {
        return -1;
    }

}
