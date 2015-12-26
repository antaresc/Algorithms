package main.com.acscooter.datastructures;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
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
public class BinarySearchTree<K extends Comparable<K>, V> implements Map<K, V> {

    public BinarySearchTree() {

    }

    
}
