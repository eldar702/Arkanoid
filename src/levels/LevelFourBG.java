package levels;
/**@author Eldar Shlomi.
 * id 205616634.*/
import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Sprite;

import java.awt.Color;

/**
 * class for the background of level 4.
 */
public class LevelFourBG implements Sprite {



    @Override
    public void drawOn(DrawSurface d) {
        int gameWidth = GameLevel.getGameWidth(), gameHeight = GameLevel.getGameHeight();
        int helper50 = gameHeight / 12, helper25 = gameHeight / 24, helper10 = gameHeight / 60, helper5 = helper10 / 2;
        //drawing the background color
        d.setColor(new Color(0x24BADB));
        d.fillRectangle(0, helper10 * 2, gameWidth, gameHeight);


        // making the first cloud
        addCloud(d, helper50 * 13, helper10 * 37, helper10 * 3);
        addCloud(d, helper50 * 6, helper10 * 37, helper10 * 3);
        addCloud(d, helper50 * 2, gameWidth / 2, helper10 * 3);

        }
    // adding the cloud, by adding 5 balls and lines represent rain

    /**
     * adding clouds to the background by giving a starting point coordinate (make the cloud include the "rain".
     * @param d the surface how the game.
     * @param firstPX the first point X coordinate
     * @param firstPY the first point Y coordinate
     * @param size the size of circles whose create the cloud
     */
    public void addCloud(DrawSurface d, int firstPX, int firstPY, int size) {
        int gameWidth = GameLevel.getGameWidth(), gameHeight = GameLevel.getGameHeight();
        int helper50 = gameHeight / 12, helper25 = gameHeight / 24, helper10 = gameHeight / 60, helper5 = helper10 / 2;
        // drawing the rain
        int helper = firstPX;
        d.setColor(Color.white);
        for (int i = 0; i < helper10; i++) {
            d.drawLine(helper - helper5 * 3, firstPY, helper - (helper10 * 6), gameHeight);
            helper += 10;
        }
        helper = firstPX;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3 - i; j++) {
                randomCloudColor(j + i, d);
                d.fillCircle(firstPX + (helper5 * helper5) * j, firstPY, size);
            }
            firstPX += helper10;
            firstPY += helper10 * 2;
        }
    }

    /**
     * make the different white shade of the cloud.
     * @param num represent the different colors
     * @param d the game surface
     */
    public void randomCloudColor(int num, DrawSurface d) {
        if (num == 0) {
            d.setColor(new Color(0xCBDFE5));
        }
        if (num == 1) {
            d.setColor(new Color(0xD4E5E3));
        }
        if (num == 2) {
            d.setColor(new Color(0xD6E5DD));
        }
        if (num == 3) {
            d.setColor(new Color(0xC0C4E5));
        }
    }
    @Override
    public void timePassed() {

    }
}
