package interfaces;
/**@author Eldar Shlomi.
 * id 205616634.*/

import geometry.Ball;
import geometry.Block;

/**
 * interface for registered hitlistener classes.
 */
public interface HitListener {
    /**
     * occur when notify variable being hit and "tell" it. the consequent upon is deleting the ball.
     * @param beingHit the being hit block.
     * @param hitter the hitter ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}