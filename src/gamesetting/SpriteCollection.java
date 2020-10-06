package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.Collection;

/**
 this class collect info about the sprites which in the game.
 */
public class SpriteCollection {
    //privates:
    private Collection<Sprite> sprites = new ArrayList<>();

    /**
     * adding the sprite objects to the sprite array list.
     * @param s - a sprite variable.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * remove the sprite objects from the sprite array list.
     * @param s - a sprite variable.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * notify to the sprites that time had passed. (by call timePassed() method on all sprites).
     */
    public void notifyAllTimePassed() {
        Collection<Sprite> sp = new ArrayList<Sprite>(this.sprites);
        // Notify all listeners about a hit event by call timePassed() on all sprites.

        for (Sprite s : sp) {
            s.timePassed();
        }
    }
    /**
     * draw all the sprite on the game surface by calling drawOn(d) on all sprites.
     * @param d - the game surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}