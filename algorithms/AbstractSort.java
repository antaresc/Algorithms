package main.com.acscooter.algorithms;


import java.util.Arrays;
import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-14
 * A base class for sorting algorithms. The method sort is subject to change
 * with the algorithm so we'll leave that abstract. A nice wrapper for arrays
 * is provided.
 */
public abstract class AbstractSort {
    
    /**
     * Wrapper method for sorting arrays
     * @param array
     * @param <T>
     * @return sorted array
     */
    public <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(Arrays.asList(array)).toArray(array);
    }

    /**
     * Abstract method for the sorting algorithm
     * @param list
     * @param <T>
     * @return sorted array
     */
    public abstract <T extends Comparable<T>> List<T> sort(List<T> list);
}
