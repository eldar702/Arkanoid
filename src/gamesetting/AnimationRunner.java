/**@author Eldar Shlomi.
 * id 205616634.*/

package gamesetting;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * create the animation effect.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    // height of the game screen.
    private static final int GAME_HIGHT = 600;
    // width of the game screen.
    private static final int GAME_WIDTH = 800;

    /**
     * constructor.
     * @param framesPerSecond how much frame of picture set for second (that what create the animation illusion).
     */
    public AnimationRunner(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = new GUI("Arkanoid",  GAME_WIDTH,  GAME_HIGHT);
    }

    /**
     *  run a loop which create the animation.
     * @param animation the specific animation which make the animation effect on
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * getter for the gui.
     * @return the gui.
     */
    public GUI getGui() {
        return gui;
    }
}