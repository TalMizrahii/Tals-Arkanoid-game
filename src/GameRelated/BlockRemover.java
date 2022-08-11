package GameRelated;

import Collidables.Ball;
import Listener.HitListener;
import SpriteRelated.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class BlockRemover implements HitListener {
    private GameLevel game; //the game we delete the block from.
    private Counter remainingBlocks; //counter to track how many block are in the game.

    /**
     * a constructor for the class.
     *
     * @param removedBlocks counter to track how many blocks are in the game.
     * @param game          the game we delete the block from.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        setGame(game);
        setRemainingBlocks(removedBlocks);
    }

    /**
     * a getter for the game member.
     *
     * @return the game member.
     */
    public GameLevel getGame() {
        return this.game;
    }

    /**
     * a setter for the game member.
     *
     * @param game the game member.
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * a getter for the counter of the remaining blocks.
     *
     * @return the counter class of the remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * a setter for the counter of the remaining blocks.
     *
     * @param remainingBlocks the counter class of the remaining blocks.
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * a method who takes care of the hit and it's consequences of the hit.
     * specific to this listener, the method removes the block from the game.
     *
     * @param beingHit the object who has been hit.
     * @param hitter   the object who hits the object who has been hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(getGame()); // removing the block.
        beingHit.removeHitListener(this); //removing it from the listener's list.
        getRemainingBlocks().decrease(1); //updating the counter by decreasing 1 from it.
    }
}