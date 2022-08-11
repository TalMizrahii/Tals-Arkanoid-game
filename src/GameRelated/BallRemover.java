package GameRelated;

import Collidables.Ball;
import Listener.HitListener;
import SpriteRelated.Block;

/**
 * BallRemover responsible to remove a ball from the game.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class BallRemover implements HitListener {
    private Counter ballsCounter; //counter to track how many ball are in the game.
    private GameLevel game; //the game we delete the balls from.

    /**
     * a constructor for the class.
     *
     * @param counter counter to track how many balls are in the game.
     * @param game    the game we delete the ball from.
     */
    public BallRemover(Counter counter, GameLevel game) {
        setGame(game);
        setBallsCounter(counter);
    }

    /**
     * a getter for the game member.
     *
     * @return the game member.
     */
    public GameLevel getGame() {
        return this.game;
    }

    /**
     * a setter for the game member.
     *
     * @param game the game member.
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * a getter for the counter of the remaining balls.
     *
     * @return the counter class of the remaining balls.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * a setter for the counter of the remaining balls.
     *
     * @param ballsCounter the counter class of the remaining balls.
     */
    public void setBallsCounter(Counter ballsCounter) {
        this.ballsCounter = ballsCounter;
    }

    /**
     * a method who takes care of the hit and it's consequences of the hit.
     * specific to this listener, the method removes the ball from the game.
     *
     * @param beingHit the object who has been hit.
     * @param hitter   the object who hits the object who has been hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(getGame()); // removing the ball.
        getBallsCounter().decrease(1); //decreasing the number of balls int the counter by 1.
    }
}
