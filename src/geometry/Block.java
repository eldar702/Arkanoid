package geometry;
/**
 * @author Eldar Shlomi.
 * id 205616634.
 * @version IntelliJ IDEA 2019.3.3
 * @since 2020-04-28 */

import biuoop.DrawSurface;
import gamesetting.GameLevel;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *  Block class.
 *  implements collidable and sprite.
 *  * The class will creating an abstract ball.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Color color;
    private Rectangle rectangle;
    private List<HitListener> hitListeners;

    /**
     * constructor for Block variables.
     * @param rectangle - the lines of the block, exactly like rectangle.
     * @param color - the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * constructor for Block variables.
     * @return rectangle - the rectangle private variable of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
// getters:

    /**
     * setter for getColor private variable.
     * @return color - the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }

    /**
     * getter for rectangle private variable.
     * @return rectangle - the rectangle private variable of the block.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
           //variable declaration:
        double dx =  currentVelocity.getDx();
        double dy =  currentVelocity.getDy();
        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();

        //check if there is a collision with the horizontal lines of the block
        if (((rectangle.getUpperLeft().getX() < collisionX) && (rectangle.getUpperRight().getX() > collisionX))) {
            dy *= -1;
            this.notifyHit(hitter);
        }
        //check if there is a collision with the vertical lines of the block.
        if (((rectangle.getUpperLeft().getY() < collisionY) && (rectangle.getDownLeft().getY() > collisionY))) {
            dx *= -1;
            this.notifyHit(hitter);

            // check if the collision is with one of the rectangle corners.
        } else if (rectangle.getUpperLeft().getX() == collisionX || rectangle.getDownLeft().getX() == collisionX
                || rectangle.getUpperRight().getX() == collisionX || rectangle.getDownRight().getX() == collisionX) {

            dx *= -1;
            dy *= -1;
            this.notifyHit(hitter);
        }
            Velocity velocity = new Velocity(dx, dy);
            return velocity;
        }

    @Override
    public void drawOn(DrawSurface surface) {
        DrawSurface surface1 = surface;
        //variable declaration:
       double height = this.rectangle.getHeight();
        double width = this.rectangle.getWidth();
        double upperLeftX = this.rectangle.getUpperLeft().getX();
        double upperLeftY = this.rectangle.getUpperLeft().getY();

       surface1.setColor(getColor());
       surface1.fillRectangle((int) upperLeftX, (int) upperLeftY, (int) width, (int) height);

       //drawing the black lines that make the blocks's frame.
        surface1.setColor(Color.black);
        surface1.drawRectangle((int) upperLeftX, (int) upperLeftY, (int) width, (int) height);
    }
   @Override
    public void timePassed() {
    }
    /**
     this method make a random color for the block.
     * @return color - the block random color.
     */
    public static Color randomColor() {
        Random helper = new Random();
        int paintR = helper.nextInt(255);
        int paintG = helper.nextInt(255);
        int paintB = helper.nextInt(255);
        Color color = new Color(paintR, paintG, paintB);
        return color;
    }

    /**
     * add a block element to the game.
     * <p>
     * addSprite - this method add the blocks to the sprites list.
     * addCollidable - this method add the blocks to the Collidable list.
     * <p>
     * @param g - the Game variable.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * remove a block element from the game.
     * <p>
     * addSprite - this method remove the block from the sprites list.
     * addCollidable - this method remove the block from the Collidable list.
     * <p>
     * @param game - the Game variable.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * method for notify when being hit to the registered listeners.
     * @param hitter the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}

