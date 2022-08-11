package Collidables;
import Geomatry.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * creating a new velocity appearance.
     *
     * @param dx delta x.
     * @param dy delta y.
     */
    public Velocity(double dx, double dy) {
        setDx(dx);
        setDy(dy);
    }

    /**
     * setter for dx.
     *
     * @param x new dx value.
     */
    public void setDx(double x) {
        this.dx = x;
    }

    /**
     * setter for dy.
     *
     * @param y new dy value.
     */
    public void setDy(double y) {
        this.dy = y;
    }

    /**
     * getter for dx.
     *
     * @return the value of dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getter for dy.
     *
     * @return value of dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * creating a new velocity from angel and speed.
     *
     * @param angle the angle of the vector.
     * @param speed the speed of the vector.
     * @return new velocity instance.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle);
        double dy = speed * Math.cos(angle) * (-1);
        return new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y), and return a new point with position (x+dx, y+dy).
     *
     * @param p the x, y values.
     * @return new Geomatry.Point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + getDx(), p.getY() + getDy());
    }

    /**
     * converting the dx and dy to speed.
     *
     * @return the speed value.
     */
    public double getSpeed() {
        return Math.sqrt(Math.abs((getDx() * getDx()) + (getDy() * getDy())));
    }
}