
package Collidables;

import Geomatry.Point;

/**
 * the "collidables.CollisionInfo" class.
 *
 * <p>contains the information related to an impact of a collidable object with a ball.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class CollisionInfo {
    private Point collidePoint; //the point of the upcoming collision.
    private Collidable collidable; // The object that the ball is about to collide with.

    /**
     * a constructor for the collidables.CollisionInfo object.
     *
     * @param collidePoint the collision point of the ball with an object.
     * @param collidable   the collided object.
     */
    public CollisionInfo(Point collidePoint, Collidable collidable) {
        setCollidable(collidable);
        setCollidePoint(collidePoint);
    }

    /**
     * a setter for the collidable member.
     *
     * @param collidable the new collidable value.
     */
    public void setCollidable(Collidable collidable) {
        this.collidable = collidable;
    }

    /**
     * a setter for the collision point member.
     *
     * @param collidePoint the new collision point value.
     */
    public void setCollidePoint(Point collidePoint) {
        this.collidePoint = collidePoint;
    }

    /**
     * a getter for the collision point value.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collidePoint;
    }

    /**
     * a getter for the collidable object value.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}