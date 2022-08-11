package GameRelated;

/**
 * a Counter who is responsible to keep track of given value.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class Counter {
    private int number; //the number of the counter.

    /**
     * a setter for the number.
     *
     * @param number the starting value of the counter.
     */
    public Counter(int number) {
        this.number = number;
    }

    /**
     * increase the value by a given number.
     *
     * @param number the number we increase the value by.
     */
    public void increase(int number) {
        this.number = getValue() + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number number the number we decrease the value by.
     */
    public void decrease(int number) {
        this.number = getValue() - number;
    }

    /**
     * getter for the current count.
     *
     * @return the value of the current count.
     */
    public int getValue() {
        return this.number;
    }
}