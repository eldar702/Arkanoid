package levels;
/**@author Eldar Shlomi.
 * id 205616634.*/
import biuoop.DrawSurface;
import gamesetting.GameLevel;
import geometry.Point;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class for the background of level 3.
 */
public class LevelThreeBG implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        int gameWidth = GameLevel.getGameWidth(), gameHeight = GameLevel.getGameHeight();
        int helper50 = gameHeight / 12, helper25 = gameHeight / 24, helper10 = gameHeight / 60, helper5 = helper10 / 2;
        Point firstPoint = new Point(helper50 * 3, helper50 * 9 + helper5);
        d.setColor(new Color(0x126C0D));
        d.fillRectangle(0, helper10 * 2, gameWidth, gameHeight);
        //making the "ball" on top of the bulding
        d.setColor(Color.yellow);
        d.fillCircle(helper10 * 11 + helper5, helper25 * 9 + helper10, helper10 + helper5);
        d.setColor(Color.RED);
        d.fillCircle(helper10 * 11 + helper5, helper25 * 9 + helper10, helper10);
        d.setColor(Color.WHITE);
        d.fillCircle(helper5 * 23, helper5 * 47, 4);

        //drawing the building
        d.setColor(Color.darkGray);
        d.fillRectangle(helper50 + helper5 * 3, helper50 * 9, helper50 * 2, helper50 * 3);
        d.fillRectangle(helper50 * 2, helper10 * 38 + helper5, helper10 * 3, helper50 + helper5 * 3);
        d.fillRectangle(helper10 * 11, helper50 * 5, helper10, helper5 * 27);


        // create the blocks for the game by using 2 loops.
        d.setColor(Color.white);
        for (int i = 0; i < helper5; i++) {
            for (int j = 0; j < helper5; j++) {
                d.fillRectangle((int) firstPoint.getX(), (int) firstPoint.getY(), helper10, helper10 * 2);
                firstPoint = new Point((int) firstPoint.getX() - (helper10 * 2), (int) firstPoint.getY());
            }
            firstPoint = new Point(helper50 * 3, (int) firstPoint.getY() + helper10 * 3);
        }
    }

    @Override
    public void timePassed() {

    }
}
