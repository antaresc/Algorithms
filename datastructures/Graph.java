package main.com.acscooter.datastructures;

import java.util.List;

/** An interface for a Graph object. Indexes all vertices as non-negative
 *  integers.
 *  @author Antares Chen
 *  @since  2015-25-12 */
public interface Graph<VLabel, ELabel> {

    /** Adds vertex V to the graph if it is not already present. Optional
     *  operation. */
    void addVertex(Integer v);

    /** Adds an edge between vertices V1 and V2. Optional operation. */
    void addEdge(Integer v1, Integer v2);

    /** Adds LABEL to vertex V. Returns the previous label associated with V.
     *  Returns null if no label was previously assigned. Optional operation. */
    VLabel addVLabel(Integer v, VLabel label);

    /** Adds LABEL to edge (V1, V2). Returns the previously associated label.
     *  Returns null if no label was previously assigned. Optional operation. */
    ELabel addELabel(Integer v1, Integer v2, ELabel label);

    /** Returns the label associated with vertex V. */
    VLabel getVLabel(Integer v);

    /** Returns the label associated with the edge (V1, V2). */
    ELabel getELabel(Integer v1, v2);

    /** Returns if vertex V is assigned. */
    boolean contains(Integer v);

    /** Returns if the edge (V1, V2) is assigned. */
    boolean contains(Integer v1, Integer v2);

    /** Returns the successors for V. */
    List<Integer> successors(Integer v);

    /** Returns the predecessors for V. */
    List<Integer> predecessors(Integer v);

}
