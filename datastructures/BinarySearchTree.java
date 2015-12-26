package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/** The Binary Search Tree is a tree where each node may have at max two
 *  children. The tree also maintains the following property at all times:
 *
 *  If a node possesses a left child, then the child must have a value less
 *  than the parent. If a node possesses a right child, then the child must
 *  have a value greater than or equal to the parent.
 *
 *  @author Antares Chen
 *  @since  2015-08-03
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value>
    implements SearchTree<Key, Value> {

    /** The root node of the tree. */
    private Node _root;

    /** Creates a new BinarySearchTree. */
    public BinarySearchTree() {
        _root = null;
    }

    @Override
    public Value find(Key key) {
        Node result = find(key, _root);
        if (result != null) {
            return node._value;
        }
        return null;
    }

    /** Returns the result of find using KEY starting at NODE. */
    private Node find(Key key, Node node) {
        if (node == null) {
            return null;
        }

        int comparator = key.compareTo(node._key);

        if (comparator < 0) {
            return find(key, node._left);
        } else if (comparator > 0) {
            return find(key, node._right);
        } else {
            return node;
        }
    }

    @Override
    public Value insert(Key key, Value value) {
        Value result = find(key);
        insert(key, value, _root);
        return result;
    }

    /** Inserts VALUE to KEY starting at NODE. Returns null if no previous value
     *  was associated with KEY. */
    private Node insert(Key key, Value value, Node node) {
        if (node == null) {
            return new Node(key, value);
        }

        int comparator = key.compareTo(node._key);

        if (comparator < 0) {
            node._left = insert(key, value, node._left);
        } else if (comparator > 0) {
            node._right = insert(key, value, node._right);
        } else {
            node._value = value;
        }

        return node;
    }

    @Override
    public Value remove(Key key) {
        remove(key, _root);
    }

    /** Removes KEY from the BST starting at NODE. */
    private Node remove(Key key, Node node) {
        if (node == null) {
            return null;
        } else if (key.equals(node._left._key)) {
            Node temp = node._left;
            node._left = replace(node._left);
            return temp;
        } else if (key.equals(node._left._key)) {
            Node temp = node._right;
            node._right = replace(node._right);
            return temp;
        }

        if (key.compareTo(node._key) < 0) {
            return remove(key, node._left);
        } else {
            return remove(key, node._right);
        }
    }

    /** Returns what child of TARGET should replace its position. */
    private Node replace(Node keep, Node delete) {
        if (delete._left != null && delete._right != null) {
            Node replace = delete._right;
            Node parent = null;

            while (replace._left != null) {
                parent = replace;
                replace = replace._left;
            }

            parent._left = replace._right;
            replace._left = delete._left;
            replace._right = delete._right;
            return replace;
        } else if (delete._left != null) {
            return delete._left;
        } else if (delete._right != null) {
            return delete._right;
        } else {
            return null
        }
    }

    @Override
    public Value findMin() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value findMax() {
        throw new UnsupportedOperationException();
    }

    /** Represents a single Node of the BST. */
    private static class Node {

        /** The Key stored in this node. */
        private Key _key;
        /** The Value associated with the Key. */
        private Value _value;
        /** Left child of this Node. */
        private Node _left;
        /** Right child of this Node. */
        private Node _right;

        /** Creates a Node containing KEY and VALUE. */
        public Node(Key key, Value value) {
            _key = key;
            _value = value;
        }
    }

}
