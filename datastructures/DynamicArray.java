package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author      Antares Chen
 * @since       2015-07-22
 *
 * DynamicArray
 */
@SuppressWarnings("unchecked")
public final class DynamicArray<E> extends AbstractList<E> implements Iterable<E>, Serializable, List<E>
{
    private static final long serialVersionUID = -2828336762210655162L;
    private E[] oldArray;
    private E[] currentArray;
    private int shadow;
    private int end;
    private boolean isDynamic;

    public DynamicArray()
    {
        this(1);
    }

    public DynamicArray(int initialCapacity)
    {
        currentArray = (E[]) new Object[initialCapacity];
        oldArray = currentArray;
        shadow = -1;
        end = 0;
        isDynamic = false;
    }

    public DynamicArray(Collection<? super E> c)
    {
        this((E[]) c.toArray());
    }

    public DynamicArray(E[] array)
    {
        currentArray = array;
        oldArray = null;
        isDynamic = true;
        shadow = currentArray.length - 1;
        end = currentArray.length;
    }

    @Override
    public boolean add(E e)
    {
        if (isDynamic)
        {
            if (end == currentArray.length)
            {
                oldArray = currentArray;
                currentArray = (E[]) new Object[oldArray.length * 2];

                shadow = end - 1;
            }

            currentArray[end] = e;
            currentArray[shadow] = oldArray[shadow];
            oldArray[shadow] = null;

            end++;
            shadow--;
        }
        else
        {
            currentArray[end] = e;
            end++;
            isDynamic = (end == currentArray.length);
        }
        return true;
    }

    @Override
    public void add(int index, E element)
    {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(String.format("Index %d, size %d", index, size()));

        if (isDynamic)
        {
            add(element);

            if (index > shadow)
            {
                System.arraycopy(currentArray, index, currentArray, index + 1, (end - 1) - index);
                currentArray[index] = element;
            }
            else
            {
                System.arraycopy(currentArray, shadow + 1, currentArray, shadow + 2, (end - 1) - (shadow + 1));

                System.arraycopy(oldArray, index, oldArray, index + 1, (shadow + 1) - index);

                oldArray[index] = element;
                currentArray[shadow + 1] = oldArray[shadow + 1];
                oldArray[shadow + 1] = null;


            }
        }
        else
        {
            System.arraycopy(currentArray, index, currentArray, index + 1, end - index);

            currentArray[index] = element;
            oldArray = currentArray;
            end ++;
            isDynamic = (end == currentArray.length);
        }

    }

    @Override
    public E get(int index)
    {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException(String.format("Index %d, size %d", index, size()));

        if (index <= shadow)
            return oldArray[index];
        return currentArray[index];
    }

    @Override
    public int size()
    {
        return end;
    }

    // In the non-dynamic stage, comparing things to shadow and accessing oldArray would break things, but since we
    // cheat and set oldArray to currentArray during non-dynamic stage, then its all good.
    @Override
    public E set(int index, E value)
    {
        E original = get(index);

        if (index <= shadow && isDynamic)
            oldArray[index] = value;
        else
            currentArray[index] = value;

        return original;
    }

    @Override
    public E remove(int index)
    {
        E target = get(index);

        if (isDynamic)
        {
            if (index > shadow)
            {
                System.arraycopy(currentArray, index + 1, currentArray, index, (end - 1) - index); // shifting

                oldArray[shadow + 1] = currentArray[shadow + 1];

                currentArray[end - 1] = null;
                currentArray[shadow + 1] = null;

                shadow++;
                end--;
            }
            else
            {
                System.arraycopy(oldArray, index + 1, oldArray, index, shadow - index);

                for (int i = 0; i < 2; i++)
                {
                    oldArray[shadow + i] = currentArray[shadow + 1 + i];
                    currentArray[shadow + 1 + i] = null;
                }

                shadow++;

                System.arraycopy(currentArray, shadow + 2, currentArray, shadow + 1, end - shadow - 1);

                currentArray[end - 1] = null;
                end--;
            }
            if (end == shadow + 1)
            {
                currentArray = oldArray;

                oldArray = (E[]) new Object[currentArray.length / 2];
                shadow = -1;
                end = currentArray.length;
            }
        }

        else
        {
            System.arraycopy(currentArray, index + 1, currentArray, index, end - (index + 1));
            currentArray[end - 1] = null;
            oldArray = currentArray;
            end --;
        }

        if (this.size() <= 3)
        {
            isDynamic = false;
            oldArray = currentArray;
            shadow = -1;
        }
        return target;
    }

    public String toString()
    {
        String beans = "[";

        for (E e : this)
            beans += e + ", ";

        if (beans.equals("["))
            beans = "[]";
        else
            beans = beans.substring(0, beans.length() - 2) + "]";

        return beans;
    }

    @Override
    public Iterator<E> iterator()
    {
        return new DynamicIterator();
    }

    private class DynamicIterator implements Iterator<E>
    {
        int index = 0;

        @Override
        public boolean hasNext()
        {
            return index != size();
        }

        @Override
        public E next()
        {
            if (!hasNext())
                throw new NoSuchElementException();

            E current = get(index);
            index ++;
            return current;
        }
    }
}
