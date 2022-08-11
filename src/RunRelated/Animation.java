package RunRelated;

import biuoop.DrawSurface;

/**
 * The "Animation" interface.
 *
 * <p>Managing the drawing of frames for each DrawSurface and game level.
 * * @author Tal Mizrahi.
 * * @version 1.0
 * * @since 16/03/2022
 */
public interface Animation {
    /**
     * Drawing the frame of a level on each state of the game.
     *
     * @param d the surface we print on the frame of the game.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checking the boolean condition to stop the game if necessary.
     *
     * @return A boolean true if the game is still on, otherwise false.
     */
    boolean shouldStop();
}