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
 * The "DirectHit" level class.
 *
 * <p>responsible to run the game with specific background, block and balls for this level.
 * * @author Tal Mizrahi.
 * * @version 1.0
 * * @since 16/03/2022
 */
public class DirectHit implements LevelInformation {
    private static final int NUM_OF_BALLS = 1;
    private static final int NUM_OF_BLOCKS = 1;
    private static final int PADDLE_STEP = 10;
    private static final int PADDLE_WIDTH = 100;
    private static final int NUM_OF_CIRCLES = 3;
    private static final double BLOCK_X_UPPER_LEFT = 370;
    private static final double BLOCK_Y_UPPER_LEFT = 200;
    private static final double BLOCK_SIZE = 20;
    private static final int CIRCLE_START_RADIUS = 120;
    private static final String LEVEL_NAME = "Direct Hit";
    private SpriteCollection background;


    /**
     * a constructor for the class.
     */
    public DirectHit() {
        creatBackground();
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
     * returns the amount of balls in the current level.
     *
     * @return an integer of the amount of balls.
     */
    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }

    /**
     * the initial number of balls. generally, initialBallVelocities().size() == numberOfBalls().
     *
     * @return the velocity of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity velocity = Velocity.fromAngleAndSpeed(1, numberOfBalls() + 3);
        velocities.add(velocity);
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
     * return's the level's name.
     *
     * @return a string represent the name of the level.
     */
    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    /**
     * return's the background of the level.
     *
     * @return the Sprite of the background.
     */
    @Override
    public SpriteCollection getBackground() {
        return this.background;
    }

    /**
     * creating the background.
     */
    public void creatBackground() {
        SpriteCollection sprite = new SpriteCollection();
        //creating the back.
        Block back = new Block(Color.BLACK, new Rectangle(new Point(0, 0), 800, 600));
        sprite.addSprite(back);
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
        //creating the lines.
        Line right = new Line(getCenter().getX() - 150, getCenter().getY(),
                getCenter().getX() - 50, getCenter().getY());
        Line left = new Line(getCenter().getX() + 50, getCenter().getY(),
                getCenter().getX() + 150, getCenter().getY());
        Line up = new Line(getCenter().getX(), getCenter().getY() - 50,
                getCenter().getX(), getCenter().getY() - 150);
        Line low = new Line(getCenter().getX(), getCenter().getY() + 50,
                getCenter().getX(), getCenter().getY() + 150);
        //setting the colors
        right.setColor(Color.BLUE);
        left.setColor(Color.BLUE);
        up.setColor(Color.BLUE);
        low.setColor(Color.BLUE);
        //adding to the sprites array.
        sprite.addSprite(right);
        sprite.addSprite(left);
        sprite.addSprite(up);
        sprite.addSprite(low);

    }

    /**
     * creating the circles of the target.
     *
     * @param sprite the sprite we add the circles to.
     */
    public void createCircles(SpriteCollection sprite) {
        int radius = CIRCLE_START_RADIUS;
        for (int i = 0; i < NUM_OF_CIRCLES; i++) {
            Circle circle = new Circle(getCenter(), Color.BLUE, radius);
            sprite.addSprite(circle);
            radius -= 10;
        }
    }

    /**
     * returning the general center of the level.
     *
     * @return a point of the center related to the target.
     */
    public Point getCenter() {
        return new Point(BLOCK_X_UPPER_LEFT + (BLOCK_SIZE / 2), BLOCK_Y_UPPER_LEFT + (BLOCK_SIZE / 2));
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the array of all blocks created for the level.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(BLOCK_X_UPPER_LEFT, BLOCK_Y_UPPER_LEFT), 20, 20));
        block.setColor(Color.RED);
        blocks.add(block);
        return blocks;
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