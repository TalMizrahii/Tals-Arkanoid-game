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
 * The "FinalFour" level class.
 *
 * <p>responsible to run the game with specific background, block and balls for this level.
 * * @author Tal Mizrahi.
 *  * @version 1.0
 * * @since 16/03/2022
 */
public class FinalFour implements LevelInformation {
    private static final String LEVEL_NAME = "Final Four";
    private SpriteCollection background;
    private List<Block> blocks;
    private static final int NUM_BALLS = 3;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_STEP = 13;
    private static final int NUM_OF_BLOCKS = 15 * 7;
    private static final int NUM_OF_BLOCKS_ROW = 15;
    private static final int NUM_ROWS = 7;


    private static final double BLOCK_HEIGHT = 23;
    private static final int FLOOR = 120;
    private static final double BLOCK_WIDTH = (float) 760 / NUM_OF_BLOCKS_ROW;

    /**
     * a constructor for the level's class.
     */
    public FinalFour() {
        creatBackground();
        createBlocks();
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
     * creating the blocks for this level.
     */
    public void createBlocks() {
        List<Block> blocks = new ArrayList<>();
        //colors array for each line.
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.pink, Color.cyan};
        int xPos = 20;
        int floor = FLOOR;
        // create new blocks.
        for (int k = 0; k < NUM_ROWS; k++) {
            for (int i = 0; i < 8; i++) {
                Block block;
                if (i != 7) {
                    block = new Block(new Rectangle(new Point(xPos, floor), BLOCK_WIDTH, BLOCK_HEIGHT));
                    xPos += BLOCK_WIDTH; // set the point to the next step.

                } else {
                    block = new Block(new Rectangle(new Point(xPos, floor), BLOCK_WIDTH + 10, BLOCK_HEIGHT));
                    xPos += BLOCK_WIDTH + 10; // set the point to the next step.

                }
                block.setColor(colors[k % 7]); // set its color.
                blocks.add(block);
            }
            for (int i = 0; i < 7; i++) {
                Block block = new Block(new Rectangle(new Point(xPos, floor), BLOCK_WIDTH, BLOCK_HEIGHT));
                block.setColor(colors[k % 7]); // set its color.
                blocks.add(block);
                xPos += BLOCK_WIDTH; // set the point to the next step.
            }
            xPos = 20;
            floor += BLOCK_HEIGHT;
        }
        setBlocks(blocks);
    }

    /**
     * creating the background.
     */
    public void creatBackground() {
        SpriteCollection sprite = new SpriteCollection();
        creatBack(sprite);
        //create clouds.
        creatClouds(sprite, 60, 325);
        creatClouds(sprite, 150, 250);
        creatClouds(sprite, 300, 395);
        creatClouds(sprite, 500, 380);
        creatClouds(sprite, 400, 200);
        creatClouds(sprite, 500, 250);
        creatClouds(sprite, 250, 100);
        setBackground(sprite);
    }


    /**
     * creating the clouds on the background.
     *
     * @param sprite the sprite we add the circles to.
     * @param xStart the start point of the cloud.
     * @param yStart the y start location of the cloud.
     */
    public void creatClouds(SpriteCollection sprite, double xStart, double yStart) {

        //create center for the cloud.
        Point point1 = new Point(xStart, yStart);
        Point point2 = new Point(xStart + 70, yStart + 15);
        Point point3 = new Point(xStart + 50, yStart - 15);
        Point point4 = new Point(xStart + 30, yStart - 2);
        Point point5 = new Point(xStart + 5, yStart + 30);
        Point point6 = new Point(xStart + 40, yStart + 30);

        // the cloud.
        Circle circle1 = new Circle(specificColor(169, 169, 169), point1, 35);
        Circle circle2 = new Circle(specificColor(169, 169, 169), point2, 40);
        Circle circle3 = new Circle(specificColor(192, 192, 192), point3, 30);
        Circle circle4 = new Circle(specificColor(169, 169, 169), point4, 35);
        Circle circle5 = new Circle(specificColor(192, 192, 192), point5, 25);
        Circle circle6 = new Circle(specificColor(192, 192, 192), point6, 30);

        //create the lines of the cloud.
        Line line1 = new Line(point1, new Point(point1.getX() - 20, point1.getY() + 200));
        Line line2 = new Line(point2, new Point(point2.getX() - 20, point2.getY() + 200));
        Line line3 = new Line(point3, new Point(point3.getX() - 20, point3.getY() + 200));
        Line line4 = new Line(point4, new Point(point4.getX() - 20, point4.getY() + 200));
        Line line5 = new Line(point5, new Point(point5.getX() - 20, point5.getY() + 200));
        Line line6 = new Line(point6, new Point(point6.getX() - 20, point6.getY() + 200));

        //set colors for the lines.
        line1.setColor(Color.WHITE);
        line2.setColor(Color.WHITE);
        line3.setColor(Color.WHITE);
        line4.setColor(Color.WHITE);
        line5.setColor(Color.WHITE);
        line6.setColor(Color.WHITE);

        //set the lines to the Sprite array.
        sprite.addSprite(line1);
        sprite.addSprite(line2);
        sprite.addSprite(line3);
        sprite.addSprite(line4);
        sprite.addSprite(line5);
        sprite.addSprite(line6);

        // set the circles to the sprite array.
        sprite.addSprite(circle1);
        sprite.addSprite(circle2);
        sprite.addSprite(circle3);
        sprite.addSprite(circle4);
        sprite.addSprite(circle5);
        sprite.addSprite(circle6);
    }

    /**
     * creating the back color of the screen.
     *
     * @param sprite the sprite we add the circles to.
     */
    public void creatBack(SpriteCollection sprite) {
        //creating the back.
        Block back = new Block(specificColor(8, 141, 180),
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
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return generally, This number should be <= blocks.size();
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
