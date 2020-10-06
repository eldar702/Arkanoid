package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;

import java.awt.Color;

/**
 * the animation of the count down screen before level start (3, 2, 1, go!).
 */
public class CountdownAnimation implements Animation {
    // class variable.
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private double helper;

    /**
     * //constructor.
     * @param numOfSeconds num of second to wait before start
     * @param countFrom the number to start the count down
     * @param gameScreen the screen which  the count down appear.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds * 1000;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.helper = countFrom;
    }

    /**
     *
     * The actions that happen in each frame.
     * @param d the surface which the function draw on.
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();

        this.gameScreen.drawAllOn(d);
        long timeOnScreen = (long) (this.numOfSeconds / helper);
        d.setColor(new Color(0, 110, 105));
        // for the first second.
        if (this.countFrom == helper) {
            d.drawText((d.getWidth() / 2) - 25, 100, Integer.toString(countFrom), 65);
        }
        // for all the second except the first and last.
        if (this.countFrom > 0 && this.countFrom < helper) {
            d.drawText((d.getWidth() / 2) - 25, 100, Integer.toString(countFrom), 65);

        }
        // for the last second.
        if (this.countFrom == 0) {
            d.drawText((d.getWidth() / 2) - 25, 100, "GO!", 65);
        }
        // the countdown had finished - go!.
        if (this.countFrom < 0) {
            this.stop = true;
        }
        // the waiting between numbers in the countdown
        if (this.countFrom != this.helper) {
            sleeper.sleepFor(timeOnScreen);
        }
        // decreasing 1 from the count.
        this.countFrom--;

    }
    /**
     * if the class should stop.
     * @return boolean of yes or no
     */
    public boolean shouldStop() {
        return this.stop; }

}