//206960890 Tal Mizrahi

package SpriteRelated;

import GameRelated.GameLevel;
import biuoop.DrawSurface;

/**
 * in charge of presenting the level's name.
 *
 * @author Tal Mizrahi.
 * @version ass6.
 * @since 16/03/2022
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * a constructor for the class.
     *
     * @param name the name of the current level.
     */
    public LevelName(String name) {
        setName(name);
    }

    /**
     * a getter for the name member.
     *
     * @return a string who represent the name of the level.
     */
    public String getName() {
        return this.name;
    }

    /**
     * a setter for the name member.
     *
     * @param name a string who represent the name of the level.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        String score = "Level Name: " + getName();
        d.drawText(500, 15, score, 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        // currently empty
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
