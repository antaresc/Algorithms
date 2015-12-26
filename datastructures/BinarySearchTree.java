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
public class BinarySearchTree<Key extends Comparable<K>, Value>
    implements Map<Key, Value> {

    public BinarySearchTree() {

    }


}
