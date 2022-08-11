
package Collidables;

import Geomatry.Point;
import Geomatry.Rectangle;

/**
 * the "Collidable" interface.
 *
 * <p>an interface of the collidable objects. contains the "hit" method, which responsible to handle a hit of the ball.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the rectangle object to be collided.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          the ball who hits the object.
     * @param collisionPoint  The collision point of the block with the ball.
     * @param currentVelocity The current velocity of the ball.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}