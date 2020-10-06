package levels;
/**@author Eldar Shlomi.
 * id 205616634.*/
import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Sprite;

import java.awt.Color;
/**
 * class for the background of level 1.
 */
public class LevelOneBG implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
       int gameWidth = GameLevel.getGameWidth();
       int gameHeight = GameLevel.getGameHeight();
       int helper50 = gameHeight / 12;
       int helper25 = gameHeight / 24;
       int helper10 = gameHeight / 60;
         // setting the game's background color
        d.setColor(Color.black);
        d.fillRectangle(0, GameLevel.getBorderWidth(), gameWidth, gameHeight);


        //drawing the sight on the surface - 4 lines.
        d.setColor(Color.blue);
        d.drawLine((gameWidth / 2), helper50,  (gameWidth / 2), helper10 * 18);           // upper sight line
        d.drawLine((gameWidth / 2), helper10 * 22, (gameWidth / 2),  helper50 * 7);  // upper sight line
        d.drawLine(helper10 * 38, gameHeight / 3,  helper10 * 23, gameHeight / 3);  // right sight line
        d.drawLine(helper10 * 42, gameHeight / 3,  gameHeight - 3 * helper10, gameHeight / 3);  // left sight line

        //drawing the sight on the surface - 4 lines.
        d.drawCircle(gameWidth / 2, gameHeight / 3, helper25 + helper50);         // smallest sight circle
        d.drawCircle(gameWidth / 2, gameHeight / 3, helper50 + helper50);        // middle sight circle
        d.drawCircle(gameWidth / 2, gameHeight / 3, helper25 * 5);        // biggest sight circle

    }

    @Override
    public void timePassed() {

    }
}
