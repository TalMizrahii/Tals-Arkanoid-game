package RunRelated;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The "PauseScreen" class.
 *
 * <p>responsible to pause the screen if the user wants to.
 * * @author Tal Mizrahi.
 * * @version 1.0
 * * @since 16/03/2022
 */
public class PauseScreen implements Animation {
    private boolean stop; // a boolean value to indicate if the user wishes to pause

    /**
     * a constructor for the class.
     */
    public PauseScreen() {
        setStop(false);
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
     * Drawing the pause frame.
     *
     * @param d the surface we print on the frame of the game.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(new Color(29, 233, 162));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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