package Levels;

import Collidables.Velocity;
import Geomatry.Circle;
import Geomatry.Point;
import Geomatry.Rectangle;
import RunRelated.LevelInformation;
import SpriteRelated.Block;
import SpriteRelated.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "Green 3" level class.
 *
 * <p>responsible to run the game with specific background, block and balls for this level.
 * * @author Tal Mizrahi.
 *  * @version 1.0
 * * @since 16/03/2022
 */
public class Green3 implements LevelInformation {
    private static final String LEVEL_NAME = "Green 3";
    private SpriteCollection background;
    private List<Block> blocks;
    private static final int NUM_BALLS = 2;
    private static final int PADDLE_WIDTH = 80;
    private static final int PADDLE_STEP = 10;
    private static final int NUM_OF_BLOCKS = 50;

    private static final double BLOCK_HEIGHT = 20;
    private static final double BLOCK_WIDTH = 50;
    private static final int FLOOR = 150;


    /**
     * a constructor for the level's class.
     */
    public Green3() {
        creatBackground();
        createBlocks();
    }

    /**
     * creating the blocks for this level.
     */
    public void createBlocks() {
        int count = 12; // the number of lines of blocks.
        List<Block> blocks = new ArrayList<>();
        //colors array for each line.
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.PINK, Color.GREEN};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < count; j++) {
                //creating a block.
                Block block = new Block(colors[i],
                        new Geomatry.Rectangle(new Point((780 - BLOCK_WIDTH) - (BLOCK_WIDTH * j),
                                FLOOR + BLOCK_HEIGHT * i), BLOCK_WIDTH, BLOCK_HEIGHT));
                blocks.add(block);
            }
            count--;
        }
        setBlocks(blocks);
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
        creatBack(sprite);
        creatBiulding(sprite);
        setBackground(sprite);
    }

    /**
     * creating the building.
     *
     * @param sprite the sprite we add the circles to.
     */
    public void creatBiulding(SpriteCollection sprite) {
        Block block = new Block(Color.BLACK.brighter(), new Rectangle(new Point(50, 350), 110, 400));
        sprite.addSprite(block);
        int winx = 60;
        int winy = 370;
        int winwidth = 10;
        int winheight = 20;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                Block window = new Block(Color.WHITE, new Rectangle(new Point(winx, winy), winwidth, winheight));
                sprite.addSprite(window);
                winx += winwidth + 10;
            }
            winx = 60;
            winy += winheight + 10;
        }

        Block block1 = new Block(Color.DARK_GRAY, new Rectangle(new Point(85, 270), 40, 80));
        Block block2 = new Block(Color.DARK_GRAY, new Rectangle(new Point(100, 150), 10, 120));
        sprite.addSprite(block1);
        sprite.addSprite(block2);

        Color[] colors = {Color.RED, Color.orange, Color.YELLOW};
        for (int i = 0; i < 10; i++) {
            Circle circle = new Circle(new Point(105, 150), colors[i % 3], 12 - (i * 2));
            sprite.addSprite(circle);
        }


    }

    /**
     * creating the back color of the screen.
     *
     * @param sprite the sprite we add the circles to.
     */
    public void creatBack(SpriteCollection sprite) {
        //creating the back.
        Block back = new Block(specificColor(0, 120, 0),
                new Rectangle(new Point(0, 0), 800, 600));
        sprite.addSprite(back);
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
            Velocity velocity = Velocity.fromAngleAndSpeed(i * -10, 4);
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
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return generally, This number should be <= blocks.size();
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
