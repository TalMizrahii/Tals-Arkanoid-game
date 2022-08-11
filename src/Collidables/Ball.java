
package Collidables;

import GameRelated.GameLevel;
import GameRelated.GameEnvironment;
import Geomatry.Line;
import Geomatry.Point;
import Geomatry.Rectangle;
import SpriteRelated.Sprite;
import biuoop.DrawSurface;

/**
 * the "Ball" class.
 *
 * <p>contain all methods related to the ball, including the balls movement and position calculations.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Ball implements Sprite {
    private Velocity velocity; //the velocity of the ball.
    private double x; //the x coordinate of the center of the ball.
    private double y; //the y coordinate of the center of the ball.
    private int size; //the radius of the ball.
    private java.awt.Color color; // the color of the ball.
    private GameEnvironment gameEnvironment; //the environment that tha ball will play in.
    private static final int CORRECTION = 1;
    private static final int LEFT_UPPER_BORDER = 20;
    private static final int RIGHT_BORDER = 780;
    private static final double EPSILON = Math.pow(10, -10); //a THRESHOLD value.


    /**
     * creating new ball from center, radius and color.
     *
     * @param center the x and y coordinates.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        setX(center.getX());
        setY(center.getY());
        setSize(r);
        setColor(color);
        setVelocity(2, 2);
        setGameEnvironment(new GameEnvironment());
    }

    /**
     * creating new ball from x and y coordinates, radius and color.
     *
     * @param x     the x coordinate.
     * @param y     the y coordinate.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        setX(x);
        setY(y);
        setSize(r);
        setColor(color);
        setVelocity(2, 2);
        setGameEnvironment(new GameEnvironment());
    }

    /**
     * a setter for the "gameEnvironment" member.
     *
     * @param gameEnvironment the new game environment value
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * a getter for the "gameEnvironment" member.
     *
     * @return a GameRlated.GameEnvironment value.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * getter for x.
     *
     * @return returns x value.
     */
    public int getX() {
        return (int) this.x;
    }

    /**
     * getter for y.
     *
     * @return returns y value.
     */
    public int getY() {
        return (int) this.y;
    }

    /**
     * getter for size.
     *
     * @return returns size value.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * getter for color.
     *
     * @return returns color value.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * setter for x. makes sur that the x coordinate is inside the game.
     *
     * @param x1 x value.
     */
    public void setX(double x1) {
        //if the x value is not inside the borders, put it inside.
        if (x1 + EPSILON >= RIGHT_BORDER) {
            x1 = RIGHT_BORDER - (2 * CORRECTION);
        } else if (x1 - EPSILON <= LEFT_UPPER_BORDER) {
            x1 = LEFT_UPPER_BORDER + (2 * CORRECTION);
        }
        this.x = x1;
    }

    /**
     * setter for y.
     *
     * @param y1 y value.
     */
    public void setY(double y1) {
        //if the y value is not inside the borders, put it inside.
        if (y1 - EPSILON <= LEFT_UPPER_BORDER) {
            y1 = LEFT_UPPER_BORDER + (2 * CORRECTION);
        }
        this.y = y1;
    }

    /**
     * setter for size.
     *
     * @param size1 the radius.
     */
    public void setSize(int size1) {
        //the size cannot be negative.
        if (size1 <= 0) {
            this.size = 10;
            return;
        }
        this.size = size1;
    }

    /**
     * setter for color.
     *
     * @param color1 sets the color.
     */
    public void setColor(java.awt.Color color1) {
        this.color = color1;
    }

    /**
     * drawing the surface and balls.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(getColor()); //setting the color of the ball.
        d.fillCircle(getX(), getY(), getSize()); //drawing it.
    }

    /**
     * calling the method that moves the ball.
     */
    @Override
    public void timePassed() {
        //each time we have to move the ball while it is time to move.
        moveOneStep();
    }

    /**
     * setter for the velocity member.
     *
     * @param v the velocity value.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setter for the velocity member.
     *
     * @param dx the dx value in velocity.
     * @param dy the dy value in velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * getter for velocity.
     *
     * @return the velocity value.
     */
    public Velocity getVelocity() {
        //if no velocity was defined.
        if (this.velocity == null) {
            return new Velocity(0, 0);
        }
        return this.velocity;
    }

    /**
     * moving the ball one step. making sur that the ball is not inside a block.
     */
    public void moveOneStep() {
        //creating a trajectory that calculates the next position of the ball.
        Line traj = new Line(getX(), getY(), getVelocity().getDx() + getX(), getVelocity().getDy() + getY());
        //creating a collision information variable to check if any collision is about to occur.
        CollisionInfo collisionInfo = getGameEnvironment().getClosestCollision(traj);
        //if collision is obout to happen, check if the ball is not inside any block.
        if (collisionInfo != null) {
            if (!collisionInfo.collisionObject().getClass().getName().equals("collidables.Paddle")) {
                insideBlock(collisionInfo);
            }
            //changing the velocity according to the collision.
            collision();
        }

        //  moving the point.
        Point point = new Point(getX(), getY());
        point = this.getVelocity().applyToPoint(point);
        setX(point.getX());
        setY(point.getY());
    }

    /**
     * check if the ball is inside a block.
     *
     * @param collisionInfo the collision information about the collided object and the collision point.
     */
    public void insideBlock(CollisionInfo collisionInfo) {
        //create new rectangle and check if it is in the collided block.
        Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
        if (rectangle.getUpperLeft().getX() == collisionInfo.collisionPoint().getX()) {
            //if it is about to hit the left side, change the velocity accordingly.
            setX(collisionInfo.collisionPoint().getX() - CORRECTION);
        } else if (collisionInfo.collisionPoint().getX() == rectangle.getUpperRight().getX()) {
            //if it is about to hit the left side, change the velocity accordingly.
            setX(collisionInfo.collisionPoint().getX() + CORRECTION);
        }
        if (collisionInfo.collisionPoint().getY() == rectangle.getUpperLeft().getY()) {
            //if it is about to hit the upper side, change the velocity accordingly.
            setY(collisionInfo.collisionPoint().getY() - CORRECTION);

        } else if (collisionInfo.collisionPoint().getY() == rectangle.getLowerLeft().getY()) {
            //if it is about to hit the lower side, change the velocity accordingly.
            setY(collisionInfo.collisionPoint().getY() + CORRECTION);
        }
    }

    /**
     * checking and setting the balls movement and velocity after the collision.
     */
    public void collision() {
        //creat new trajectory and check if it is going to collide an object.
        Line traj = new Line(getX(), getY(), getVelocity().getDx() + getX(), getVelocity().getDy() + getY());
        CollisionInfo collisionInfo = getGameEnvironment().getClosestCollision(traj);
        //if no collision is about to occur, return.
        if (collisionInfo == null) {
            return;
        }
        //if a collision is about to occur, call "hit" method and change the velocity of the ball accordingly.
        Velocity v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), getVelocity());
        setVelocity(v);
    }

    /**
     * add the ball to the game, by adding it to the Sprites array in the GameRlated.Game class.
     *
     * @param g the designated game instance.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the ball to the game, by removing it to the Sprites array in the GameRlated.Game class.
     *
     * @param g the designated game instance.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}