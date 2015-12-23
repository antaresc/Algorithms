package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/** A Queue is an abstract data structure that only allows removal from the
 *  front of the list and insertion from the back. That is to say elements are
 *  accessed in a First In First Out (FIFO) order.
 *
 *  @author Antares Chen
 *  @since  2015-07-19 */
public class Queue<Element> implements Iterable<Element> {

    /** The underlying dequeue object. */
    private Dequeue<Element> queue;

    /** Initializes an empty queue */
    public Queue() {
        queue = new Dequeue<Element>();
    }

    /** Initializes an empty queue of size SIZE. */
    public Queue(int size) {
        queue = new Dequeue<Element>(size);
    }

    /** Returns if the queue is empty. */
    public boolean isEmpty() {
        return queue.isEmpty();
    }


    /** Returns the size of the queue. */
    public int size() {
        return queue.size();
    }


    /** Inserts VALUE onto the end of the queue. Returns if the value was
     *  added. */
    public boolean enqueue(E value) {
        return queue.insertLast(value);
    }

    /** Returns and removes the front element of the queue. */
    public E dequeue() {
        return queue.removeFirst();
    }


    /** Returns the top of the queue. */
    public E peek() {
        return queue.peek();
    }

    @Override
    public String toString() {
        String beans = "[";
        for (E value : this)
            beans += value + ", ";

        beans = beans.substring(0, beans.length() - 2) + "]";
        return beans;
    }

    @Override
    public Iterator<E> iterator() {
        return new queue.iterator();
    }
}
