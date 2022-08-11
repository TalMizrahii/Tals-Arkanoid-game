//206960890 Tal Mizrahi

package SpriteRelated;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * the SpriteCollection class.
 *
 * <p>contain all methods related to Sprits, including adding and drawing.
 *
 * @author Tal Mizrahi.
 * @version ass6.
 * @since 16/03/2022
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites; // the sprites array.

    /**
     * adding new sprite to the array.
     *
     * @param s the new sprite value.
     */
    public void addSprite(Sprite s) {
        //checking if the array is null.
        if (this.sprites == null) {
            this.sprites = new ArrayList<>();
        }
        this.sprites.add(s);
    }

    /**
     * a getter for the sprites array.
     *
     * @return the sprites array.
     */
    public ArrayList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * call the "timePassed" method for each sprite.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritArray = this.sprites;
        for (int i = 0; i < spritArray.size(); i++) {
            spritArray.get(i).timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the surface we are drawing with.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < getSprites().size(); i++) {
            getSprites().get(i).drawOn(d);
        }
    }
}