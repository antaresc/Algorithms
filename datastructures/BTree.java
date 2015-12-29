package main.com.acscooter.datastructures;

/** A B-Tree is another balanced Binary Search Tree that, instead of imposing
 *  colors, imposes a restriction on the number of children it has.
 *
 *  @author Antares Chen
 *  @since  2015-12-28
 */
public class BTree<Key extends Comparable<Key>, Value>
    implements SearchTree<Key, Value> {

    /** Default order of a BTree makes it a 2-4 tree. */
    private static final int DEFAULT_ORDER = 4;

    /** The root of the BTree. */
    private BNode _root;
    /** The order of the BTree. */
    private int _order;
    /** The height of the BTree. */
    private int _height;

    /** Basic constructor that returns a 2-4 Tree. */
    public BTree() {
        this(DEFAULT_ORDER);
    }

    /** Constructor that returns a B-Tree with each node having at most ORDER
     *  children. Requires that ORDER be both even and greater than 2. */
    public BTree(int order) {
        if (order % 2 == 1 || order <= 2) {
            throw new IllegalArgumentException("Must have an even order > 2");
        }
        _order = order;
        _root = new BNode(0);
    }

    @Override
    public Value find(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return find(key, _root, _height);
    }

    /** Returns the value of searching for KEY in the B-Tree rooted at NODE.
     *  The HEIGHT variable is kept to determine if the search is at root. */
    private Value find(Key key, BNode node, int height) {
        if (height) == 0 {
            for (BNode child : node._children) {
                if (key.compareTo(child._key) == 0) {
                    return child._value;
                }
            }
        } else {
            for (int i = 0; i < node.size(); i += 1) {
                if (i + 1 = node.size()
                    || key.compareTo(node.get(i + 1)._key) < 0) {
                    return find(key, node.get(i)._next, height - 1);
                }
            }
        }

        return null;
    }

    @Override
    public Value insert(Key key, Value value) {
        if (key == null) {
            throw new NullPointerException();
        }
        Value result = find(key);
        BNode curr = insert(key, value, _root, _height);
        if (curr != null) {
            BNode newRoot = new BNode(null, null, null);
            newRoot.set(0, new Entry(_root.children[0]._key, null, _root));
            newRoot.set(1, new Entry(curr.children[0]._key, null, curr));
            _root = newRoot;
            _height += 1;
        }
        return result;
    }

    /** Inserts the KEY and VALUE pair in the subtree rooted at NODE. HEIGHT is
     *  kept to determine if a root node is considered. */
    private BNode insert(Key key, Value value, BNode node, int height) {
        int i;
        BNode insert = new BNode(key, value, null);

        if (height == 0) {
            for (i = 0; i < node.size(); i += 1) {
                if (key.compareTo(node.get(i)._key) < 0) {
                    break;
                }
            }
        } else {
            for (i = 0; i < node.size(); i += 1) {
                if (i + 1 == node.size()
                    || key.compareTo(node.get(i + 1)._key) < 0) {
                    i += 1;
                    BNode curr = insert(key, value, node.get(i)._next,
                                        height + 1);
                    if (curr != null) {
                        insert._key = curr.get(0)._key;
                        insert._next = curr;
                        break;
                    } else {
                        return null;
                    }
                }
            }
        }
        for (int j = node.size(); j > i; j -= 1) {
            node.set(j, node.get(j - 1));
        }
        node.set(j, insert);
        if (node.size() >= _order) {
            return split(node);
        } else {
            return null;
        }
    }

    /** Returns the new root node with childrens comprised of NODE split into
     *  two pieces. */
    private BNode split(BNode node) {
        int sIndex = _order / 2;
        BNode head = new BNode(null, null, null);
        for (int i = 0; i < sIndex; i += 1) {
            head.set(i, node.get(sIndex + i));
        }
        return head;
    }

    @Override
    public Value remove(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Value result = find(key);
        if (result != null) {
            insert(key, null);
        }
        return result;
    }

    @Override
    public Value findMin() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value findMax() {
        throw new UnsupportedOperationException();
    }

    /** A helper class defining nodes in the B-Tree. */
    private static class BNode {

        /** The key associated with this BNode. */
        private Key _key;
        /** The value associated with this BNode. */
        private Value _value;
        /** The list of children. */
        private List<BNode> _children;

        BNode(Key key, Value value, BNode next) {
            _key = key;
            _value = value;
            _next = next;
            _children = new LinkedList<BNode>();
        }

        /** Returns the child at position INDEX. */
        BNode get(int index) {
            return _children.get(index);
        }

        /** Returns if the value at INDEX in _children was set to CHILD. */
        boolean set(int index, BNode child) {
            return _children.set(index, child);
        }

        /** Returns the number of children this BNode has. */
        int size() {
            return _children.size();
        }
    }

}
