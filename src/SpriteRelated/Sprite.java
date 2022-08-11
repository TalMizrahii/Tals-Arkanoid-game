//206960890 Tal Mizrahi

package SpriteRelated;

import biuoop.DrawSurface;

/**
 * the "Sprites" interface.
 *
 * <p>contain all methods related to the SpriteRelated.Block, including the SpriteRelated.Block's color and shape.
 *
 * @author Tal Mizrahi.
 * @version ass6.
 * @since 16/03/2022
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}