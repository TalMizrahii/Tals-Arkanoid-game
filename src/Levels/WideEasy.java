package Levels;

import Collidables.Velocity;
import Geomatry.Circle;
import Geomatry.Line;
import Geomatry.Point;
import Geomatry.Rectangle;
import RunRelated.LevelInformation;
import SpriteRelated.Block;
import SpriteRelated.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "WideEasy" level class.
 *
 * <p>responsible to run the game with specific background, block and balls for this level.
 * * @author Tal Mizrahi.
 * @version 1.0
 * * @since 16/03/2022
 */
public class WideEasy implements LevelInformation {
    private static final String LEVEL_NAME = "Wide Easy";
    private SpriteCollection background;
    private List<Block> blocks;
    private static final int NUM_BALLS = 10;
    private static final int PADDLE_WIDTH = 600;
    private static final int PADDLE_STEP = 10;
    private static final int NUM_OF_BLOCKS = 15;

    private static final double BLOCK_HEIGHT = 23;
    private static final int FLOOR = 250;
    private static final int CIRCLE_START_RADIUS = 60;
    private static final int NUM_OF_CIRCLES = 3;
    private static final Point SUN_CENTER = new Point(90, 120);
    private static final double BLOCK_WIDTH = (float) 760 / NUM_OF_BLOCKS;

    /**
     * a constructor for the level's class.
     */
    public WideEasy() {
        creatBackground();
        createBlocks();
    }

    /**
     * a getter for the blocks array.
     *
     * @return the list of the blocks of the level.
     */
    public List<Block> getBlocks() {
        return this.blocks;
    }

    /**
     * a setter for the blocks array.
     *
     * @param blocks the array of blocks.
     */
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * a setter for the background.
     *
     * @param background the background as a spriteCollection.
     */
    public void setBackground(SpriteCollection background) {
        this.background = background;
    }

    /**
     * creating the background.
     */
    public void creatBackground() {
        SpriteCollection sprite = new SpriteCollection();
        createCircles(sprite); //creating the circles of the target.
        createLines(sprite); //creating the Lines of the target.
        setBackground(sprite);
    }

    /**
     * creating the targets lines and adding them to the game.
     *
     * @param sprite the array we add the lines to.
     */
    public void createLines(SpriteCollection sprite) {
        double xPoint = 20;
        for (int i = 0; i < 150; i++) {
            Line line = new Line(SUN_CENTER, new Point(xPoint, FLOOR));
            line.setColor(specificColor(255, 200, 0));
            sprite.addSprite(line);
            xPoint += 7.6;
        }
    }

    /**
     * creating the circles of the sun.
     *
     * @param sprite the sprite we add the circles to.
     */
    public void createCircles(SpriteCollection sprite) {
        int radius = CIRCLE_START_RADIUS; // we change the value, so we put the value in a new variable.
        //we want yellow colors, so we make them.
        int red = 255;
        int green = 204;
        int blue = 150;
        for (int i = 0; i < NUM_OF_CIRCLES; i++) {
            Circle circle = new Circle(SUN_CENTER, specificColor(red, green, blue), radius);
            circle.setFill(specificColor(red, green, blue));
            sprite.addSprite(circle);
            radius -= 10; // reduce the radius
            blue -= 30; // reduce the blue to get darker yellow.
        }
    }

    /**
     * generate a specific color.
     *
     * @param red   the red value.
     * @param green the green value.
     * @param blue  the blue value.
     * @return a new Color.
     */
    public Color specificColor(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    /**
     * returns the amount of balls in the current level.
     *
     * @return an integer of the amount of balls.
     */
    @Override
    public int numberOfBalls() {
        return NUM_BALLS;
    }

    /**
     * the initial number of balls. generally, initialBallVelocities().size() == numberOfBalls().
     *
     * @return array of the velocity of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        //create the velocities and return an array of them.
        for (int i = 0; i < NUM_BALLS; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed((i * 10) - 45, numberOfBalls() - 3);
            velocities.add(velocity);
        }
        return velocities;
    }

    /**
     * returns the speed of the paddle.
     *
     * @return an integer of the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return PADDLE_STEP;
    }

    /**
     * returns the width of the paddle.
     *
     * @return an integer of the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * returns the level's name.
     *
     * @return a string represent the name of the level.
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * returns the background of the level.
     *
     * @return the Sprite of the background.
     */
    @Override
    public SpriteCollection getBackground() {
        return this.background;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the array of all blocks created for the level.
     */
    @Override
    public List<Block> blocks() {
        return getBlocks();
    }

    /**
     * creating the blocks for this level.
     */
    public void createBlocks() {
        List<Block> blocks = new ArrayList<>();
        //colors array for each line.
        Color[] colors = {Color.RED, Color.orange, Color.YELLOW,
                Color.GREEN, Color.BLUE, Color.PINK, Color.blue.brighter(), Color.RED.brighter()};
        double xPos = 20;
        int j = 0;
        // create new block.
        for (int i = 0; i < 8; i++) {
            Block block;
            if (i != 7) {
                block = new Block(new Rectangle(new Point(xPos, FLOOR), BLOCK_WIDTH, BLOCK_HEIGHT));
            } else {
                block = new Block(new Rectangle(new Point(xPos, FLOOR), BLOCK_WIDTH + 10, BLOCK_HEIGHT));
            }
            block.setColor(colors[j % 8]); // set its color.
            blocks.add(block);
            xPos += BLOCK_WIDTH; // set the point to the next step.
            //set the colors array to the next color every two blocks
            if (i % 2 != 0) {
                j++;
            }
        }
        xPos = 377 + BLOCK_WIDTH;
        for (int i = 0; i < 7; i++) {
            Block block = new Block(new Rectangle(new Point(xPos, FLOOR), BLOCK_WIDTH, BLOCK_HEIGHT));
            block.setColor(colors[j % 8]); // set its color.
            blocks.add(block);
            xPos += BLOCK_WIDTH; // set the point to the next step.
            //set the colors array to the next color every two blocks
            if (i % 2 != 0) {
                j++;
            }
        }
        setBlocks(blocks);
    }

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return generally, This number should be <= blocks.size();
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
