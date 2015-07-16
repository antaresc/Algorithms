package main.com.acscooter.algorithms;


import java.util.Arrays;
import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-14
 * Sortable interface designates sorting algorithms
 */
public abstract class Sort
{
    /**
     * Wrapper method for sorting arrays
     * @param array
     * @param <T>
     * @return sorted array
     */
    public <T extends Comparable<T>> T[] sort(T[] array)
    {
        return sort(Arrays.asList(array)).toArray(array);
    }

    /**
     * Abstract method for the sorting algorithm
     * @param list
     * @param <T>
     * @return sorted array
     */
    abstract <T extends Comparable<T>> List<T> sort(List<T> list);
}
