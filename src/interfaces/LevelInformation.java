package interfaces;
/**@author Eldar Shlomi.
 * id 205616634.*/

import geometry.Block;
import geometry.Velocity;

import java.util.List;

/**
 * interface for all the levels, including all the information of the level.
 */
public interface LevelInformation {
    /**
     * the number of ball which will be in a specific level.
     * @return the number
     */
     int numberOfBalls();
        // The initial velocity of each ball

    /**
     * The initial velocity of each ball.
     * @return a list of the balls's velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * the paddle speed.
     * @return the speed
     */
    int paddleSpeed();

    /**
     * the paddle's width.
     * @return the width.
     */
    int paddleWidth();

    /**
     * the level name which will be displayed at the top of the screen.
     * @return the name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return the sprite
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level.
     * @return a list of the blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return the number
     */
    int numberOfBlocksToRemove();


}