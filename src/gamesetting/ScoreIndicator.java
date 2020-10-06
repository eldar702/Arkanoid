package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * indicator method for the score represent. (the count of
 * score of the game, being in the middle top how to game).
 */
public class ScoreIndicator implements Sprite {
    private Color color;
    private Rectangle rectangle;
    private Counter counter;


    /**
     * constructor.
     * @param rectangle the rectangle where is the score count.
     * @param color the color of the rectangle.
     * @param counter the counter of the points.
     */
    public ScoreIndicator(Rectangle rectangle, Color color, Counter counter) {
        this.rectangle = rectangle;
        this.color = color;
        this.counter = counter;
    }
    @Override
    public void drawOn(DrawSurface d) {
        DrawSurface surface = d;
        //variable declaration:
        double width = d.getWidth();
        double height = d.getWidth() / 40;


        surface.setColor(Color.lightGray);
        surface.fillRectangle(0, 0, (int) width, (int) height);
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 2 - 70, d.getWidth() / 40 - 5, "score: " + this.counter.getValue(), 15);
    }


    @Override
    public void timePassed() {

    }
}
