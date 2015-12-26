package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** The deque (double ended queue) is a abstract data structure that only allows
 *  access from the front and end of the queue. This implementation is backed
 *  by a circular buffer.
 *  @author Antares Chen
 *  @since  2015-07-20 */
public class Dequeue<Element> implements Serializable, Iterable<Element> {

    private CircularBuffer<Element> _buffer;

    /** Initializes a Dequeue object. */
    public Dequeue() {
        _buffer = new CircularBuffer<Element>();
    }

    /** Initializes a Dequeue object with size SIZE. */
    public Dequeue(int size) {
        _buffer = new CircularBuffer<Element>(size);
    }

    /** Returns if the dequeue is empty. */
    public boolean isEmpty() {
        return _buffer.isEmpty();
    }

    /** Returns the size of the dequeue. */
    public int size() {
        return _buffer.size();
    }

    /** Returns the value at the head index. */
    public Element peek() {
        return _buffer.getHead();
    }

    /** Inserts VALUE at the head of the dequeue. */
    public boolean insertFirst(Element value) {
        return _buffer.addHead(value);
    }

    /** Inserts VALUE at the tail of the dequeue. */
    public boolean insertLast(Element value) {
        return _buffer.addTail(value);
    }

    /** Returns the first value of the dequeue. */
    public Element removeFirst() {
        return _buffer.removeHead();
    }

    /** Returns the last value of the dequeue. */
    public Element removeLast() {
        return _buffer.removeTail();
    }

    @Override
    public String toString() {
        return _buffer.toString();
    }

    @Override
    public Iterator<Element> iterator() {
        return _buffer.iterator();
    }

}
