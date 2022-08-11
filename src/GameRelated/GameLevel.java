package GameRelated;

import Collidables.Ball;
import Collidables.Collidable;
import Collidables.Paddle;
import Collidables.Velocity;
import Geomatry.Line;
import Listener.HitListener;


import Geomatry.Point;
import Geomatry.Rectangle;
import RunRelated.AnimationRunner;
import RunRelated.CountdownAnimation;
import SpriteRelated.Sprite;
import SpriteRelated.ScoreIndicator;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import SpriteRelated.LevelName;
import SpriteRelated.SpriteCollection;
import SpriteRelated.Block;
import RunRelated.EndScreen;
import RunRelated.PauseScreen;
import RunRelated.KeyPressStoppableAnimation;
import RunRelated.Animation;
import RunRelated.LevelInformation;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the "Game" class.
 *
 * <p>contain all methods related to the Game, including initializing and running the game.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class GameLevel implements Animation {
    private GUI gui; // The GUI frame that will host the game.
    private SpriteCollection sprites; // The array of all sprites related to the game.
    private GameEnvironment environment; // The game environment of the current game.
    private Counter counterBlocks; // a counter for the blocks.
    private Counter counterBalls; // a counter for the balls.
    private static final int COUNTER_START = 0; // the starting value for the counters.
    private static final int ADD_ONE = 1;
    private Counter score; // the score's counter.
    private Paddle paddle; // the paddle of the game.
    private AnimationRunner runner; // a runner to run the game level.
    private boolean running; // a boolean value to track is the game is still on.
    private static final int FRAME_PER_SECONDS = 1000 / 60; //the frame rate of the class.
    private static final int BALLS_SPEED = 4;
    private KeyboardSensor keyboard; // a sensor to indicate if the user pressed a pause key.
    private static final int COUNT_FROM = 3; // countdown time for the level.
    private static final int START_TIME_GAP = 1200; // to know how much time between countdown numbers.
    // the level information member who store all the relevant data about the level.
    private LevelInformation levelInformation;

    /**
     * a constructor for the GameRelated.Game instance.
     *
     * @param levelInformation the level information member who store all the relevant data about the level.
     * @param gui              the gui of the game.
     * @param runner           the runner of the game.
     * @param score            the counter of the score.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, AnimationRunner runner, Counter score) {
        setScore(score);
        setLevel(levelInformation);
        setEnvironment(new GameEnvironment());
        this.sprites = new SpriteCollection();
        //Setting a default "GUI" member in the designated size.
        setGui(gui);
        setRunning(true);
        setRunner(runner);
        setKeyboard(gui);
    }

    /**
     * a setter for the level info.
     *
     * @param levelInformation the level info value.
     */
    public void setLevel(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    /**
     * a getter for the level info.
     *
     * @return the level info member.
     */
    public LevelInformation getLevel() {
        return this.levelInformation;
    }

    /**
     * the keyboard's sensor getter.
     *
     * @return the state of the pressed key.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * a setter for the keyboard sensor.
     *
     * @param gui the gui of the level.
     */
    public void setKeyboard(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * a getter for the gui.
     *
     * @return the gui's value.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * a getter for the AnimationRunner member.
     *
     * @return the AnimationRunner member's value.
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }

    /**
     * a setter for the AnimationRunner member.
     *
     * @param runner the AnimationRunner member's value.
     */
    public void setRunner(AnimationRunner runner) {
        this.runner = runner;
    }

    /**
     * a getter fer the running member.
     *
     * @return a boolean value of the game's state.
     */
    public boolean getRunning() {
        return this.running;
    }

    /**
     * a getter fer the running member.
     *
     * @param running a boolean value of the game's state. starts with "true", and may be changed.
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * a getter for the block's counter.
     *
     * @return the counter of the block's.
     */
    public Counter getCounterBlocks() {
        return this.counterBlocks;
    }

    /**
     * a setter for the blocks counter.
     *
     * @param counter the counter of the block's.
     */
    public void setCounterBlocks(Counter counter) {
        this.counterBlocks = counter;
    }

    /**
     * a getter for the balls counter.
     *
     * @return the counter od the balls.
     */
    public Counter getCounterBalls() {
        return this.counterBalls;
    }

    /**
     * a setter for the counter of the balls.
     *
     * @param counterBalls the value of the counter of the balls.
     */
    public void setCounterBalls(Counter counterBalls) {
        this.counterBalls = counterBalls;
    }

    /**
     * a getter for the score's counter.
     *
     * @return the score counter
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * a setter for the score's counter.
     *
     * @param score the score's counter.
     */
    public void setScore(Counter score) {
        this.score = score;
    }

    /**
     * a getter for the paddle.
     *
     * @return the paddle's instance.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * a setter for the paddle.
     *
     * @param paddle the paddle's instance.
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * a setter for the gui member.
     *
     * @param gui the new gui value.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * a setter for the environment member.
     *
     * @param environment the new environment value.
     */
    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * a getter for the environment member.
     *
     * @return the environment value.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * adding a collidable object to the collidables array in the environment member.
     *
     * @param c the collidable object.
     */
    public void addCollidable(Collidable c) {
        if (this.environment.getCollidables() == null) {
            this.environment = new GameEnvironment();
        }
        this.environment.addCollidable(c);
    }

    /**
     * Adding a new sprite to the Sprites array.
     *
     * @param s The new sprite to be added.
     */
    public void addSprite(Sprite s) {
        //Checking if the sprites array is null or not.
        if (this.sprites.getSprites() == null) {
            this.sprites = new SpriteCollection();
        }
        this.sprites.addSprite(s);
    }

    /**
     * a getter for the sprites collection.
     *
     * @return the array of sprites.
     */
    public SpriteCollection getSprites() {
        if (this.sprites == null) {
            setSprites(new SpriteCollection());
            return this.sprites;
        }
        return this.sprites;
    }

    /**
     * a setter for the sprites collection.
     *
     * @param sprites the array of sprites.
     */
    public void setSprites(SpriteCollection sprites) {
        this.sprites = sprites;
    }

    /**
     * creating the borders of the game.
     */
    public void createBorders() {
        //Setting the borders points.
        Point up = new Point(0, 20);
        Point left = new Point(0, 40);
        Point right = new Point(780, 40);
        Block[] borders = new Block[3];

        // Creating each rectangle's borders.
        borders[0] = new Block(new Rectangle(up, 800, 20));
        borders[1] = new Block(new Rectangle(left, 20, 580));
        borders[2] = new Block(new Rectangle(right, 20, 580));

        for (int i = 0; i < 3; i++) {
            borders[i].addToGame(this);
            borders[i].setColor(Color.GRAY);

        }
    }

    /**
     * creating and adding the death region to the game.
     */
    public void deathRegion() {
        Point low = new Point(20, 605);
        Block death = new Block(new Rectangle(low, 760, 20));
        death.setColor(randomColor(200));
        //creating a ball remover.
        death.addHitListener(new BallRemover(getCounterBalls(), this));
        //creating a death region for the game.
        death.addToGame(this);
    }

    /**
     * creating the blocks of the game.
     *
     * @param remover the block remover.
     * @param score   the score's listener.
     */
    public void createLevelBlocks(BlockRemover remover, HitListener score) {
        List<Block> blocks = getLevel().blocks();
        for (Block block : blocks) {
            block.addHitListener(remover); // adding the remover listener.
            getCounterBlocks().increase(ADD_ONE); //increasing the counter of blocks by 1.
            block.addToGame(this); // adding the block to the game.
            block.addHitListener(score); //adding the score as a listener.
        }
    }


    /**
     * color generator.
     *
     * @param start the rang of the color to be set.
     * @return random Color variable.
     */
    public Color randomColor(int start) {
        Random rand = new Random(start); // create a random-number generator
        float r = rand.nextFloat(); // get a float in range 0-256
        float g = rand.nextFloat(); // get float in range 0-256
        float b = rand.nextFloat(); // get integer in range 0-256
        return new Color(r, g, b);
    }

    /**
     * creating an extra ball when hitting a specific block.
     *
     * @param ball the ball we add to the game.
     */
    public void extraBall(Ball ball) {
        ball.setVelocity(Velocity.fromAngleAndSpeed(35, BALLS_SPEED)); // setting the velocity.
        ball.addToGame(this); // adding to the game.
        getCounterBalls().increase(1); //increasing the counter.
        ball.setGameEnvironment(getEnvironment()); //adding the game environment
        getPaddle().addBall(ball); // adding the ball to the paddle.
    }

    /**
     * creating the score counter.
     *
     * @return the score's listener.
     */
    public HitListener createScore() {
        //creating the score's space.
        Block block = new Block(Color.WHITE, new Rectangle(new Point(0, 0), 800, 20));
        block.addToGame(this);
        //creating the counter of the score.
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(getScore());
        setScore(getScore());
        ScoreIndicator scoreIndicator = new ScoreIndicator(getScore());
        scoreIndicator.addToGame(this);
        return scoreTrackingListener;
    }

    /**
     * creating the name of the level.
     */
    public void createLevelName() {
        LevelName levelName = new LevelName(getLevel().levelName());
        levelName.addToGame(this);
    }

    /**
     * creating the paddle.
     */
    public void createPaddle() {
        double upperX = (float) (760 - getLevel().paddleWidth()) / 2;

        //declaring new paddle.
        Paddle paddle = new Paddle(new Rectangle(new
                Point(upperX, 555), getLevel().paddleWidth(), 17), this.gui, Color.ORANGE);
        paddle.addToGame(this); // adding to the game.
        setPaddle(paddle); //setting the paddle as a member.
    }

    /**
     * calculates the center upper point of the paddle.
     *
     * @return a Point of the center of the paddle.
     */
    public Point centerPaddle() {
        if (getPaddle() != null) {
            Line line = new Line(getPaddle().getCollisionRectangle().getUpperLeft(),
                    getPaddle().getCollisionRectangle().getUpperRight());
            return line.middle();
        }
        return new Point(500, 555);
    }

    /**
     * creating the balls and adding them to the game.
     *
     * @return an array of balls.
     */
    public ArrayList<Ball> createBalls() {
        //the array of colors.
        Color[] colors = {Color.RED, Color.GREEN, Color.PINK, Color.GRAY, Color.YELLOW, Color.BLUE};
        //adding the balls to an array and adding it to the paddle instance.
        ArrayList<Ball> balls = new ArrayList<>();
        List<Velocity> velocities = getLevel().initialBallVelocities();


        for (int i = 0; i < getLevel().numberOfBalls(); i++) {
            Ball ball = new Ball(centerPaddle(), i + 3, colors[i % 6]);
            ball.setVelocity(velocities.get(i));
            balls.add(ball);
            ball.addToGame(this); // adding the ball to the game.
            ball.setGameEnvironment(getEnvironment());
        }
        setCounterBalls(new Counter(getLevel().numberOfBalls()));
        getPaddle().setBalls(balls); //setting the balls.
        return balls;
    }

    /**
     * adding the sprites to the level from the level's class.
     */
    public void addSpritesFromLevel() {
        for (int i = 0; i < getLevel().getBackground().getSprites().size(); i++) {
            addSprite(getLevel().getBackground().getSprites().get(i));
        }
    }

    /**
     * initiating all the game components and adding them to the game.
     */
    public void initialize() {
        //adding the sprites to the level from the level's class.
        addSpritesFromLevel();
        //creating a new counter's who begin with 0;
        setCounterBlocks(new Counter(COUNTER_START));
        setCounterBalls(new Counter(COUNTER_START));
        //the game's block remover.
        BlockRemover remover = new BlockRemover(this, getCounterBlocks());
        //setting new environment.
        setEnvironment(new GameEnvironment());
        //creating paddle.
        createPaddle();
        //creating balls.
        ArrayList<Ball> balls = createBalls();
        getPaddle().setBalls(balls);
        //adding all the borders to the game.
        createBorders();
        //creating score.
        HitListener score = createScore();
        // creating the name of the level.
        createLevelName();
        //create blocks.
        createLevelBlocks(remover, score);
        //creating a death region.
        deathRegion();
//        setRunner(new AnimationRunner(getGui(), FRAME_PER_SECONDS));
    }

    /**
     * starting the game and running the animation screen.
     */
    public void run() {
        // countdown before turn starts.
        getRunner().setFramesPerSecond(START_TIME_GAP); // setting the countdown gap.
        getRunner().run(new CountdownAnimation(COUNT_FROM, getSprites())); // running the countdown.
        // resetting the original game runner and running the game.
        getRunner().setFramesPerSecond(FRAME_PER_SECONDS);
        setRunning(true);
        getRunner().run(this);
    }

    /**
     * remover of a collidable object.
     *
     * @param c the object we remove.
     */
    public void removeCollidable(Collidable c) {
        getEnvironment().getCollidables().remove(c);
    }

    /**
     * remover of a sprite object.
     *
     * @param s the object we remove.
     */
    public void removeSprite(Sprite s) {
        getSprites().getSprites().remove(s);
    }

    /**
     * Drawing the frame of a level on each state of the game.
     *
     * @param d the surface we print on the frame of the game.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // checking if we want to stop the game.
        if (this.keyboard.isPressed("p")) {
            getRunner().run(new KeyPressStoppableAnimation(getKeyboard(), "space", new PauseScreen()));
//            getRunner().run(new PauseScreen(getKeyboard()));
        }
        //checking if there are still balls in the game.
        if (getCounterBalls().getValue() <= 0) {
            getRunner().run(new KeyPressStoppableAnimation(getKeyboard(),
                    "space", new EndScreen(getScore().getValue(), "end")));
            getGui().close();
            setRunning(false);
        }
        //checking if there are still blocks in the game.
        if (getCounterBlocks().getValue() == 0) {
            getScore().increase(95);
            getCounterBlocks().decrease(1);
            setRunning(false);
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

    }

    /**
     * Checking the boolean condition to stop the game if necessary.
     *
     * @return A boolean true if the game is still on, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return !getRunning();
    }
}