
package Collidables;

import GameRelated.GameLevel;
import Geomatry.Point;
import Geomatry.Rectangle;
import SpriteRelated.Sprite;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * the "collidables" class.
 *
 * <p>contain all methods related to the paddle, including the paddle's color, shape and movement.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard; //sensor for the key pressing.
    private Rectangle rectangle; //the paddles shape.
    private Color color; //the paddles color.
    private static final int LEFT_PADDLE_BORDER = 20;
    private static final int RIGHT_PADDLE_BORDER = 780;
    private static final int STEP = 10; // a step of the paddle.
    private ArrayList<Ball> balls; // an array of balls to check if any ball is inside the paddle.
    private static final double EPSILON = Math.pow(10, -10); //a THRESHOLD value.

    /**
     * @param rectangle the shape of the paddle.
     * @param gui       the gui of the game to be printed on animation.
     * @param color     the color of the paddle.
     */
    public Paddle(Rectangle rectangle, GUI gui, Color color) {
        setKeyboard(gui);
        setCollisionRactengle(rectangle);
        setColor(color);
    }

    /**
     * moving the paddle left according to its position.
     */
    public void moveLeft() {
        if (getCollisionRectangle().getUpperLeft().getX() > LEFT_PADDLE_BORDER) {
            //the new upper left point of the rectangle.
            Point nextUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX() - STEP,
                    getCollisionRectangle().getUpperLeft().getY());
            //the new rectangle position
            Rectangle newPosition = new Rectangle(nextUpperLeft,
                    getCollisionRectangle().getWidth(), getCollisionRectangle().getHeight());

            setCollisionRactengle(newPosition);
        }

    }

    /**
     * moving the paddle right according to its position.
     */
    public void moveRight() {
        if (getCollisionRectangle().getUpperLeft().getX() < RIGHT_PADDLE_BORDER - getCollisionRectangle().getWidth()) {
            //the new upper left point of the rectangle.
            Point nextUpperLeft = new Point(getCollisionRectangle().getUpperLeft().getX() + STEP,
                    getCollisionRectangle().getUpperLeft().getY());
            //the new rectangle position
            Rectangle newPosition = new Rectangle(nextUpperLeft,
                    getCollisionRectangle().getWidth(), getCollisionRectangle().getHeight());

            setCollisionRactengle(newPosition);
        }
    }

    /**
     * checking if a ball is inside the paddle.
     */
    public void ballsInside() {
        for (int i = 0; i < getBalls().size(); i++) {
            //check if the ball is inside the left part of the paddle.
            if (balls.get(i).getY() > getCollisionRectangle().getUpperLeft().getY()
                    && balls.get(i).getY() - EPSILON <= getCollisionRectangle().getLowerLeft().getY()
                    && balls.get(i).getX() + EPSILON >= getCollisionRectangle().getUpperLeft().getX()
                    && balls.get(i).getX() <= EPSILON + getCollisionRectangle().getUpperLeft().getX()
                    + (getCollisionRectangle().getWidth() / 2)
            ) {
                //set the ball outside the paddle.
                balls.get(i).setX(balls.get(i).getX() - 1.5 * STEP);
                balls.get(i).setVelocity(balls.get(i).getVelocity().getDx() * (-1),
                        balls.get(i).getVelocity().getDy());
                //check if the ball is inside the left part of the paddle.
            } else if (balls.get(i).getY() > getCollisionRectangle().getUpperLeft().getY()
                    && balls.get(i).getY() <= getCollisionRectangle().getLowerLeft().getY()
                    && balls.get(i).getX() <= getCollisionRectangle().getUpperRight().getX()
                    && balls.get(i).getX() >= getCollisionRectangle().getUpperRight().getX()
                    - (getCollisionRectangle().getWidth() / 2)) {
                //set the ball outside the paddle.
                balls.get(i).setX(balls.get(i).getX() + 1.5 * STEP);
                balls.get(i).setVelocity(balls.get(i).getVelocity().getDx() * (-1),
                        balls.get(i).getVelocity().getDy());
            }
        }
    }

    /**
     * each time we have to move the paddle while it is time to move.
     */
    @Override
    public void timePassed() {
        ballsInside();
        //move the paddle to the left.
        if (getKeyboard().isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        //move the puddle to the right.
        if (getKeyboard().isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawing the paddle on the screen.
     *
     * @param d the surface we print the paddle threw.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //printing the color of the paddle.
        d.setColor(getColor());
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        //setting the black frame of the paddle.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(), (int) getCollisionRectangle().getHeight());
    }

    /**
     * a getter for the rectangles shape.
     *
     * @return the rectangles shape.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * a setter for the rectangle.
     *
     * @param rectangle the new rectangles shape.
     */
    public void setCollisionRactengle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * a setter fot the color of the paddle.
     *
     * @param color the new color of the paddle.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * a getter for the paddle.
     *
     * @return the color of the paddle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * a getter for the sensor of the keyboard.
     *
     * @return the keyboard sensor.
     */
    public biuoop.KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * a setter for the keyboard sensor.
     *
     * @param gui the specific gui of the keyboard sensor.
     */
    public void setKeyboard(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * checking where on the paddle the ball is hitting and changing the angle of the ball accordingly.
     *
     * @param collisionPoint  the collision point on the paddle.
     * @param currentVelocity the velocity of the ball.
     * @return the new velocity of the ball.
     */
    public Velocity checkhitSpot(Point collisionPoint, Velocity currentVelocity) {
        double section = getCollisionRectangle().getWidth() / 5; //dividing the paddle to 5 sections.
        double startPoint = getCollisionRectangle().getUpperLeft().getX();
        double x = collisionPoint.getX();
        //checking where on the paddle the ball is hitting and changing the angle of the ball accordingly.
        if (x >= startPoint && x <= startPoint + section) { // section 1
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        } else if (x >= startPoint + section && x <= startPoint + (section * 2)) { // section 2
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        } else if (x >= startPoint + (section * 2) && x <= startPoint + (section * 3)) { // section 3
            currentVelocity.setDy(currentVelocity.getDy() * (-1));
            return currentVelocity;
        } else if (x >= startPoint + (section * 3) && x <= startPoint + (section * 4)) { // section 3
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        } else if (x >= startPoint + (section * 4) && x <= startPoint + (section * 5)) { // section 4
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        } else {
            currentVelocity.setDy(currentVelocity.getDy() * (-1)); // a default setter.
            return currentVelocity;
        }
    }

    /**
     * Notify the object that we collided with, it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  The collision point of the paddle with the ball.
     * @param currentVelocity The current velocity of the ball.
     * @return the new velocity of the ball.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the hit is on the sides.
        if (collisionPoint.getX() == getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        } else if (collisionPoint.getX() == getCollisionRectangle().getUpperRight().getX()) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        //if it is on the top
        if (Math.abs(collisionPoint.getY() - getCollisionRectangle().getUpperLeft().getY()) == 0) {
            currentVelocity = checkhitSpot(collisionPoint, currentVelocity);
        }
        return currentVelocity;
    }

    /**
     * a setter for the balls array.
     *
     * @param balls the balls array.
     */
    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    /**
     * adding new ball to the paddle.
     *
     * @param ball the ball to be added.
     */
    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    /**
     * a getter for the balls array.
     *
     * @return the array of balls.
     */
    public ArrayList<Ball> getBalls() {
        if (balls.isEmpty()) {
            balls = new ArrayList<>();
        }
        return balls;
    }

    /**
     * adding the paddle to the game.
     *
     * @param g the game object we are playing in.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
