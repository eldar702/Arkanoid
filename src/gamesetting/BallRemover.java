/**@author Eldar Shlomi.
 * id 205616634.*/
package gamesetting;
import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;

/**
 * class for making the action of deleting balls.
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game the game variable.
     * @param removedBalls counter for the number of balls which in the game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * occur when notify variable being hit and "tell" it. the consequent upon is deleting the ball.
     * @param beingHit the being hit block.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //beingHit.removeHitListener(this);
        hitter.removeFromGame(this.game);
        //decrease 1 from the counter of the exist ball.
        game.getCounterBall().decrease(1);
    }
}

