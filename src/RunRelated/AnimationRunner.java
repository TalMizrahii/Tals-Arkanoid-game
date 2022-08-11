package RunRelated;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The "AnimationRunner" class.
 *
 * <p>responsible to run the game and contain a "run" loop to do so.
 * * @author Tal Mizrahi.
 * * @version 1.0.
 * * @since 16/03/2022
 */
public class AnimationRunner {
    private GUI gui; // the specific "GUI" for this runner.
    private int framesPerSecond; // frame rate for the run
    private Sleeper sleeper; // the sleeper

    /**
     * a constructor for the class.
     *
     * @param gui             the Gui we will use to print on with "Run" method.
     * @param framesPerSecond the frame rate for the specific screen.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        setGui(gui);
        setFramesPerSecond(framesPerSecond);
        setSleeper(new Sleeper());
    }

    /**
     * a getter for the sleeper.
     *
     * @return the sleeper's value
     */
    public Sleeper getSleeper() {
        return this.sleeper;
    }

    /**
     * a setter for the sleeper.
     *
     * @param sleeper the sleeper's value.
     */
    public void setSleeper(Sleeper sleeper) {
        this.sleeper = sleeper;
    }

    /**
     * A setter for the gui member.
     *
     * @return the gui member's value.
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * a getter for the gui member.
     *
     * @param gui The gui member's value
     */
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    /**
     * a getter for the frame rate member.
     *
     * @return the frame rate value.
     */
    public int getFramesPerSecond() {
        return this.framesPerSecond;
    }

    /**
     * a setter for the frame rate.
     *
     * @param framesPerSecond the frame rate's value.
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * starting the game and running the animation screen.
     *
     * @param animation The animation object we use to run the game
     *                  Usually a level, but could be a "pause" or "finish" screen.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = getFramesPerSecond();
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = getGui().getDrawSurface();
            animation.doOneFrame(d);
            getGui().show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                getSleeper().sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}