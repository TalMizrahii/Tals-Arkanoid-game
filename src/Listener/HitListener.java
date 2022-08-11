package Listener;

import Collidables.Ball;
import SpriteRelated.Block;

/**
 * the HitListener interface.
 *
 * <p></p>contain all methods related to the rectangle, including the rectangle's color, shape and position.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public interface HitListener {

    /**
     * a method who takes care of the hit and it's consequences of the hit.
     *
     * @param beingHit the object who has been hit.
     * @param hitter   the object who hits the object who has been hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}