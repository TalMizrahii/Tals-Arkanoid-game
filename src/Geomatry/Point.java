package Geomatry;

/**
 * the Point class.
 *
 * <p>this class include methods which connected to Points and their location.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Point {
    //the x and y coordinates.
    private double x;
    private double y;
    private static final double EPSILON = Math.pow(10, -10);

    /**
     * setting the x coordinate.
     *
     * @param x the x value coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * setting the y coordinate.
     *
     * @param y the y coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * constructor for creating a  new point creating a point whith x and y coordinates.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * calculating the distance between two points.
     *
     * @param other the second point to measure the distance from.
     * @return an integer of the value of the distance.
     */
    public double distance(Point other) {
        double x2 = getX();
        double y2 = getY();
        return Math.sqrt(((other.x - x2) * (other.x - x2)) + ((other.y - y2) * (other.y - y2)));
    }

    /**
     * return's true is the points are equal, false otherwise.
     *
     * @param other the second point to determent if the points are equal.
     * @return boolean operator.
     */
    public boolean equals(Point other) {
        double x2 = this.getX();
        double y2 = this.getY();
        return (Math.abs(x2 - other.getX()) <= EPSILON) && (Math.abs(y2 - other.getY()) <= EPSILON);
    }

    /**
     * a getter for the x value.
     *
     * @return a double x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * a getter for the y value.
     *
     * @return a double y value.
     */
    public double getY() {
        return this.y;
    }
}