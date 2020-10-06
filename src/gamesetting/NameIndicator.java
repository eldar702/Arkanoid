package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * indicator for the name counter.
 */
public class NameIndicator implements Sprite {
    private geometry.Rectangle rectangle;
    private Counter counter;
    private String name;
    /**
     * constructor.
     * @param rectangle the rectangle where is the score count
     * @param counter the counter of the points
     * @param levelName the name of the level
     */
    public NameIndicator(Rectangle rectangle, Counter counter, String levelName) {
        this.rectangle = rectangle;
        this.counter = counter;
        this.name = levelName;
    }
    @Override
    public void drawOn(DrawSurface d) {
        //variable declaration:
        d.setColor(Color.black);
        d.drawText((int) (rectangle.getWidth() / 10 * 7), d.getWidth() / 40 - 5,
                "level name: " + this.name, 15);
    }


    @Override
    public void timePassed() {

    }
}

