package interfaces;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;

/**
 *  Sprite interface.
 *  interface for sprite objects.
 *  (for now classes Block and Paddle).
 */
public interface Sprite {
    /**
     * draw the sprite on the game screen.
     * @param d - the game surface.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}

