package Geomatry;

import SpriteRelated.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the "Circle" class.
 *
 * <p>contains the center and colors of the circle.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Circle implements Sprite {
    private Point center; // the center of the circle.
    private Color frame; // the frame of the circle.
    private Color fill; //the filled color of the circle.
    private int radius;

    /**
     * a constructor with the filled color of the circle.
     *
     * @param center the center of the circle.
     * @param frame  the frame color of the circle.
     * @param fill   the filled color of the circle.
     * @param radius the radius of the circle.
     */
    public Circle(Point center, Color frame, Color fill, int radius) {
        setCenter(center);
        setFrame(frame);
        setFill(fill);
        setRadius(radius);
    }

    /**
     * a constructor without the filled color of the circle.
     *
     * @param center the center of the circle.
     * @param frame  the frame color of the circle.
     * @param radius the radius of the circle.
     */
    public Circle(Point center, Color frame, int radius) {
        setCenter(center);
        setFrame(frame);
        setRadius(radius);
    }

    /**
     * a circle constructor.
     *
     * @param fill   the filled color of the circle.
     * @param center the center point of the circle.
     * @param radius the radius of the circle.
     */
    public Circle(Color fill, Point center, int radius) {
        setCenter(center);
        setFill(fill);
        setRadius(radius);
    }


    /**
     * a getter for the radius of the circle.
     *
     * @return the radius of the circle.
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * a setter for the radius of the circle.
     *
     * @param radius the radius of the circle.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * a getter for the filled color.
     *
     * @return the filled color.
     */
    public Color getFill() {
        return this.fill;
    }

    /**
     * a setter for the filled color.
     *
     * @param fill the filled color.
     */
    public void setFill(Color fill) {
        this.fill = fill;
    }

    /**
     * a getter for the frame color.
     *
     * @return the frame color.
     */
    public Color getFrame() {
        if (this.frame == null && this.fill == null) {
            return Color.BLUE;
        } else if (this.frame == null) {
            return this.fill;
        }
        return this.frame;
    }

    /**
     * a setter for the frame color.
     *
     * @param frame the frame color.
     */
    public void setFrame(Color frame) {
        this.frame = frame;
    }

    /**
     * a getter for the center of the circle.
     *
     * @return the center point of the circle.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * a setter for the center of the Circle.
     *
     * @param center the center point of the circle.
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getFrame());
        d.drawCircle((int) getCenter().getX(), (int) getCenter().getY(), getRadius());
        if (getFill() != null) {
            d.setColor(getFill());
            d.fillCircle((int) getCenter().getX(), (int) getCenter().getY(), getRadius());
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        //currently empty
    }
}
