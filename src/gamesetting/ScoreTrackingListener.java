package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import geometry.Ball;
import geometry.Block;
import interfaces.HitListener;

/**
 * a listener class for the class tracking.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor for ScoreTrackingListener variable.
     * @param scoreCounter counter of score variable.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * occur when notify variable being hit and "tell" it. the consequent upon is adding points to the point's count.
     * @param beingHit the being hit block.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //decrease 1 from the counter of the exist blocks.
        getCounterPoint().increase(5);
    }

    /**
     * getter for the count of points.
     * @return the counter's value.
     */
    public Counter getCounterPoint() {
        return currentScore;
    }
}
