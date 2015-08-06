package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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
 *
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements Map<K,V>, Serializable
{
    private static final long serialVersionUID = 6512878093890355697L;
    private Node<K,V> root;
    private int size;

    /**
     * BST tree nodes keep track of parent, left, and right child. Because binary search trees are often used for
     * backing map-type data structures such as dictionaries as well as ordered data structures such as sets, each node
     * is parameterized by a key and value where the BST sorts by key.
     * @param <K>
     * @param <V>
     */
    private static class Node<K,V> implements Map.Entry<K,V>
    {
        K key;
        V value;
        Node<K,V> parent;
        Node<K,V> left;
        Node<K,V> right;

        Node(K key, V value, Node<K,V> parent, Node<K,V> left, Node<K,V> right)
        {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public K getKey()
        {
            return key;
        }

        @Override
        public V getValue()
        {
            return value;
        }

        @Override
        public V setValue(V value)
        {
            V previous = this.value;
            this.value = value;
            return previous;
        }
    }

    /**
     * Basic constructor sets all fields to null
     */
    public BinarySearchTree()
    {
        this.root = null;
        this.size = 0;
    }

    /**
     * A wrapper constructor for Java STL map objects
     * @param m
     */
    public BinarySearchTree(Map<? extends K, ? extends V> m)
    {
        this.root = null;
        this.size = 0;
        putAll(m);
    }

    /**
     * Because the BST property always holds, retrieving values from BSTs are relatively trivial and leads to a O(log N)
     * runtime where N is the number of elements in the BST.
     * @param key
     * @return
     */
    @Override
    public V get(Object key)
    {
        K newKey = (K) key;
        Node<K,V> result = recursiveFind(newKey, this.root);
        if (result == null)
            throw new NoSuchElementException();
        return result.value;
    }

    /**
     *
     * @param key
     * @param value
     * @return previous value assigned to key
     */
    @Override
    public V put(K key, V value)
    {
        Node<K,V> previousValue = recursiveFind(key, this.root);

        Node<K,V> parent = null;
        Node<K,V> current = this.root;

        while (current != null)
        {
            parent = current;
            if (key.compareTo(current.key) < 0)
                current = current.left;
            else
                current = current.right;
        }

        Node<K,V> node = new Node<>(key, value, parent, null, null);

        if (parent == null)
            this.root = node;
        else if (key.compareTo(parent.key) < 0)
            parent.left = node;
        else
            parent.right = node;

        size ++;

        if (previousValue == null)
            return null;
        return previousValue.value;
    }

    @Override
    public int size()
    {
        return this.size;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m)
    {
        for (K key : m.keySet())
            this.put(key, m.get(key));
    }

    @Override
    public void clear()
    {
        this.root = null;
        this.size = 0;
    }

    @Override
    public Set<K> keySet()
    {
        Collection<Entry<K, V>> list = new LinkedList<>();
        infixTreeWalk(this.root, list);

        HashSet<K> set = new HashSet<>();
        for (Entry<K, V> node : list)
            set.add(node.getKey());

        return set;
    }

    @Override
    public Collection<V> values()
    {
        Collection<Entry<K, V>> list = new LinkedList<>();
        infixTreeWalk(this.root, list);

        LinkedList<V> values = new LinkedList<>();
        for (Entry<K, V> node : list)
            values.add(node.getValue());

        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        HashSet<Map.Entry<K,V>> list = new HashSet<>();
        infixTreeWalk(this.root, list);

        return list;
    }

    @Override
    public boolean isEmpty()
    {
        return this.size == 0;
    }

    @Override
    public boolean containsKey(Object key)
    {
        K newKey = (K) key;
        return recursiveFind(newKey, this.root) != null;
    }

    @Override
    public boolean containsValue(Object value)
    {
        LinkedList<V> list = new LinkedList<>(this.values());
        return list.contains(value);
    }

    @Override
    public V remove(Object key)
    {
        K newKey = (K) key;

        Node<K,V> target = recursiveFind(newKey, this.root);
        if (target == null)
            throw new NoSuchElementException();

        if (target.left == null)
            transplant(target, target.right);

        else if (target.right == null)
            transplant(target, target.left);
        else {
            Node<K,V> minimum = subtreeMinimum(target.right);

            if (minimum.parent != target) {
                transplant(minimum, minimum.right);
                minimum.right = target.right;
                minimum.right.parent = minimum;
            }

            transplant(target, minimum);
            minimum.left = target.left;
            minimum.left.parent = minimum;
        }
        size --;
        return target.value;
    }


    public V min()
    {
        return subtreeMinimum(this.root).value;
    }

    public V max()
    {
        return subtreeMaximum(this.root).value;
    }



    private Node<K,V> recursiveFind(K key, Node<K,V> node)
    {
        if (node == null || node.key == key)
            return node;
        if (node.key.compareTo(key) < 0)
            return recursiveFind(key, node.left);
        return recursiveFind(key, node.right);
    }

    private Node<K,V> subtreeMinimum(Node<K,V> subtree)
    {
        if (subtree.left == null)
            return subtree;
        return subtreeMinimum(subtree.left);
    }

    private Node<K,V> subtreeMaximum(Node<K,V> subtree)
    {
        if (subtree.right == null)
            return subtree;
        return subtreeMaximum(subtree.right);
    }

    private void transplant(Node<K,V> u, Node<K,V> v)
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

    private void infixTreeWalk(Node<K,V> node, Collection<Map.Entry<K,V>> c)
    {
        if (node != null)
        {
            infixTreeWalk(node.left, c);
            c.add(node);
            infixTreeWalk(node.right, c);
        }
    }
}
