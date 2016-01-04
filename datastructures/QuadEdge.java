package main.com.acscooter.datastructures;

/** The QuadEdge data structure was first proposed by Guibas and Stolfi in
 *  order to provide a faster method of planar subdivision.
 *
 *  The QuadEdge seeks to efficiently encapsulate the relationship between
 *  primal and dual graohs. Given a (primal) graph with vertex v, edge e, and
 *  face f, there exists a dual graph with vertex v* corresponding to f, edge e*
 *  corresponding to e, and face f* corresponding to v. Primal edges connect
 *  primal vertices that separate primal faces while dual edges connect dual
 *  vertices separating dual faces.
 *
 *  This data structure consists of four directed and oriented edges and a set
 *  of functions that formally construct an edge algebra. More details on that
 *  can be found in the original Guibas-Stolfi paper.
 *  @author Antares Chen
 *  @since  2016-1-3
 */
public class QuadEdge<Edge> {

    /** The next quad edge for iteration purposes. */
    private QuadEdge _next;
    /** The dual quad edge. */
    private QuadEdge _rot;
    /** The origin (tail) point of the QuadEdge. */
    private Point _orig;
    /** Data associated with this edge. */
    private Edge _data;

    /** Private constructor to create a quad edge using ORIG, NEXT, and ROT. */
    private QuadEdge(Point orig, QuadEdge next, QuadEdge rot) {
        _orig = orig;
        _next = next;
        _rot = rot;
        _data = null;
    }

    /** Creates a quad edge between two points ORIG and DEST. */
    public static makeEdge(Point orig, Point dest) {
        QuadEdge q0 = new QuadEdge(null, null, orig);
        QuadEdge e0 = new QuadEdge(null, null, null);
        QuadEdge q1 = new QuadEdge(null, null, dest);
        QuadEdge e1 = new QuadEdge(null, null, null);

        q0.oNext = q0;
        q1.oNext = q1;
        e0.oNext = e1;
        e1.oNext = e0;

        q0.rot = e0;
        e0.rot = q1;
        q1.rot = e1;
        e1.rot = q0;

        return q0;
    }

    /** Creates a QuadEdge between two points ORIG and DEST with DATA as the
     *  associated data. */
    public static makeEdge(Point orig, Point dest, Edge data) {
        _data = data;
        return makeEdge(orig, dest);
    }

    /** Returns the data associated with this quad edge. */
    public Edge getData() {
        return _data;
    }

    /** Returns the origin point of this quad edge. */
    public Point getOrig() {
        return _orig;
    }

    /** Returns the destination point of this quad edge. */
    public Point getDest() {
        sym().getOrig();
    }

    /** Sets the data associated with this QuadEdge to DATA. */
    public void setData(Edge data) {
        _data = data;
    }

    /** Sets the next quad edge to NEXT. */
    public void setNext(QuadEdge next) {
        _next = next;
    }

    /** Sets the origin point to P. */
    public void setOrig(Point p) {
        _orig = p;
    }

    /** Sets the destination point to P. */
    public void setDest(Point p) {
        sym().setOrig(p);
    }

    /** Returns the dual quad edge. */
    public QuadEdge rot() {
        return _rot;
    }

    /** Returns the dual of this quad edge flipped. */
    public QuadEdge rotInv() {
        return _rot.sym();
    }

    /** Returns the symmetric quad edge, the edge with orig and dest flipped. */
    public QuadEdge sym() {
        return _rot.rot();
    }

    /** Returns the next quad edge counterclockwise about the origin. */
    public QuadEdge oNext() {
        return _next;
    }

    /** Returns the previous quad edge counterclockwise about the origin. */
    public QuadEdge oPrev() {
        return _rot.next().rot();
    }

    /** Returns the next quad edge counterclockwise about the destination. */
    public QuadEdge dNext() {
        return sym().oNext().sym();
    }

    /** Returns the next quad edge counterclockwise about the destination. */
    public QuadEdge dPrev() {
        return rotInv().oNext().rotInv();
    }

    /** Returns the next quad edge counterclockwise about the left face. */
    public QuadEdge lNext() {
        return rotInv().oNext().rot();
    }

    /** Returns the previous quad edge counterclockwise about the left face. */
    public QuadEdge lPrev() {
        return _next.sym();
    }

    /** Returns the next quad edge counterclockwise about the right face. */
    public QuadEdge rNext() {
        return _rot.next().rotInv();
    }

    /** Returns the previous quad edge counterclockwise about the right face. */
    public QuadEdge rPrev() {
        return sym().oNext();
    }

    /** Splices Q1 with Q2. As defined in the original paper, splice is an
     *  operation such that the following holds.
     *
     *  If two rings are distinct, splice will combine them into one. If two are
     *  exactly the same ring, then splice will break it into two separate
     *  pieces. If two rings are the same taken with opposite orientation, then
     *  splice will flip a segment of that ring. */
    public static void splice(QuadEdge q1, QuadEdge q2) {
        QuadEdge dual1 = q1.oNext().rot();
        QuadEdge dual2 = q2.oNext().rot();

        q1.setNext(q2.oNext());
        q2.setNext(q1.oNext());

        dual1.setNext(dual2.oNext());
        dual2.setNext(dual1.oNext());
    }

    /** Returns a new QuadEdge connecting the destination of Q1 to the origin
     *  of Q2 maintaining that all three have the same left face. */
    public static QuadEdge connect(QuadEdge q1, QuadEdge q2) {
        QuadEdge result = makeEdge(q1.getDest(), q2.getOrig());
        splice(result, q1.lNext());
        splice(result.sym(), q2);
        return result;
    }

    /** Rotates Q counterclockwise given that Q is within an enclosing
     *  quadrilateral. */
    public static void swap(QuadEdge q) {
        QuadEdge prev = q.oPrev();
        QuadEdge sPrev = q.sym().oPrev();

        splice(q, prev);
        splice(q.sym(), sPrev);
        splice(q, prev.lNext());
        splice(q.sym(), sPrev.lNext());

        e.setOrig(prev.getDest());
        e.setDest(sPrev.getDest());
    }

}
