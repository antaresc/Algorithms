package main.com.acscooter.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** A mutable and finite Graph object. Edge labels are stored via a hashmap
 *  where labels are mapped to a key calculated by the following.
 *
 *  Given two verticies V1, V2, and the max number of vertices M, the edge index
 *  is given by e = V1 * (M + 1) + V2. Under this schema, all unique pairs of
 *  (V1, V2) are mapped to a unique index e. However, this requires that for
 *  any vertex V, V^2 <= Integer.MAX_VALUE
 *
 *  @author Antares Chen
 *  @since  2015-12-25
 */
public class SimpleGraph<VLabel, ELabel> implements Graph<VLabel, ELabel> {

    /** Maximum vertex number. */
    private final int _maxVertex;

    /** Maps vertices to lists of predecessors. */
    private HashMap<Integer, ArrayList<Integer>> _pred = new HashMap<>();
    /** Maps vertices to lists of successors. */
    private HashMap<Integer, ArrayList<Integer>> _succ = new HashMap<>();
    /** Maps vertices to labels. */
    private HashMap<Integer, VLabel> _vlabels = new HashMap<>();
    /** Maps edges to labels. */
    private HashMap<Integer, ELabel> _elabels = new HashMap<>();


    /** A Graph whose vertices are all <= MAXVERTEX.  If the graph is
     *  to have edge labels, MAXVERTEX ** 2 should be <=
     *  Integer.MAX_VALUE or the results may be undefined.  */
    SimpleGraph(int maxVertex) {
        _maxVertex = maxVertex;
    }

    @Override
    public void addVertex(Integer v) {
        if (v < 0 || v > _maxVertex) {
            throw new IllegalArgumentException();
        }
        if (_succ.get(v) == null) {
            _succ.put(v, new ArrayList<Integer>());
            _pred.put(v, new ArrayList<Integer>());
        }
    }

    @Override
    public void addEdge(Integer v1, Integer v2) {
        addVertex(v1);
        addVertex(v2);

        _succ.get(v1).add(v2);
        _pred.get(v2).add(v1);
    }


    @Override
    public void addVLabel(Integer v, VLabel label) {
        _vlabels.put(v, label);
    }

    @Override
    public void addELabel(Integer v1, Integer v2, ELabel label) {
        int index = v1 * (_maxVertex + 1) + v2;
        _elabels.put(index, label);
    }

    @Override
    public VLabel getVLabel(Integer v) {
        return _vlabels.get(v);
    }

    @Override
    public ELabel getELabel(Integer v1, Integer v2) {
        int index = v1 * (_maxVertex + 1) + v2;
        return _elabels.get(index);
    }

    @Override
    public boolean contains(Integer v) {
        return _pred.get(v) != null;
    }

    @Override
    public boolean contains(Integer v1, Integer v2) {
        return contains(v1) && successors(v1).contains(v2);
    }

    @Override
    public List<Integer> successors(Integer v) {
        return _succ.get(v);
    }

    @Override
    public List<Integer> predecessors(Integer v) {
        return _pred.get(v);
    }

}
