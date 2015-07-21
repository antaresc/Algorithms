package main.com.acscooter.algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-14
 * MediansQuicksort implements quicksort with the pivot chosen by the median of medians algorithm. Medians are
 * calculated using the quickselect algorithm. Praise be to the CS gods: Blum, Floyd, Pratt, Rivest, and Tarjan.
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
            return select(list, (int) Math.ceil(list.size() / 2.0));
        LinkedList<T> medians = new LinkedList<>();

        LinkedList<T> sublist = new LinkedList<>();
        for (int i = 0; i < list.size(); i ++)
        {
            sublist.add(list.get(i));

            if (sublist.size() == 5 || i == list.size() - 1)
            {
                medians.add(select(sublist, (int) Math.ceil(sublist.size() / 2.0)));
                sublist = new LinkedList<>();
            }
        }

        return getPivot(medians);
    }


    /**
     * Determines the median of a list of five or less elements, this uses the quickselect algorithm
     * @param list
     * @param <T>
     * @return the median
     */
    private <T extends Comparable<T>> T select(List<T> list, int position)
    {
        LinkedList<T> less = new LinkedList<>();
        LinkedList<T> equal = new LinkedList<>();
        LinkedList<T> greater = new LinkedList<>();

        T pivot = list.get((int) Math.ceil(list.size() / 2.0));

        for (T element : list)
        {
            if (element.compareTo(pivot) < 0)
                less.add(element);
            else if (element.compareTo(pivot) == 0)
                equal.add(element);
            else
                greater.add(element);
        }

        if (position < less.size())
            return select(less, position);
        else if (position > less.size() + equal.size())
            return select(greater, position - less.size() - equal.size());
        else
            return pivot;
    }
}
