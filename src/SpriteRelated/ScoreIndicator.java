// 206960890 Tal Mizrahi

package SpriteRelated;

import GameRelated.Counter;
import GameRelated.GameLevel;
import biuoop.DrawSurface;

/**
 * a ScoreIndicator is in charge of keeping the score of the game.
 *
 * @author Tal Mizrahi.
 * @version ass6.
 * @since 16/03/2022
 */
public class ScoreIndicator implements Sprite {
    private Counter counter; //the counter of the score.

    /**
     * a constructor for the class.
     *
     * @param counter the counter of the score.
     */
    public ScoreIndicator(Counter counter) {
        setCounter(counter);
    }

    /**
     * a getter for the counter.
     *
     * @return the counter value of the score.
     */
    public Counter getCounter() {
        return this.counter;
    }

    /**
     * a setter for the counter.
     *
     * @param counter the counter value of the score.
     */
    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        String score = "SCORE: " + getCounter().getValue();
        d.drawText(350, 15, score, 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        //currently empty
    }

    /**
     * adding the score to the game.
     *
     * @param g the game class we add the score to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
