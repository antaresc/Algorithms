package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/** A Stack is an abstract data structure that only allows insertion and
 *  removal from the front of a list. That is to say elements are accessed in a
 *  Last In First Out (LIFO) order.
 *  @author Antares Chen
 *  @since  2015-07-19
 */
public class Stack<Element> implements Iterable<Element> {

    /* The underlying dequeue object. */
    private CircularBuffer<Element> _stack;

    /** Initializes an empty stack */
    public Stack() {
        _stack = new CircularBuffer<Element>();
    }

    /** Initializes an empty stack of size SIZE. */
    public Stack(int size) {
        _stack = new CircularBuffer<Element>(size);
    }

    /** Returns if the stack is empty. */
    public boolean isEmpty() {
        return _stack.isEmpty();
    }

    /** Returns the size of the stack. */
    public int size() {
        return _stack.size();
    }

    /** Inserts VALUE onto the top of the stack. Returns if the value was
     *  added. */
    public boolean push(E value) {
        return _stack.addHead(value);
    }

    /** Returns and removes the top most element of the stack. */
    public E pop() {
        return _stack.removeHead();
    }

    /** Returns the top of the stack. */
    public E peek() {
        return _stack.getHead();
    }

    @Override
    public String toString() {
        return _stack.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return _stack.iterator();
    }
}
