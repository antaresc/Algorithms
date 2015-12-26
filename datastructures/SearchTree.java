package main.com.acscooter.datastructures;

/** A generic search tree that stores Key, Value pairs.
 *  @author Antares Chen
 *  @since  2015-12-25
 */
public interface SearchTree<Key extends Comparable<Key>, Value> {

    /** Returns the value associated with KEY. */
    Value find(Key key);

    /** Returns value associated with the minimum key. Optional operation. */
    Value findMin();

    /** Returns value associated with the maximum key. Optional operation. */
    Value findMax();

    /** Inserts VALUE with the key KEY into the tree. Returns the previous
     *  value associated with KEY. */
    Value insert(Key key, Value value);

    /** Removes KEY from the tree and returns the value associated with it. */
    Value remove(Key key);

    /** Returns the number of vertices in the tree. */
    int size();
}
