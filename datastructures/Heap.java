package main.com.acscooter.datastructures;

/** A generic Max-Heap that stores comparable values.
 *  @author Antares Chen
 *  @since  2015-12-25
 */
public interface Heap<Value extends Comparable<Value>> {

    /** Returns the max value of the heap. */
    Value findMax();

    /** Removes the max value from the heap. Returns the max value. */
    Value removeMax();

    /** Inserts VALUE into the heap. */
    void insert(Value value);

    /** Returns the current VALUE and increases the importance of VALUE to
     *  NEWVALUE. */
    Value increaseKey(Value value, Value newValue);
    
}
