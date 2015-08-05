package main.com.acscooter.datastructures;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * @author      Antares Chen
 * @since       2015-08-03
 *
 * The binary search tree is a tree where each node may have at max two children. At all times, the tree also maintains
 * the following property:
 *
 *      If a node possesses a left child, then the child must have value less than the parent. If a node possesses a
 *      right child, then the child must have a value greater than or equal to the parent.
 *
 * The "or equal to" part is arbitrarily chosen and doesn't affect run time drastically. More comments to come later.
 * Currently trying to figure out whether or not to keep this a general binary search tree or allow this to be used
 * to back dictionary objects.
 */
public class BinarySearchTree<E extends Comparable<E>>
{
    private Node<E> root;
    private int size;

    private static class Node<E>
    {
        E value;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        Node(E value, Node<E> parent, Node<E> left, Node<E> right)
        {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    public BinarySearchTree()
    {
        this.root = null;
        this.size = 0;
    }

    public BinarySearchTree(Node<E> root)
    {
        this.root = root;
        this.size = 1;
    }

    public BinarySearchTree(Collection<E> c)
    {
        this.size = c.size();
        c.forEach(this::add);
    }

    public BinarySearchTree(E[] array)
    {
        this.size = array.length;
        for (E e : array)
            add(e);
    }

    public boolean add(E e)
    {
        Node<E> parent = null;
        Node<E> current = this.root;

        while (current != null)
        {
            parent = current;
            if (e.compareTo(current.value) < 0)
                current = current.left;
            else
                current = current.right;
        }

        Node<E> node = new Node<>(e, parent, null, null);

        if (parent == null)
            this.root = node;
        else if (e.compareTo(parent.value) < 0)
            parent.left = node;
        else
            parent.right = node;

        size ++;
        return true;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public int size()
    {
        return this.size;
    }

    public E remove(E e)
    {
        Node<E> target = recursiveFind(e, this.root);
        if (target == null)
            throw new NoSuchElementException();

        if (target.left == null)
            transplant(target, target.right);

        else if (target.right == null)
            transplant(target, target.left);
        else {
            Node<E> minimum = subtreeMinimum(target.right);

            if (minimum.parent != target) {
                transplant(minimum, minimum.right);
                minimum.right = target.right;
                minimum.right.parent = minimum;
            }

            transplant(target, minimum);
            minimum.left = target.left;
            minimum.left.parent = minimum;
        }
        return target.value;
    }

    public E find(E e)
    {
        Node<E> result = recursiveFind(e, this.root);
        if (result == null)
            throw new NoSuchElementException();
        return result.value;
    }

    public E min()
    {
        return subtreeMinimum(this.root).value;
    }

    public E max()
    {
        return subtreeMaximum(this.root).value;
    }

    private Node<E> recursiveFind(E value, Node<E> node)
    {
        if (node == null || node.value == value)
            return node;
        if (node.value.compareTo(value) < 0)
            return recursiveFind(value, node.left);
        return recursiveFind(value, node.right);
    }

    private Node<E> subtreeMinimum(Node<E> subtree)
    {
        if (subtree.left == null)
            return subtree;
        return subtreeMinimum(subtree.left);
    }

    private Node<E> subtreeMaximum(Node<E> subtree)
    {
        if (subtree.right == null)
            return subtree;
        return subtreeMaximum(subtree.right);
    }

    private void transplant(Node<E> u, Node<E> v)
    {
        if (u.parent == null)
            this.root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;

        if (v != null)
            v.parent = u.parent;
    }


}
