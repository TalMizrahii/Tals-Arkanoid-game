//206960890 Tal Mizrahi

package SpriteRelated;

import Collidables.Ball;
import Collidables.Collidable;
import Collidables.Velocity;
import GameRelated.GameLevel;
import Geomatry.Point;
import Geomatry.Rectangle;
import Listener.HitListener;
import Listener.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the "Block" class.
 *
 * <p>contain all methods related to the Block, including the Block's color and shape.
 *
 * @author Tal Mizrahi.
 * @version ass6.
 * @since 16/03/2022
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * a SpriteRelated.Block constructor, who use a color and rectangle to create a SpriteRelated.Block.
     *
     * @param color the SpriteRelated.Block's color.
     * @param shape the SpriteRelated.Block's shape.
     */
    public Block(Color color, Rectangle shape) {
        setColor(color);
        setShape(shape);
    }

    /**
     * a getter for the Color member.
     *
     * @return the color value of the block.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * a setter for the Color member.
     *
     * @param color the new color value of the block.
     */
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * a Block constructor, who use only a rectangle to create a SpriteRelated.Block, with default color of gray.
     *
     * @param shape the SpriteRelated.Block's shape.
     */
    public Block(Rectangle shape) {
        setColor(Color.GRAY);
        setShape(shape);
    }

    /**
     * a setter for the shape member.
     *
     * @param shape1 the shape value of the block.
     */
    public void setShape(Rectangle shape1) {
        this.shape = shape1;
        getCollisionRectangle().setRecArr();
    }

    /**
     * drawing the surface and balls.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //drawing the rectangle on the screen
        d.setColor(getColor());
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        //drawing the black frame of the rectangle.
        d.setColor(Color.BLACK);
        d.drawRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(),
                (int) getCollisionRectangle().getWidth(), (int) getCollisionRectangle().getHeight());
    }

    @Override
    //currently empty.
    public void timePassed() {
    }

    /**
     * a getter for the rectangle's shape.
     *
     * @return the shape of the rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Notify the object that we collided with it, at collisionPoint with a given velocity.
     *
     * @param collisionPoint  The collision point of the block with the ball.
     * @param currentVelocity The current velocity of the ball.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //if the ball is about to hit the block from the sides.
        if ((collisionPoint.getX() == getCollisionRectangle().getUpperRight().getX())
                || (collisionPoint.getX()) == getCollisionRectangle().getUpperLeft().getX()) {
            currentVelocity.setDx(currentVelocity.getDx() * (-1));
        }
        //if the ball is about to hit the block from above or below.
        if ((collisionPoint.getY() == getCollisionRectangle().getUpperLeft().getY())
                || (collisionPoint.getY() == getCollisionRectangle().getLowerLeft().getY())) {
            currentVelocity.setDy(currentVelocity.getDy() * (-1));
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * add the block to the game, by adding it to the Sprites array and Collidables array in the GameRlated.Game class.
     *
     * @param g the designated game instance.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removing the block from the game.
     *
     * @param game the game we remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * a getter for the listeners array.
     *
     * @return the array of listeners.
     */
    public List<HitListener> getHitListeners() {
        if (this.hitListeners == null) {
            this.hitListeners = new ArrayList<>();
        }
        return this.hitListeners;
    }

    /**
     * adding a listeners.
     *
     * @param hl the listener we add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        getHitListeners().add(hl);
    }

    /**
     * removing the hit listener from the listeners array.
     *
     * @param hl the listener we remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        getHitListeners().remove(hl);
    }

    /**
     * notifying all the listeners of the block that the block was hit.
     *
     * @param hitter the ball who hits the block.
     */
    private void notifyHit(Ball hitter) {
        //check if there are any listeners exist.
        if (!getHitListeners().isEmpty()) {
            // Make a copy of the hitListeners before iterating over them.
            List<HitListener> listeners = new ArrayList<>(this.hitListeners);
            // Notify all listeners about a hit event:
            for (HitListener hl : listeners) {
                hl.hitEvent(this, hitter);
            }
        }
    }
}
