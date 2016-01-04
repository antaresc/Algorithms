package main.com.acscooter.datastructures;

/** Point encapsulates a two-dimensional integral point in cartesian space.
 *  @author Antares Chen
 *  @since  2016-1-3
 */
public class Point {
    /** The x-coordinate of the point. */
    private int _x;
    /** The y-coordinate of the point. */
    private int _y;

    /** A basic constructor creating Point(x, y). */
    public Point(int x, int y) {
        _x = x;
        _y = y;
    }

    /** Returns the x value of this point. */
    public int getX() {
        return _x;
    }

    /** Returns the y value of this point. */
    public int getY() {
        return _y
    }

    /** Sets the x value to X. */
    public void setX(int x) {
        _x = x;
    }

    /** Sets the y value to Y. */
    public void setY(int y) {
        _y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
