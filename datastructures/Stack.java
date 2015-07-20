package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * @author      Antares Chen
 * @since       2015-07-19
 * The stack data structure is a sequential list accessed in Last In First Out (LIFO) order .
 */
public class Stack<T> implements Cloneable, Serializable, Iterable<T>
{
    private int size;
    private Node head;

    /**
     * Node helper class represents a member of the stack.
     */
    private class Node
    {
        private T value;
        private Node next;

        Node(T value, Node next)
        {
            this.value = value;
            this.next = next;
        }
    }


    /**
     * Default constructor that instantiates a null stack
     */
    public Stack()
    {
        head = null;
        size = 0;
    }


    /**
     * Instantiates a stack taking elements from an array
     * @param array
     */
    public Stack(T[] array)
    {
        size = 0;
        for (T value : array)
            push(value);
    }


    /**
     * Instantiates a stack taking elements from a list
     * @param list
     */
    public Stack(List<T> list)
    {
        size = 0;
        for (T value : list)
            push(value);
    }


    /**
     * Determines if the stack is empty
     * @return emptiness
     */
    public boolean isEmpty()
    {
        return (size == 0);
    }


    /**
     * Determines the size of the stack
     * @return the size
     */
    public int size()
    {
        return size;
    }


    /**
     * Pushes a new value on to the stack
     * @param value
     */
    public boolean push(T value)
    {
        Node node = new Node(value, head);
        head = node;
        size ++;
        return true;
    }


    /**
     * Pushes all elements of values in sequence on to the stack
     * @param values
     * @return if pushed
     */
    public boolean push(List<T> values)
    {
        for (T value : values)
        {
            Node node = new Node(value, head);
            head = node;
            size ++;
        }
        return true;
    }


    /**
     * Pushes all elements of values in sequence on to the stack
     * @param values
     * @return if pushed
     */
    public boolean push(T... values)
    {
        push(Arrays.asList(values));
        return true;
    }


    /**
     * Pops the topmost element of the stack
     * @return stack head
     */
    public T pop()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        Node popped = head;
        head = head.next;
        size --;
        return popped.value;
    }


    /**
     * Returns the topmost element without removing it
     * @return stack head
     */
    public T peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        return head.value;
    }


    /**
     * String representation of the stack
     * @return
     */
    @Override
    public String toString()
    {
        String beans = "[";
        for (T value : this)
            beans += value + ", ";

        beans = beans.substring(0, beans.length() - 2) + "]";
        return beans;
    }


    /**
     * Returns an iterator for the stack
     * @return stack iterator
     */
    @Override
    public Iterator<T> iterator()
    {
        return new ListIterator();
    }


    /**
     * Defines the list iterator linked with stack objects
     */
    private class ListIterator implements Iterator<T>
    {
        private Node current = head;

        /**
         * Determines if there is a next element while traversing the stack
         * @return
         */
        @Override
        public boolean hasNext()
        {
            return (current != null);
        }


        /**
         * Removes the element after it is traversed. This operation is not supported.
         */
        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }


        /**
         * Returns the next element in the stack
         * @return next element
         */
        @Override
        public T next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            T value = current.value;
            current = current.next;
            return value;
        }
    }

}
