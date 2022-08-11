package RunRelated;

import Collidables.Velocity;
import SpriteRelated.Block;
import SpriteRelated.SpriteCollection;

import java.util.List;

/**
 * the "LevelInformation" interface.
 *
 * <p>contain all methods related to the current level, and information about the balls, blocks, paddle, etc.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public interface LevelInformation {
    /**
     * returns the amount of balls in the current level.
     *
     * @return an integer of the amount of balls.
     */
    int numberOfBalls();

    /**
     * the initial number of balls. generally, initialBallVelocities().size() == numberOfBalls().
     *
     * @return array of the velocity of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the speed of the paddle.
     *
     * @return an integer of the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * returns the width of the paddle.
     *
     * @return an integer of the width of the paddle.
     */
    int paddleWidth();

    /**
     * returns the level's name.
     *
     * @return a string represent the name of the level.
     */
    String levelName();

    /**
     * returns the background of the level.
     *
     * @return the Sprite of the background.
     */
    SpriteCollection getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the array of all blocks created for the level.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return generally, This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
}