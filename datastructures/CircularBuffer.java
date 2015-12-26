package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** A circular buffer is an inititally empty array with a head and tail index
 *  equal to half the array's size. The head index always points to the first
 *  element in the buffer, and the tail index points to the first empty space
 *  in the buffer. Inserting to the front of the buffer involves decrementing
 *  the head and inserting the element into the array at that index, while
 *  inserting to the end involves adding the element to the array at tail, and
 *  then increasing the index. All value operations done on head and tail are
 *  done modulo the size of the buffer thus if you supercede the size of the
 *  buffer, the index wraps around (hence the name circular). In that manner,
 *  the only time where head and tail should be equal is when no element has
 *  been added. After the first element is added, the next time head and tail
 *  are equal signifies that the buffer is full and a resizing needs to be done.
 *
 *  It may seem for that reason, insertion and deletion are done in O(n) time.
 *  However, in reality, insertion and deletion are actually amortized constant
 *  time. This is because resizing is relatively rare operation and the cost
 *  of resizing can be distributed evenly amongst additions and removals.
 *
 *  Removal operations are made especially easy because all that needs to be
 *  done is correctly decrement or increment the tail or head pointers. This
 *  is because insertion does not care about what is currently in the buffer!
 *  @author Antares Chen
 *  @since  2015-07-20 */
public class CircularBuffer<Element> implements Iterable<Element> {

    /* The initialize size of the circular buffer. */
    private static final INIT_SIZE = 8;

    /* The circular buffer array. */
    private Element[] _buffer;
    /* The index of queue's head. */
    private int _head;
    /* The index of the queue's tail. */
    private int _tail;

    /** Initializes a CircularBuffer object with size 8. */
    public CircularBuffer() {
        this(INIT_SIZE)
    }

    /** Initializes a CircularBuffer object with size SIZE. */
    public CircularBuffer(int size) {
        _buffer = new Element[size];
        _head = size / 2;
        _tail = size / 2;
    }

    /** Returns if the queue is empty. This is true if the head index is the
     *  same as the tail index. */
    public boolean isEmpty() {
        return _head == _tail;
    }

    /** Returns the size of the queue. This is equal to the tail index minus
     *  the head index modulo the length of the buffer. */
    public int size() {
        return (_tail - _head) % _buffer.length;
    }

    /** Returns the value at the head index. */
    public Element getHead() {
        if (isEmpty())
            throw new NoSuchElementException();
        return _buffer[_head];
    }

    /** Returns the value at the tail index. */
    public Element getHead() {
        if (isEmpty())
            throw new NoSuchElementException();
        return _buffer[(_tail - 1) % buffer.length];
    }

    /** Inserts VALUE at the head of the deque. This is done by first
     *  decrementing the head index, checking if the head index is equal to the
     *  tail (resizing if need be), and then adding the value. */
    public boolean addHead(Element value) {
        _head = (_head - 1) % _buffer.length;
        if (_head == _tail) {
            resize();
        }
        _buffer[_head] = value;
        return true;
    }

    /** Inserts VALUE at the tail of the deque. This is done by adding the
     *  value, incrementing the tail index, and resizing if necessary. */
    public boolean addTail(Element value) {
        _buffer[_tail] = value;
        _tail = (_tail + 1) % _buffer.length;
        if (_head == _tail) {
            resize();
        }
        return true;
    }

    /** Returns the value at index head and increments the head index. */
    public Element removeHead() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Element value = _buffer[_head];
        _head = (_head + 1) % _buffer.length;
        return value;
    }

    /** Returns the value at index before tail and decrements the tail index. */
    public Element removeTail() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Element value = _buffer[_tail - 1];
        _tail = (_tail - 1) % _buffer.length;
        return value;
    }

    /** Doubles the size of the buffer and concentrates all elements to the
     *  center of the buffer.
     *
     *  This operation is fairly straight forward. If the head index is before
     *  the tail index, then there exists one contiguous block. If the head is
     *  after the tail, then you must copy two blocks. Else there are no
     *  elements in the buffer and all that must be done is set the head and
     *  tail pointers. */
    private void resize() {
        Element[] next = new Element[_buffer.length * 2];
        int start = next.length / 2;
        if (_head < _tail) {
            System.arraycopy(_buffer, _head, next, start + _head, size());
        } else if (_head > _tail) {
            int firstSize = _buffer.length - _head;
            System.arraycopy(_buffer, _head, next, start, firstSize);
            System.arraycopy(_buffer, 0, next, start + firstSize, _tail);
        } else {
            _head = start;
            _tail = start;
        }
        _buffer = next;
    }

    @Override
    public String toString() {
        String beans = "[";

        for (Element value : this)
            beans += value + ", ";

        beans = beans.substring(0, beans.length() - 2) + "]";
        return beans;
    }

    @Override
    public Iterator<Element> iterator() {
        return new CircularIterator();
    }

    /** A CircularIterator iterates through the circular buffer to access
     *  elements. */
    private class CircularIterator implements Iterator<Element> {

        /* Current index of the iterator. */
        int index;

        /** Initializes the iterator object. */
        public CircularIterator() {
            index = _head;
        }

        @Override
        public boolean hasNext() {
            return index != _tail;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Element next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Element value = _buffer[index];
            index = (index + 1) % _buffer.length;
            return value;
        }
    }

}
