package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * the screen which appears when the user pause the game.
 */
public class PauseScreen implements Animation {
    private boolean stop;
    /**
     * constructor for the PauseScreen variable.
     */
    public PauseScreen() {
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);

    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}