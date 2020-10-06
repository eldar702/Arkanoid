package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;
import java.awt.Color;


/**
 * indicator method for the number of lives which the player have.
 */
public class LivesIndicator implements Sprite {
    private geometry.Rectangle rectangle;
    private Counter counter;


        /**
         * constructor.
         * @param rectangle the rectangle where is the score count.
         * @param counter the counter of the points.
         */
        public LivesIndicator(Rectangle rectangle, Counter counter) {
            this.rectangle = rectangle;
            this.counter = counter;
        }
        @Override
        public void drawOn(DrawSurface d) {
            //variable declaration:
            d.setColor(Color.black);
            d.drawText((int) (rectangle.getWidth() / 10), d.getWidth() / 40 - 5,
                    "lives: " + this.counter.getValue(), 15);
        }


        @Override
        public void timePassed() {

        }
    }
