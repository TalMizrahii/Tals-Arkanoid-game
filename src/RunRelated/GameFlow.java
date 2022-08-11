package RunRelated;

import GameRelated.Counter;
import GameRelated.GameLevel;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * in charge of moving from level to level.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class GameFlow {
    private KeyboardSensor keyboard; // a sensor to indicate if the user pressed a pause key.
    private AnimationRunner animationRunner;
    private Counter score;
    private GUI gui;


    /**
     * a constructor for the class.
     *
     * @param ar    the animation runner for the game.
     * @param ks    the keyboard sensor.
     * @param score the score counter to keep track of score.
     * @param gui   the gui of the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, Counter score, GUI gui) {
        setGui(gui);
        setAnimationRunner(ar);
        setKeyboard(ks);
        setScore(score);
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
     * a setter for the gui.
     *
     * @param gui the gui's value.
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * a getter for the keyboard sensor.
     *
     * @return the keyboard sensor.
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * a setter for the keyboard's sensor.
     *
     * @param keyboard the sensor's value.
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.keyboard = keyboard;
    }

    /**
     * a getter for the runner.
     *
     * @return the runner's value.
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    /**
     * a setter for the runner.
     *
     * @param animationRunner the value of the animation runner.
     */
    public void setAnimationRunner(AnimationRunner animationRunner) {
        this.animationRunner = animationRunner;
    }

    /**
     * a getter for the score.
     *
     * @return the score's counter.
     */
    public Counter getScore() {
        return score;
    }

    /**
     * a setter for the score.
     *
     * @param score the new score's value.
     */
    public void setScore(Counter score) {
        this.score = score;
    }

    /**
     * a method who runs all the levels one by one.
     *
     * @param levels the array of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // go over every level.
        for (int i = 0; i < levels.size(); i++) {
            GameLevel level = new GameLevel(levels.get(i), getGui(), getAnimationRunner(), getScore());
            level.initialize();
            //run the game as long as no stop signs occur.
            while (level.getRunning()) {
                level.run();
            }
            //if no balls left, break.
            if (level.getCounterBalls().getValue() <= 0) {
                break;
            }
            if (i == levels.size() - 1 && level.getCounterBlocks().getValue() <= 0) {
                level.getRunner().run(new KeyPressStoppableAnimation(getKeyboard(), "space",
                        new EndScreen(getScore().getValue(), "win")));
            }
        }
    }
}