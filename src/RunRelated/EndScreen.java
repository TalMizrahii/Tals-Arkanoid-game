package RunRelated;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The "EndScreen" class.
 *
 * <p>responsible to ens the screen if the user loses or wins.
 * * @author Tal Mizrahi.
 * * @version 1.0
 * * @since 16/03/2022
 */
public class EndScreen implements Animation {
    private boolean stop; // a boolean value to indicate if the user wishes to leave.
    private int score;
    private String endPhrase;

    /**
     * a constructor for this class.
     *
     * @param score     the score counter's value.
     * @param endPhrase the end phrase (win or lose).
     */
    public EndScreen(int score, String endPhrase) {
        setEndPhrase(endPhrase);
        setScore(score);
        setStop(false);
    }

    /**
     * a getter for the end phrase.
     *
     * @return the right end phrase.
     */
    public String getEndPhrase() {
        return endPhrase;
    }

    /**
     * a setter for the end phrase.
     *
     * @param endPhrase the right end phrase.
     */
    public void setEndPhrase(String endPhrase) {
        this.endPhrase = endPhrase;
    }

    /**
     * a setter for the score.
     *
     * @param score the score's value.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * a getter for the score.
     *
     * @return the score's value.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * a getter for the boolean stop.
     *
     * @return a boolean value. true means pause, false means continue.
     */
    public boolean getStop() {
        return this.stop;
    }

    /**
     * a setter for the boolean stop.
     *
     * @param stop a boolean value. true means pause, false means continue.
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Drawing the frame of a level on each state of the game.
     *
     * @param d the surface we print on the frame of the game.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (getEndPhrase().equals("win")) {
            d.setColor(new Color(206, 51, 58));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.BLACK);
            d.drawText(10, d.getHeight() / 2, "YOU WIN!!! Your score is " + getScore() + " points.", 32);
        } else {
            d.setColor(new Color(95, 108, 185));
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.BLACK);
            d.drawText(10, d.getHeight() / 2, "GAME OVER. Your score is " + getScore() + " points.", 32);
        }
        setStop(true);
    }

    /**
     * Checking the boolean condition to stop the game if necessary.
     *
     * @return A boolean true if the game is still on, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return getStop();
    }
}
