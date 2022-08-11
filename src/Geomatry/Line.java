package Geomatry;

import SpriteRelated.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * the Line class.
 *
 * <p>includes methods who connected to lines and the intersections.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Line implements Sprite {
    //the start and end of each line.
    private Point start;
    private Point end;
    private static final double EPSILON = Math.pow(10, -10); //a THRESHOLD value.
    private Color color; // the color of the line.

    /**
     * a constructor who creates a new line from start and end point.
     *
     * @param start the line's start.
     * @param end   the line's end.
     */
    public Line(Point start, Point end) {
        setStart(start);
        setEnd(end);
    }

    /**
     * a constructor who creates a new line from four coordinates in the space.
     *
     * @param x1 x coordinate of start.
     * @param y1 y coordinate of start.
     * @param x2 x coordinate of end.
     * @param y2 y coordinate of end.
     */
    public Line(double x1, double y1, double x2, double y2) {
        //creating new points
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        //setting the lines members to be the coordinates who have been sent.
        setStart(start);
        setEnd(end);
    }

    /**
     * a getter for the color of the line.
     *
     * @return the value of the color of the line.
     */
    public Color getColor() {
        if (this.color == null) {
            return Color.BLUE;
        }
        return this.color;
    }

    /**
     * a setter for the color of the line.
     *
     * @param color the color value.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * setting the value of the start of the line.
     *
     * @param start value to store.
     */
    public void setStart(Point start) {
        this.start = start;
    }

    /**
     * setting the value of the end of the line.
     *
     * @param end value to store.
     */
    public void setEnd(Point end) {
        this.end = end;
    }

    /**
     * calculating the point's length.
     *
     * @return double variable.
     */
    // Return the length of the line
    public double length() {
        Point one = start();
        Point two = end();
        return one.distance(two);
    }

    /**
     * calculating the point's middle coordinate.
     *
     * @return Point variable.
     */
    public Point middle() {
        //creating the points to calculate the middle.
        Point one = start();
        Point two = end();
        double x1 = one.getX();
        double y1 = one.getY();
        double x2 = two.getX();
        double y2 = two.getY();
        //returning the point's middle with the Geomatry.Point class's method.
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * getting the value of the start's point of the line.
     *
     * @return a Geomatry.Point variable of the start of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * getting the value of a point's end.
     *
     * @return a Geomatry.Point variable of the end of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculating the incline of a line.
     *
     * @param start start point.
     * @param end   end point.
     * @return double variable of the incline.
     */
    public double incline(Point start, Point end) {
        if (start.getX() == end.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return ((start.getY() - end.getY()) / (start.getX() - end.getX()));
    }

    /**
     * checking if the lines intersects.
     *
     * @param other a Geomatry.Line variable to check if it intersects with the main line.
     * @return boolean operator.
     */
    public boolean isIntersecting(Line other) {
        Line check = new Line(start(), end());
        //if the both lines are verticals.
        if ((start.getX() == end.getX()) && (other.start.getX() == other.end.getX())) {
            //if they are verticals but different x's.
            if (start.getX() != other.start.getX()) {
                return false;
            } else {
                return containY(start, end, other.start, other.end);
            }
        }
        //result check variable.
        Point result;
        //if one of the lines has infinite incline.
        if (start.getX() == end.getX()) {
            result = check.semiParallel(other);
            return result != null;
        }
        if (other.start.getX() == other.end.getX()) {
            result = other.semiParallel(check);
            return result != null;
        }

        //getting the incline of the lines.
        double m1 = incline(start(), end());
        double m2 = incline(other.start, other.end);
        //calculating the loose parameter.
        double sum1 = start.getY() - m1 * start.getX();
        double sum2 = other.start.getY() - m2 * other.start.getX();
        //if the incline is the same.
        if (m1 == m2) {
            if (sum1 != sum2) {
                return false;
            }
            return containX(start, end, other.start, other.end);
        }
        //calculating the coordinate of the infinite intersection.
        double xCor = (sum2 - sum1) / (m1 - m2);
        //checking if the coordinate is in the range of the lines
        return (((xCor + EPSILON >= start.getX() && xCor - EPSILON <= end.getX())
                || (xCor - EPSILON <= start.getX() && xCor + EPSILON >= end.getX()))
                && ((xCor + EPSILON >= other.start.getX() && xCor - EPSILON <= other.end.getX())
                || (xCor - EPSILON <= other.start.getX() && xCor + EPSILON >= other.end.getX())));
    }

    /**
     * checking if the lines contain each other.
     *
     * @param start1 point variable.
     * @param start2 point variable.
     * @param end1   point variable.
     * @param end2   point variable.
     * @return boolean variable.
     */
    public boolean containY(Point start1, Point end1, Point start2, Point end2) {
        //if the lines contain each other
        return (((start2.getY() + EPSILON >= start1.getY()) && (start2.getY() - EPSILON <= end1.getY()))
                || ((start2.getY() - EPSILON <= start1.getY()) && (start2.getY() + EPSILON >= end1.getY()))
                || ((end2.getY() + EPSILON >= start1.getY()) && (end2.getY() - EPSILON <= end1.getY()))
                || ((end2.getY() - EPSILON <= start1.getY()) && (end2.getY() + EPSILON >= end1.getY())));
    }


    /**
     * checking if the lines contain each other.
     *
     * @param start1 point variable.
     * @param start2 point variable.
     * @param end1   point variable.
     * @param end2   point variable.
     * @return boolean variable.
     */
    public boolean containX(Point start1, Point end1, Point start2, Point end2) {
        //if the lines contain each other
        return (((start2.getX() + EPSILON >= start1.getX()) && (start2.getX() - EPSILON <= end1.getX()))
                || ((start2.getX() - EPSILON <= start1.getX()) && (start2.getX() + EPSILON >= end1.getX()))
                || ((end2.getX() + EPSILON >= start1.getX()) && (end2.getX() - EPSILON <= end1.getX()))
                || ((end2.getX() - EPSILON <= start1.getX()) && (end2.getX() + EPSILON >= end1.getX())));
    }

    /**
     * Returns the intersection point if the lines intersecting, and null otherwise.
     *
     * @param other a line variable.
     * @return a point of intersection.
     */
    public Point intersectionWith(Line other) {
        Line check = new Line(start(), end());
        //checking if there is any intersection at all.
        if (!check.isIntersecting(other)) {
            return null;
        }
        //if the its the same line.
        if (this.equals(other)) {
            return null;
        }
        //if the both lines are verticals.
        if ((start.getX() == end.getX()) && (other.start.getX() == other.end.getX())) {
            return check.parallelY(other);
        }
        //if one of the lines has infinite incline.
        if (start.getX() == end.getX()) {
            return check.semiParallel(other);
        }
        if (other.start.getX() == other.end.getX()) {
            return other.semiParallel(check);
        }
        //getting the incline of the lines.
        double m1 = incline(start(), end());
        double m2 = incline(other.start, other.end);
        //if the lines are parallel.
        if (m1 == m2) {
            return check.parallelX(other);
        }
        //calculating the loose parameter.
        double sum1 = start.getY() - m1 * start.getX();
        double sum2 = other.start.getY() - m2 * other.start.getX();
        //calculating the coordinate of the infinite intersection.
        double xCor = (sum2 - sum1) / (m1 - m2);
        //checking if the coordinate is in the range of the lines
        if (((xCor + EPSILON >= start.getX() && xCor - EPSILON <= end.getX())
                || (xCor - EPSILON <= start.getX() && xCor + EPSILON >= end.getX()))
                && ((xCor + EPSILON >= other.start.getX() && xCor - EPSILON <= other.end.getX())
                || (xCor - EPSILON <= other.start.getX() && xCor + EPSILON >= other.end.getX()))) {
            double yCor = (m1 * xCor) + sum1;
            return new Point(xCor, yCor);
        }
        return null;
    }

    /**
     * given two parallel lines with infinite incline, checking their intersection.
     *
     * @param other Geomatry.Line variable.
     * @return a point of intersection or null.
     */
    public Point parallelY(Line other) {

        //if both are points.
        if (start.equals(end)
                && other.start.equals(other.end)
                && start.equals(other.start)) {
            return start;
        }
        //checking if one of the lines is a point.
        if (start.equals(other.start)
                && (((end.getY() + EPSILON >= start.getY()) && (other.end.getY() <= EPSILON + start.getY()))
                || ((end.getY() <= start.getY()) && (other.end.getY() >= start.getY())))) {
            return start;
        }
        if (end.equals(other.end)
                && (((start.getY() + EPSILON >= end.getY()) && (other.start.getY() <= EPSILON + end.getY()))
                || ((start.getY() - EPSILON <= end.getY()) && (other.start.getY() + EPSILON >= end.getY())))) {
            return end;
        }
        if (other.start.equals(end)
                && (((start.getY() + EPSILON >= end.getY()) && (other.end.getY() - EPSILON <= end.getY()))
                || ((start.getY() - EPSILON <= end.getY()) && (other.end.getY() + EPSILON >= end.getY())))) {
            return end;
        }
        if (other.end.equals(start)
                && (((other.start.getY() + EPSILON >= start.getY()) && (end.getY() - EPSILON <= start.getY()))
                || ((end.getY() + EPSILON >= start.getY()) && (other.start.getY() - EPSILON <= start.getY())))) {
            return start;
        }

        return null;
    }

    /**
     * given to parallel lines, checking their intersection.
     *
     * @param other Geomatry.Line variable.
     * @return a point of intersection or null.
     */
    public Point parallelX(Line other) {

        //if both are points.
        if (start.equals(end)
                && other.start.equals(other.end)
                && start.equals(other.start)) {
            return start;
        }
        if (start.equals(other.start)
                && (((end.getX() + EPSILON >= start.getX()) && (other.end.getX() - EPSILON <= start.getX()))
                || ((end.getX() - EPSILON <= start.getX()) && (other.end.getX() + EPSILON >= start.getX())))) {
            return start;
        }
        if (end.equals(other.end)
                && (((start.getX() + EPSILON >= end.getX()) && (other.start.getX() - EPSILON <= end.getX()))
                || ((start.getX() - EPSILON <= end.getX()) && (other.start.getX() + EPSILON >= end.getX())))) {
            return end;
        }
        if (other.start.equals(end)
                && (((start.getX() + EPSILON >= end.getX()) && (other.end.getX() - EPSILON <= end.getX()))
                || ((start.getX() - EPSILON <= end.getX()) && (other.end.getX() + EPSILON >= end.getX())))) {
            return end;
        }
        if (other.end.equals(start)
                && (((other.start.getX() + EPSILON >= start.getX()) && (end.getX() - EPSILON <= start.getX()))
                || ((end.getX() + EPSILON >= start.getX()) && (other.start.getX() - EPSILON <= start.getX())))) {
            return start;
        }

        return null;
    }

    /**
     * if one of the lines is vertical.
     *
     * @param other Geomatry.Line variable.
     * @return Geomatry.Point variable of intersection.
     */
    public Point semiParallel(Line other) {
        //getting the incline of the line.
        double m = incline(other.start, other.end);
        //the y value of the not infinite incline line x position.
        double yVal = (m * start.getX()) + other.start.getY() - (other.start.getX() * m);
        //checking if the line is a point.
        if (this.start.equals(this.end)) {
            //getting the sum of the b value of the not infinite line.
            double sum = other.start.getY() - m * other.start.getX();
            if (this.start.getY() == m * this.start.getX() + sum) {
                if (((start.getX() + EPSILON >= other.start.getX())
                        && (start.getX() - EPSILON <= other.end.getX()))
                        || ((start.getX() - EPSILON <= other.start.getX())
                        && (start.getX() + EPSILON >= other.end.getX()))) {
                    return this.start;
                }
            }
        }
        //checking if the lines intersect
        if (((start.getX() + EPSILON >= other.start.getX())
                && (start.getX() - EPSILON <= other.end.getX()))
                || ((start.getX() - EPSILON <= other.start.getX())
                && (start.getX() + EPSILON >= other.end.getX()))) {
            //checking if the coordinate is in range.
            if ((yVal - EPSILON <= start.getY()
                    && yVal + EPSILON >= end.getY())
                    || (yVal + EPSILON >= start.getY()
                    && yVal - EPSILON <= end.getY())) {
                return new Point(start.getX(), yVal);
            }
        }
        return null;
    }

    /**
     * checking if two lines are equal.
     *
     * @param other the second Geomatry.Line variable.
     * @return boolean variable.
     */
    public boolean equals(Line other) {
        return ((start.equals(other.start) && end.equals(other.end))
                || (end.equals(other.start) && start.equals(other.end)));
    }

    /**
     * checking if the line is intersecting the rectangle.
     *
     * @param rect the rectangle to be checked if the line is intersecting with.
     * @return the closest intersection point to the start of the line.
     * If this line does not intersect with the rectangle, return null.
     */
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> recList = rect.intersectionPoints(this);
        //if the list is empty
        if (recList.isEmpty()) {
            return null;
        }
        //if the list has one point
        if (recList.size() == 1) {
            return recList.get(0);
        }

        if (rect.getUpperLeft().equals(this.start) || rect.getLowerLeft().equals(this.start)
                || rect.getLowerRight().equals(this.start) || rect.getUpperRight().equals(this.start)) {
            if (recList.size() >= 3) {
                return this.start;
            }
            return recList.get(1);
        }

        Point result = recList.get(0);
        for (Point point : recList) {
            if (point.distance(start()) <= result.distance(start())) {
                result = point;
            }
        }
        return result;

    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.drawLine((int) start().getX(), (int) start().getY(), (int) end().getX(), (int) end().getY());
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        //currently, empty.
    }
}