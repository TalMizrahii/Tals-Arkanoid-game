package RunRelated;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The "KeyPressStoppableAnimation" class.
 * basically a decorator for all "press to stop" classes.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class KeyPressStoppableAnimation implements Animation {
    private String key;
    private Animation animation;
    private KeyboardSensor sensor;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * a constructor for the class.
     *
     * @param sensor    the keyboard sensor.
     * @param key       the key to press to continue.
     * @param animation the animation to draw.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        setAnimation(animation);
        setKey(key);
        setSensor(sensor);
        setStop(false);
        setAlreadyPressed(true);
    }

    /**
     * a getter to check if the key is already pressed.
     *
     * @return a boolean value.
     */
    public boolean isAlreadyPressed() {
        return this.isAlreadyPressed;
    }

    /**
     * a setter to check if the key is already pressed.
     * @param alreadyPressed a boolean value.
     */
    public void setAlreadyPressed(boolean alreadyPressed) {
        isAlreadyPressed = alreadyPressed;
    }

    /**
     * a getter for the key.
     *
     * @return the key's value.
     */
    public String getKey() {
        return key;
    }

    /**
     * a getter for the key.
     *
     * @param key the key's value.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * a getter for the animation.
     *
     * @return the animation's value.
     */
    public Animation getAnimation() {
        return animation;
    }

    /**
     * a getter for the animation.
     *
     * @param animation the animation's value.
     */
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    /**
     * a getter for the sensor.
     *
     * @return the sensor's value.
     */
    public KeyboardSensor getSensor() {
        return sensor;
    }

    /**
     * a setter for the sensor.
     *
     * @param sensor the sensor's value.
     */
    public void setSensor(KeyboardSensor sensor) {
        this.sensor = sensor;
    }

    /**
     * a getter for the stop member.
     *
     * @return the stop boolean true or false.
     */
    public boolean getStop() {
        return stop;
    }

    /**
     * a setter for the stop member.
     *
     * @param stop the boolean true or false for the stop.
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Drawing the frame of a level on each state of the game.
     *
     * @param d the surface we print on the frame of the game.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        getAnimation().doOneFrame(d);

        if (getSensor().isPressed(getKey()) && !isAlreadyPressed()) {
            setStop(true);
        }
        setAlreadyPressed(false);
    }

    /**
     * Checking the boolean condition to stop the game if necessary.
     *
     * @return A boolean true if the game is still on, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return getStop();
    }
}