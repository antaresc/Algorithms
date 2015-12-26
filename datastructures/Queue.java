package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/** A Queue is an abstract data structure that only allows removal from the
 *  front of the list and insertion from the back. That is to say elements are
 *  accessed in a First In First Out (FIFO) order.
 *  @author Antares Chen
 *  @since  2015-07-19 */
public class Queue<Element> implements Iterable<Element> {

    /** The underlying dequeue object. */
    private CircularBuffer<Element> _queue;

    /** Initializes an empty queue */
    public Queue() {
        _queue = new CircularBuffer<Element>();
    }

    /** Initializes an empty queue of size SIZE. */
    public Queue(int size) {
        _queue = new CircularBuffer<Element>(size);
    }

    /** Returns if the queue is empty. */
    public boolean isEmpty() {
        return _queue.isEmpty();
    }

    /** Returns the size of the queue. */
    public int size() {
        return _queue.size();
    }

    /** Inserts VALUE onto the end of the queue. Returns if the value was
     *  added. */
    public boolean enqueue(E value) {
        return _queue.addTail(value);
    }

    /** Returns and removes the front element of the queue. */
    public E dequeue() {
        return _queue.removeHead();
    }


    /** Returns the top of the queue. */
    public E peek() {
        return _queue.getHead();
    }

    @Override
    public String toString() {
        return _queue.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return _queue.iterator();
    }
}
