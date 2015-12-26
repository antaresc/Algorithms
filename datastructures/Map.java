package main.com.acscooter.datastructures;

import java.util.Collection;
import java.util.Set;

/** An interface describing a generic map between two collection of objects.
 *  @author Antares Chen
 *  @since  2015-12-22
 */
public interface Map<Key, Value> {

    /** Clears the Map of all Key, Value pairs. */
    void clear();

    /** Returns the number of elements stored in the map. */
    int size();

    /** Returns if the map is empty. */
    boolean isEmpty();

    /** Returns the value previously associated with K and then associates K
     *  with the new value V in the map. If K was previously not a key in the
     *  the map, then null is returned. */
    Value put(Key k, Value v);

    /** Returns the value associated with K in the map. If no values is
     *  associated, null is returned. */
    Value get(Key k);

    /** Returns if K is a key in the map. */
    boolean containsKey(Key k);

    /** Returns if V is a value in the map. */
    boolean containsValue(Value v);

    /** Returns a Set view of all the keys in the map. */
    Set<Key> keySet();

    /** Returns a Collection view of all the values in the map. */
    Collection<Value> values();

    /** Returns if O is equal to the current object. */
    boolean equals(Object o);

    /** Returns the hashcode associated with this object. */
    int hashCode();

}
