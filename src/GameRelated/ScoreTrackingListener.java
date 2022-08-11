package GameRelated;

import Collidables.Ball;
import Listener.HitListener;
import SpriteRelated.Block;

/**
 * PrintingHitListener print "a block was hit" to the terminal.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore; //the counter of the score.

    /**
     * the constructor of the class.
     *
     * @param scoreCounter the score's counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        setCurrentScore(scoreCounter);
    }

    /**
     * a getter for the score's counter.
     *
     * @return the scores counter.
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }

    /**
     * a setter for the score's counter.
     *
     * @param currentScore the score's counter.
     */
    public void setCurrentScore(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * a method who takes care of the hit and it's consequences of the hit.
     * specific to this listener, the method increase the counter by 5.
     *
     * @param beingHit the object who has been hit.
     * @param hitter   the object who hits the object who has been hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        getCurrentScore().increase(5);
    }
}
