package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;

/**
 * class for making the action of deleting blocks.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     *
     * @param game - the game played.
     * @param removedBlocks the count of the blocks which in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * occur when notify variable being hit and "tell" it. the consequent upon is deleting the being hit block.
     * @param beingHit the being hit block.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        //make the ball considering the collidable object which exists in the game environment.
        hitter.getGameE().removeCollidable(beingHit);
        //decrease 1 from the counter of the exist blocks.
        game.getCounterBlock().decrease(1);

        }
    }
