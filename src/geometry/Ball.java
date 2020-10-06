package geometry;

/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import gamesetting.GameLevel;
import gamesetting.CollisionInfo;
import gamesetting.GameEnvironment;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;

/**
 * Ball class.
 * The class will creating an abstract ball.
 */
public class Ball implements Sprite {

    private Point center;
    private int r;
    private Color color;
    private Velocity v;
    private GameEnvironment gameE;
    /** constructor.
     * @param center - the center coordination of the ball.
     * @param r - the radios .
     * @param color - the color of the ball.*/
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameE = null;
    }
    /** constructor.
     * @param x - the x coordination of the center of the ball.
     * @param y - the y coordination of the center of the ball.
     * @param r - the radios.
     * @param color - the color of the ball.*/
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.gameE = null;
    }

    /**
     * The method implements the velocity of the ball.
     * @param ve - represent the velocity.
     */

    public void setVelocity(Velocity ve) {
        this.v = ve;
    }
    /**
     * The method implements the velocity of the ball.
     * @param dx - represent the dx of the velocity.
     * @param dy - represent the dy of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * this method is an accessors for getting the x coordination of the center of the ball.
     * @return this.center.getX - the x coordination of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * this method is an accessors for getting the y coordination of the center of the ball.
     * @return this.center.getY - the x coordination of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * this method use for getting the radios of the ball.
     * @return r - the radios of the ball.
     */
    public int getSize() {
        return r;
    }
    /**
     * this method use for getting the color of the ball.
     * @return r - the radios of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * this method use for getting the velocity of the ball.
     * @return v - the velocity.
     */
    public Velocity getVelocity() {
        return (v);
    }
    /**
     * this method use for getting center ball coordination.
     * @return v - the velocity.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * getter to GameEnvironment private variable.
     * @return game
     */
    public GameEnvironment getGameE() {
        return gameE;
    }

    /**
     * setter for GameEnvironment private variable.
     * @param gameEnvironment - store the gameE variable.
     */
    public void setGameE(GameEnvironment gameEnvironment) {
        this.gameE = gameEnvironment;
    }

    /**
     * this method draw ball on the surface board.
     * @param surface - the board.
     */

    public void drawOn(DrawSurface surface) {
        //drawing the ball on the surface.
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());

        //drawing the black lines that make the ball's frame.
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }
/**
 this method make the movement of the ball
 * <p>
 * getVelocity - this method send the velocity of the ball to:
 * applyToPoint - make an new ball by taking a ball coordination and
 *add him dx and dy, and by that make the illusion of the moving ball.
 * <p>
 */

    public void moveOneStep() {
        //vbbariable declaration:
        double epsilon = 1.005;
        Line trajectory = new Line(this.center.getX(), this.center.getY(), this.center.getX()
                + epsilon * this.v.getDx(), this.center.getY() +  epsilon * this.v.getDy());
        CollisionInfo firstCollision = gameE.getClosestCollision((trajectory));
        if (firstCollision == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else if (firstCollision != null) {
            Point point = firstCollision.collisionPoint();
            Collidable collidable = firstCollision.collisionObject();
            this.v = collidable.hit(this, point, v);
        }
    }
    /**
     overloading of moveOneStep which appears above.
     this method make the movement of the ball.
     * <p>
     * getVelocity - this method send the velocity of the ball to:
     * applyToPoint - make an new ball by taking a ball coordination and
     *add him dx and dy, and by that make the illusion of the moving ball.
     * <p>
     * @param d - the surface board.
     */
    public void moveOneStep(DrawSurface d) {
        //variable deceleration
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        double dx = this.v.getDx();
        double dy = this.v.getDy();

        //deceleration of the bounds of the surface
        double width = d.getWidth();
        double height = d.getHeight();
//

        //present the right border of the surface
        if (x > width - r) {
            this.setVelocity(-Math.abs(dx), dy);
        }
        //represent the left border of the surface
        if (x < 0 + r) {
            this.setVelocity(Math.abs(dx), dy);
        }
        //represent the lower border of the surface
        if (y > height - r) {
            this.setVelocity(dx, -Math.abs(dy));
        }
        //represent the upper border ot the surface
        if (y < 0 + r) {
            this.setVelocity(dx, Math.abs(dy));
        }
          this.center = this.getVelocity().applyToPoint(this.center);
          }

    /**
     * this method create the effect of the moving of the ball by using the method moveOneStep.
     *<p>
     * moveOneStep - the method which creating the allusion of the moving.
     *<p>
     */
    public void timePassed() {
    moveOneStep();
    }

    /**
     * add the ball to the game.
     * <p>
     * addSprite - setter which add Ball argument to sprite.
     * <p>
     * @param g = represent the game variable.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * this method remove the ball from the game.
     * @param game the game variable.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

