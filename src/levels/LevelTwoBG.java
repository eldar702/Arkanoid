package levels;
/**@author Eldar Shlomi.
 * id 205616634.*/
import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class for the background of level 2.
 */
public class LevelTwoBG implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        int gameWidth = GameLevel.getGameWidth(), gameHeight = GameLevel.getGameHeight();
        int helper50 = gameHeight / 12, helper25 = gameHeight / 24, helper10 = gameHeight / 60;
        int startLine = 30, numOfLines = 75;
        d.setColor(new Color(0xD4D020));
        for (int i = 1; i <= numOfLines; i++) {
            d.drawLine(helper10 * 11, helper10 * 12, startLine, helper10 * 27);
            startLine += helper10;
        }
        //make the sun by creating 3 balls in different color of yellow
        d.setColor(new Color(0xF6F2C6));
        d.fillCircle(helper10 * 11, helper10 * 12, helper10 * 7);
        d.setColor(new Color(0xE2E870));
        d.fillCircle(helper10 * 11, helper10 * 12, helper10 * 6);
        d.setColor(new Color(0xD4D027));
        d.fillCircle(helper10 * 11, helper10 * 12, helper50);
    }

    @Override
    public void timePassed() {

    }
}
