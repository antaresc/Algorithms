package main.com.acscooter.datastructures;

/** A simple interface for a max-priority queue. It is optimized for the
 *  removal of elements with highest priority.
 *
 *  @author      Antares Chen
 *  @since       2015-07-30 */
public interface PriorityQueue<Element>
{
    /** Inserts VALUE into the priority queue. Returns if VALUE was added. */
    boolean add(Element value);

    /** Returns if the value is empty. */
    boolean isEmpty();

    /** Returns the size of the priority queue. */
    int size();

    /** Returns the first element in the priority queue. */
    Element peek();

    /** Returns and removes the current element with highest priority. */
    Element remove();

    /** Increases the priority of E to the same value as KEY. Returns E. */
    Element increaseKey(Element e, Element key);
    
}
