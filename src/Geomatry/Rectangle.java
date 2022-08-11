package Geomatry;

import SpriteRelated.Sprite;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * the Rectangle class.
 *
 * <p></p>contain all methods related to the rectangle, including the rectangle's color, shape and position.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Rectangle implements Sprite {
    // values of the width and height of the rectangle.
    private double width;
    private double height;
    //the four point of the rectangle.
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    //the array of lines that represent the rectangle.
    private Line[] recArr;

    /**
     * a constructor for a new rectangle object.
     *
     * @param upperLeft a point that the frame start from.
     * @param width     the width of the frame.
     * @param height    the height of the frame.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        setHeight(height);
        setWidth(width);
        setUpperLeft(upperLeft);

        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height); //lower right point
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height); //lower left point
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY()); //upper right point

        setLowerRight(lowerRight); // setting lower right point
        setLowerLeft(lowerLeft); // setting lower left point
        setUpperRight(upperRight); // setting upper right point

        setRecArr(); // creating a rectangle array
    }

    /**
     * setter for the lower left point.
     *
     * @param lowerLeft the value of the lower left point.
     */
    public void setLowerLeft(Point lowerLeft) {
        this.lowerLeft = lowerLeft;
    }

    /**
     * setter for the lower right point.
     *
     * @param lowerRight the value of the lower right point.
     */
    public void setLowerRight(Point lowerRight) {
        this.lowerRight = lowerRight;
    }

    /**
     * setter for the upper right point.
     *
     * @param upperRight the value of the upper right point.
     */
    public void setUpperRight(Point upperRight) {
        this.upperRight = upperRight;
    }

    /**
     * a setter for the rectangle lines array.
     */
    public void setRecArr() {
        this.recArr = createRecArray();
    }

    /**
     * a setter for the rectangle lines array.
     *
     * @return the rectangle lines array.
     */
    public Line[] getRecArr() {
        setRecArr();
        return this.recArr;
    }

    /**
     * setter for the lower left point.
     *
     * @return the value of the lower left point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * setter for the lower right point.
     *
     * @return the value of the lower right point.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * getter for the upper right point.
     *
     * @return the value of the upper right point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * creating a rectangle array of lines.
     *
     * @return an array of lines describing a rectangle.
     */
    public Line[] createRecArray() {
        Line[] recArr = new Line[4];

        //0 = upper Geomatry.Line, 1 = low Geomatry.Line, 2 = left line, 3 = right Geomatry.Line.
        recArr[0] = new Line(getUpperLeft(), getUpperRight()); // up line
        recArr[1] = new Line(getLowerLeft(), getLowerRight()); // low line
        recArr[2] = new Line(getUpperLeft(), getLowerLeft()); // left line
        recArr[3] = new Line(getUpperRight(), getLowerRight()); //right line
        return recArr;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line to check if intersecting.
     * @return a list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] recArr = getRecArr();
        Point inter;
        List<Point> recList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            inter = recArr[i].intersectionWith(line);
            if (inter != null) {
                recList.add(inter);
            }
        }
        return recList;
    }

    /**
     * a getter for the upper left point of the frame (where it starts).
     *
     * @return the value of the upper left corner of the frame.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }


    /**
     * a setter for the value of the width of the frame.
     *
     * @param width1 the width value to be inserted.
     */
    public void setWidth(double width1) {
        this.width = width1;
    }

    /**
     * a setter for the value of the height of the frame.
     *
     * @param height1 the height value to be inserted.
     */
    public void setHeight(double height1) {
        this.height = height1;
    }

    /**
     * a setter for the upper left point member.
     *
     * @param leftPoint the upper left point value to be inserted.
     */
    public void setUpperLeft(Point leftPoint) {
        this.upperLeft = leftPoint;
    }

    /**
     * a getter fof the width of the frame.
     *
     * @return the value of the width of the frame.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * a getter fof the height of the frame.
     *
     * @return the value of the height of the frame.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        //currently, empty.
    }
}