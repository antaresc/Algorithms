package main.com.acscooter.algorithms;

import java.util.List;
import java.util.Random;

/**
 * @author      Antares Chen
 * @since       2015-07-14
 * RandomizedQuicksort implements the quicksort algorithm using a random pivot
 */

public class RandomizedQuicksort extends Quicksort
{
    /**
     * Chooses the pivot randomly from the list
     * @param list
     * @param <T>
     * @return pivot index
     */
    @Override
    protected <T extends Comparable<T>> int getPivotIndex(List<T> list)
    {
        Random rngGods = new Random(133713371337L);
        return rngGods.nextInt(list.size());
    }
}
