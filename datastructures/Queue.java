package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author      Antares Chen
 * @since       2015-07-20
 * The queue data structure is a sequential list accessed in First In First Out (FIFO) order.
 *
 * TODO: disable the ability to add null objects to queue
 */
public class Queue<E> implements Serializable, Iterable<E>
{
    private static final long serialVersionUID = -6539224685556853640L;
    private int size;
    private Node<E> head;
    private Node<E> tail;


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
     * Default initializer creates a null queue
     */
    public Queue()
    {
        size = 0;
        head = null;
        tail = null;
    }


    /**
     * Creates a queue from the given array
     * @param array
     */
    public Queue(E[] array)
    {
        size = 0;
        for (E value : array)
            enqueue(value);
    }


    /**
     * Creates a queue from the given list
     * @param list
     */
    public Queue(List<E> list)
    {
        size = 0;
        list.forEach(this :: enqueue);
    }


    /**
     * Determines if the queue is empty
     * @return emptiness
     */
    public boolean isEmpty()
    {
        return (size == 0);
    }


    /**
     * Returns the size of the queue
     * @return the size
     */
    public int size()
    {
        return size;
    }


    /**
     * Performs a deep copy of the given queue. Not the copy only copies down to the node level and not the values in
     * each node.
     * @return copy of queue
     */
    public Queue<E> deepCopy()
    {
        Queue<E> copy = new Queue<>();
        if (this.head != null)
        {
            Node<E> current = this.head;
            while (current != null)
            {
                copy.enqueue(current.value);
                current = current.next;
            }

            assert (copy.size() == this.size());
        }
        return copy;
    }


    /**
     * Returns the next value to be dequeued without actually dequeuing
     * @return
     */
    public E peek()
    {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");

        return head.value;
    }


    /**
     * Places the given value into the queue
     * @param value
     * @return if enqueued
     */
    public boolean enqueue(E value)
    {
        if (isEmpty())
        {
            head = new Node<>(value, null);
            tail = head;
        }
        else
        {
            Node<E> node = new Node<>(value, null);
            node.value = value;
            tail.next = node;
            tail = tail.next;
        }
        size ++;
        return true;
    }


    /**
     * Places all designated values into the queue
     * @param values
     * @return if enqueued
     */
    public boolean enqueue(E... values)
    {
        for (E value : values)
            assert this.enqueue(value);

        return true;
    }


    /**
     * Places all designated values into the queue
     * @param values
     * @return if enqueued
     */
    public boolean enqueue(List<E> values)
    {
        for (E value: values)
            assert this.enqueue(value);

        return true;
    }


    /**
     * Removes the next element in the queue
     * @return element
     */
    public E dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");

        Node<E> dequeue = head;
        head = head.next;

        if (isEmpty())
            tail = null;

        size --;
        return dequeue.value;
    }


    /**
     * Gives the string representation of the queue
     * @return queue string
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


    @Override
    public Iterator<E> iterator()
    {
        return new ListIterator();
    }

    /**
     * Class that defines the list iterator for queue.
     */
    private class ListIterator implements Iterator<E>
    {
        private Node<E> current = head;

        /**
         * Determines if there is a next element while traversing the queue
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
         * Returns the next element in the queue
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
