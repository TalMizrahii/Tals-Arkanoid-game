//206960890 Tal Mizrahi

package SpriteRelated;

import Collidables.Ball;
import GameRelated.GameLevel;
import Listener.HitListener;

import java.awt.Color;

/**
 * the CrazyBlock class.
 *
 * <p></p>a listener who add a ball to the game if a specific ball is hit.
 *
 * @author Tal Mizrahi.
 * @version ass6.
 * @since 16/03/2022
 */
public class CrazyBlock implements HitListener {
    private GameLevel game;

    /**
     * a constructor for the class.
     *
     * @param game the game we remove the ball from.
     */
    public CrazyBlock(GameLevel game) {
        setGame(game);
    }

    /**
     * a getter for the game.
     *
     * @return the game instance.
     */
    public GameLevel getGame() {
        return this.game;
    }

    /**
     * a setter for the game.
     *
     * @param game the game instance.
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * a method who takes care of the hit and it's consequences of the hit.
     * specific to this listener, the method removes the block from the game and add a new ball instead.
     *
     * @param beingHit the object who has been hit.
     * @param hitter   the object who hits the object who has been hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Ball ball = new Ball(beingHit.getCollisionRectangle().getUpperLeft().getX() + 5,
                beingHit.getCollisionRectangle().getUpperLeft().getY() + 5, 4, Color.RED);
        getGame().extraBall(ball);
        beingHit.removeFromGame(getGame());
        beingHit.removeHitListener(this);
    }
}
