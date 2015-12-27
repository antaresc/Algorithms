package main.com.acscooter.datastructures;

/** An implementation of the Red-Black tree datastructure. A Red-Black tree is
 *  a Binary Search Tree with the added properties of color, allowing it to be
 *  height balanced. Specifically they have the following properties:
 *
 *  (1) Every node is either red or black.
 *  (2) Every leaf (null node) is black.
 *  (3) If a node is red then both its children are black.
 *  (4) Every simple path from root to leaf node contains the same number of
 *      black nodes.
 *
 *  Search is simple as it still follows the BST property; however, insertion
 *  and deletion are both complicated and require many separate cases to handle.
 *
 *  @author Antares Chen
 *  @since  2015-12-26 */
public class RedBlackTree<Key extends Comparable<Key>, Value>
    implements SearchTree<Key, Value> {

    /** The root node of this Red-Black tree. */
    private RBNode _root;

    /** A basic constructor for a Red-Black tree. */
    public RedBlackTree() {
        _root = null;
    }

    @Override
    public Value find(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return find(key, _root);
    }

    /** Returns the value associated with KEY starting at NODE. Returns null if
     *  KEY not found. */
    private Value find(Key key, RBNode node) {
        if (node == null) {
            return null;
        }

        int comparator = key.compareTo(node._key);

        if (comparator < 0) {
            return find(key, node._left);
        } else if (comparator > 0) {
            return find(key, node._right);
        } else {
            return node._value;
        }
    }

    @Override
    public Value insert(Key key, Value value) {
        if (key == null) {
            throw new NullPointerException();
        } else if (value == null) {
            return remove(key);
        }

        Value result = find(key);
        _root = insert(key, value, _root);
        _root.isRed = false;
        return result;
    }

    /** Returns the previous value associated with KEY and adds KEY, VALUE to
     *  the Red-Black Tree starting at NODE. */
    private RBNode insert(Key key, Value value, RBNode node) {
        if (node == null) {
            return new RBNode(key, value, true);
        }

        int comparator = key.compareTo(node._key);

        if (comparator < 0) {
            node._left = insert(key, value, node._left);
        } else if (comparator > 0) {
            node._right = insert(key, value, node._right);
        } else {
            node._value = value;
        }

        /** Rebalances the tree appropriately. */
        if (isRed(node._right) && !isred(node._left)) {
            node = rotateLeft(node);
        }
        if (isRed(node._left) && isRed(node._left._left)) {
            node = rotateRight(node);
        }
        if (isRed(node._right) && isRed(node._left)) {
            flip(node);
        }

        return node;
    }

    @Override
    public Value remove(Key key) {
        if (key == null) {
            throw new NullPointerException();
        } else if (find(key) == null) {
            return null;
        }

        if (!isRed(_root._left) && !isRed(_root._right)) {
            _root._isRed = true;
        }
        _root = remove(key, _root);
        if (_root != null) {
            _root._isRed = false;
        }
    }

    /** Removes KEY from the Red-Black tree starting at NODE. */
    public RBNode remove(Key key, RBNode node) {
        int comparator = key.compareTo(node._key);

        if (comparator < 0) {
            if (!isRed(node._left) && !isRed(node._left._left)) {
                node = shiftLeft(node);
            }
            node._left = remove(key, node._left);
        } else {
            if (isRed(node._left)) {
                node = rotateRight(node);
            }
            if (comparator == 0 && node._right == null) {
                return null;
            }
            if (!isRed(node._right) && !isRed(node._right._left)) {
                node = shiftRight(node);
            }
            if (comparator == 0) {
                RBNode min = findMin(node._right);
                node._key = min._key;
                node._value = min._value;
                node._right = removeMin()
            } else {
                node._rigth = remove(key, node._right);
            }
        }

        return rebalance(node);
    }

    @Override
    public Value findMin() {
        return findMin(_root)._value;
    }

    /** Returns the minimum value of the subtree rooted at NODE. */
    private Node findMin(RBNode node) {
        if (node._left == null) {
            return node;
        }
        return findMin(node._left);
    }

    @Override
    public Value findMax() {
        throw new UnsupportedOperationException();
    }

    /** Returns if the RBNode is colored red. If the node is null, false. */
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node._isRed;
        }
        return false;
    }

    /** Flips all the colors of NODE and its two immediate children. */
    private void flip(RBNode node) {
        node._isRed = !node._isRed;
        node._left._isRed = !node._left._isRed;
        node._right._isRed = !node._right._isRed;
    }

    /** Returns the node at position NODE after a rebalancing. */
    private RBNode rebalance(RBNode node) {
        if (isRed(node._right)) {
            node = rotateLeft(node);
        }
        if (isRed(node._left) && isRed(node._left._left)) {
            node = rotateRight(node);
        }
        if (isRed(node._left) && isRed(node._right)) {
            flip(node)
        }
        return node;
    }

    /** Returns the left child of NODE and shifts the Red-Black tree to be right
     *  leaning. */
    private RBNode rotateRight(RBNode node) {
        RBNode oldLeft = node._left;
        node._left = oldLeft._right;

        oldLeft._right = node;
        oldLeft._isRed = oldLeft._right._isRed;
        oldLeft._right._isRed = true;

        return oldLeft;
    }

    /** Returns the right child of NODE and shifts the Red-Black tree to be left
     *  leaning. */
    private RBNode rotateLeft(RBNode node) {
        RBNode oldRight = node._right;
        node._right = oldRight._left;

        oldRight._left = node;
        oldRight._isRed = oldRight._left._isRed;
        oldRight._left._isRed = true;

        return oldRight;
    }

    /** Returns the node replacing NODE after making either right child or one
     *  of its children red. Requires that both the right child and the left of
     *  the right child are black. */
    private RBNode shiftRight(RBNode node) {
        flip(node);
        if (isRed(node._left._left)) {
            node = rotateRight(node);
            flip(node);
        }
        return node;
    }

    /** Returns the node replacing NODE after making either its left child or
     *  one of the left's childen red. Requires that the left child and the
     *  left left child are black. */
    private RBNode shiftLeft(RBNode node) {
        flip(node);
        if (isRed(node._right._left)) {
            node._right = rotateRight(node);
            node = rotateLeft(node);
            flip(node);
        }
        return node;
    }

    /** Helper class that represents a node in the Red-Black tree. */
    private static class RBNode {

        /** The Key associated with this node. */
        private Key _key;
        /** The Value associated with this node. */
        private Value _value;
        /** True iff this node is a red node. */
        private boolean _isRed;
        /** The left child of this node. */
        private RBNode _left;
        /** The right child of this node. */
        private RBNode _right;

        /** A constructor for RBNode. */
        RBNode(Key key, Value value, Boolean isRed) {
            _key = key;
            _value = value;
        }
    }
}
