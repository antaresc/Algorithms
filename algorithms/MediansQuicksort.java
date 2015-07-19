package main.com.acscooter.algorithms;

import java.util.LinkedList;
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
    protected <T extends Comparable<T>> T getPivot(List<T> list)
    {
        if (list.size() <= 5)
            return getMedian(list);
        LinkedList<T> medians = new LinkedList<>();

        LinkedList<T> sublist = new LinkedList<>();
        for (int i = 0; i < list.size(); i ++)
        {
            sublist.add(list.get(i));

            if (sublist.size() == 5 || i == list.size() - 1)
            {
                medians.add(getMedian(sublist));
                sublist = new LinkedList<>();
            }
        }

        return getPivot(medians);
    }

    /**
     * Determines the median of a list of five or less elements
     * @param list
     * @param <T>
     * @return the median
     * TODO: implement
     */
    private <T extends Comparable<T>> T getMedian(List<T> list)
    {
//        assert list.size() <= 5;
//
//        if (list.size() == 1)
//            return list.get(0);
//        else if (list.size() == 2)
//        {
//            if (list.get(1).compareTo(list.get(0)) < 0)
//                return list.get(0);
//            return list.get(1);
//        }
//        else if (list.size() == 3)
//        {
//            if (list.get(1))
//        }

        return list.get(0);
    }
}
