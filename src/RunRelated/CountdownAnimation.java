package RunRelated;


import SpriteRelated.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The "CountdownAnimation" class.
 *
 * <p>creating and printing a countdown before each level.
 * * @author Tal Mizrahi.
 * * @version 1.0
 * * @since 16/03/2022
 */
public class CountdownAnimation implements Animation {
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private static final int ONE = 1;

    /**
     * a constructor for the class.
     *
     * @param countFrom  the number who starts the count.
     * @param gameScreen the sprites array, meaning the screen of the game.
     */
    public CountdownAnimation(int countFrom, SpriteCollection gameScreen) {
        setCountFrom(countFrom);
        setGameScreen(gameScreen);
        setStop(false);
    }

    /**
     * a getter for the boolean stop value.
     *
     * @return a boolean value.
     */
    public boolean getStop() {
        return this.stop;
    }

    /**
     * a setter for the boolean stop value.
     *
     * @param stop the stop value.
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * a getter for the countdown.
     *
     * @return the current count.
     */
    public int getCountFrom() {
        return this.countFrom;
    }

    /**
     * a setter for the countdown.
     *
     * @param countFrom the new count.
     */
    public void setCountFrom(int countFrom) {
        this.countFrom = countFrom;
    }

    /**
     * a getter for the sprites array.
     *
     * @return the sprites array.
     */
    public SpriteCollection getGameScreen() {
        return this.gameScreen;
    }

    /**
     * a setter for the sprites array.
     *
     * @param gameScreen the new sprites array.
     */
    public void setGameScreen(SpriteCollection gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * printing the current count on the screen.
     *
     * @param d the surface we print on.
     */
    public void countdown(DrawSurface d) {
        String time;
        d.setColor(Color.RED);
        // if the count is on, present the count.
        if (countFrom != 0) {
            time = Integer.toString(getCountFrom());
            d.drawText(365, 300, time, 50);
            setCountFrom(getCountFrom() - ONE);
            // if its over, GO!
        } else {
            time = "GO!!!";
            d.drawText(335, 300, time, 50);
            setCountFrom(getCountFrom() - ONE);
        }
    }


    /**
     * Drawing the frame of a level on each state of the game.
     *
     * @param d the surface we print on the frame of the game.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        getGameScreen().drawAllOn(d);
        countdown(d);
        if (getCountFrom() < 0) {
            setStop(true);
        }
    }

    /**
     * asking if we should stop the current run.
     *
     * @return the boolean result of the question.
     */
    @Override
    public boolean shouldStop() {
        return getStop();
    }
}