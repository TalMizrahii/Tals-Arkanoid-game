package Listener;

/**
 * the HitListener interface.
 *
 * <p></p>contain all methods related to the rectangle, including the rectangle's color, shape and position.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener we add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener we remove.
     */
    void removeHitListener(HitListener hl);
}
