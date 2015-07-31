package main.com.acscooter.datastructures;

/**
 * Created by antares on 7/30/15.
 */
public interface PriorityQueue<E>
{
    boolean add(E value);

    boolean isEmpty();

    int size();

    E peek();

    E remove();

    E increaseKey(E value, E key);
}
