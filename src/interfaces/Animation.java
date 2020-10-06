package interfaces;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;

/**
 * interface for different animation which accrue in the game.
 */
public interface Animation {
    /**
     * the action which happened in the animation classes for every frame of the animation.
     * @param d the surface of the game
     */
    void doOneFrame(DrawSurface d);

    /**
     * when yes the animation will stop.
     * @return yes or no answer.
     */
    boolean shouldStop();
}

