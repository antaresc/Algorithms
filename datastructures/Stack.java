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
 *
 * TODO: implement a deepcopy method
 * TODO: disable the ability to add null objects to stack
 */
public class Stack<E> implements Serializable, Iterable<E>
{
    private static final long serialVersionUID = 3614863464623083648L;
    private int size;
    private Node<E> head;

    /**
     * Node helper class represents a member of the stack.
     */
    private static class Node<E>
    {
        private E value;
        private Node<E> next;

        Node(E value, Node<E> next)
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
    public Stack(E[] array)
    {
        size = 0;
        for (E value : array)
            push(value);
    }


    /**
     * Instantiates a stack taking elements from a list
     * @param list
     */
    public Stack(List<E> list)
    {
        size = 0;
        for (E value : list)
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
    public boolean push(E value)
    {
        head = new Node<>(value, head);
        size ++;
        return true;
    }


    /**
     * Pushes all elements of values in sequence on to the stack
     * @param values
     * @return if pushed
     */
    public boolean push(List<E> values)
    {
        for (E value : values)
        {
            head = new Node<>(value, head);
            size ++;
        }
        return true;
    }


    /**
     * Pushes all elements of values in sequence on to the stack
     * @param values
     * @return if pushed
     */
    public boolean push(E... values)
    {
        push(Arrays.asList(values));
        return true;
    }


    /**
     * Pops the topmost element of the stack
     * @return stack head
     */
    public E pop()
    {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");

        Node<E> popped = head;
        head = head.next;
        size --;
        return popped.value;
    }


    /**
     * Returns the topmost element without removing it
     * @return stack head
     */
    public E peek()
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
        for (E value : this)
            beans += value + ", ";

        beans = beans.substring(0, beans.length() - 2) + "]";
        return beans;
    }


    /**
     * Returns an iterator for the stack
     * @return stack iterator
     */
    @Override
    public Iterator<E> iterator()
    {
        return new ListIterator();
    }


    /**
     * Defines the list iterator linked with stack objects
     */
    private class ListIterator implements Iterator<E>
    {
        private Node<E> current = head;

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
         * Removes the element after it is traversed. Ehis operation is not supported.
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
        public E next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            E value = current.value;
            current = current.next;
            return value;
        }
    }

}
