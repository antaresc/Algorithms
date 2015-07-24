package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.*;


/**
 * @author      Antares Chen
 * @since       2015-07-21
 *
 * LinkedList implements a circular doubly linked list with a sentinel node. What differentiates this from java's
 * implementation is the fact that all the functionalities of this class rest solely on its superclass
 * AbstractSequentialList. Java's implementation also subclasses AbstractSequentialList, but for some reason it also
 * unnecessarily overrides a large number of functions such as add, addAll, remove, etc. that can already be handled
 * because of its implementation of the ListIterator.
 *
 * That is not to say that this implementation is better, since this implementation does not have the added protection
 * of a modification count variable. I haven't quite ruled out the possibility of comodifying an instance of the below.
 * The STL LinkedList also has more functionality since it implements removeFirst, removeLast, removeFirstOccurrence,
 * etc. allowing the user to essentially use the LinkedList as a stack or a queue. Those functionalities were
 * specifically left out because this LinkedList is not meant to be used as a stack or a queue.
 *
 * In essence, this was done as an excercise in extending the Java collections code base, and like the oracle guide
 * says, it's was actually very easy. All that really needs to be implemented is a List iterator. Good stuff oracle.
 */
public class LinkedList<E> extends AbstractSequentialList<E> implements Iterable<E>, List<E>, Serializable
{
    private static final long serialVersionUID = 8319825356755732994L;
    private Node<E> sentinel = new Node<>(null, null, null);
    private int size = 0;


    /**
     * A helper class that instantiates a LinkedList node. I'm not quite sure why you should use generics in this case
     * but the implementation in the java STL does so whatever, I'll role with that.
     * @param <E>
     */
    private static class Node<E>
    {
        E value;
        Node<E> next;
        Node<E> previous;

        private Node(E value, Node<E> previous, Node<E> next)
        {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }


    public LinkedList()
    {
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }


    public LinkedList(Collection<? extends E> c)
    {
        this();
        this.addAll(c);
    }


    @Override
    public int size()
    {
        return size;
    }


    @Override
    public String toString()
    {
        Node<E> current = sentinel.next;
        String beans = "[";

        while (current != sentinel)
        {
            beans += current.value +", ";
            current = current.next;
        }

        if (beans.equals("["))
            beans = "[]";
        else
            beans = beans.substring(0, beans.length() - 2) + "]";

        return beans;
    }


    @Override
    public ListIterator<E> listIterator(int index)
    {
        return new LinkedListIterator(index);
    }


    /**
     * The LinkedListIterator has three fields. Current represents the current node that the iterator cursor is meant
     * to reference. The index should always be the index of the current node. Finally, last returned represents the
     * node of the last returned value for any method in the LLIterator instance. Keeping track of these three fields
     * helps implement key methods such as add, set, and remove.
     *
     * Since a java.util.ListIterator is a java.util.Iterator, you can automatically use foreach syntax to traverse
     * this linked list. Nice bonus if you ask me.
     */
    private class LinkedListIterator implements ListIterator<E>
    {
        private Node<E> lastReturned = sentinel;
        private Node<E> current;
        private int index;

        LinkedListIterator(int index)
        {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException(String.format("Index %d, size %d", index, size));

            if (index < size / 2)
            {
                current = sentinel.next;
                for (this.index = 0; this.index < index; this.index ++)
                    current = current.next;
            }
            else
            {
                current = sentinel.previous;
                for (this.index = size - 1; this.index > index; this.index --)
                    current = current.previous;
            }
        }

        @Override
        public boolean hasNext()
        {
            return current != sentinel;
        }


        /**
         * Even though the javadocs states that this method should return the next element and advance the cursor, the
         * superclass AbstractSequentialList, when calling methods such as set, remove, add, etc. only uses this to
         * advance the cursor. If you advance the cursor and return the next element, you get index errors. For this
         * reason, next() returns the previously returned value.
         * @return the last returned value
         */
        @Override
        public E next()
        {
            if (! hasNext())
                throw new NoSuchElementException();

            lastReturned = current;
            current = current.next;
            index ++;
            return lastReturned.value;
        }

        @Override
        public boolean hasPrevious()
        {
            return current.previous != sentinel;
        }

        @Override
        public E previous()
        {
            if (! hasPrevious())
                throw new NoSuchElementException();

            current = current.previous;
            lastReturned = current;
            index --;
            return lastReturned.value;
        }

        @Override
        public int nextIndex()
        {
            return index;
        }

        @Override
        public int previousIndex()
        {
            return index - 1;
        }

        /**
         * This is the standard removal algorithm for a LinkedList node. It finds the target node and then dereferences
         * it by setting the previous nodes next-pointer to the next node and vice versa. CLRS has a better explanation
         * of this. In fact, basically all of the stuff in this class can be found in CLRS. Praise be to the CS gods.
         */
        @Override
        public void remove()
        {
            Node<E> lastNext = lastReturned.next;

            if (lastReturned == sentinel)
                throw new IllegalStateException();

            lastReturned.previous.next = lastReturned.next;
            lastReturned.next.previous = lastReturned.previous;

            // Mark the node for garbage collection
            lastReturned.next = null;
            lastReturned.previous = null;
            lastReturned.value = null;

            if (current == lastReturned)
                current = lastNext;
            else
                index --;

            size --;
            lastReturned = sentinel;
        }

        @Override
        public void set(E e)
        {
            if (lastReturned == sentinel)
                throw new IllegalStateException();

            lastReturned.value = e;
        }

        /**
         * Standard LinkedList add algorithm. I dunno just check CLRS
         * @param e
         */
        @Override
        public void add(E e)
        {
            lastReturned = sentinel;

            Node<E> node = new Node<>(e, current, current.next);
            node.previous.next = node;
            node.next.previous = node;

            size ++;
            index ++;
        }
    }
}
