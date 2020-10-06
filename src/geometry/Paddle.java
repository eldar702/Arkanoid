package geometry;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamesetting.GameLevel;
import interfaces.Collidable;
import interfaces.Sprite;

import java.awt.Color;

/**
 this class create the paddle of the game.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private int speed;

    /**
     * constructor for the paddle.
     */

    /**
     * constructor for the paddle, Relying on values sizes in advance.
     * @param keyboard - the paddle sensitive keyboard.
     * @param gameWidth - the width of the game screen.
     * @param gameHeight - the height if the game screen.
     * @param paddleWidth - the paddle width.
     * @param paddleHeight the paddle height.
     * @param widthOfB - the border block width.
     * @param speed - the moving speed of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, int gameWidth, int gameHeight,
                  int paddleWidth, int paddleHeight, int widthOfB, int speed) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(gameWidth / 2 - (paddleWidth / 2), gameHeight - widthOfB - paddleHeight,
                paddleWidth, paddleHeight);
        this.color = Color.yellow;
        this.speed = speed;
    }

    /**
     * this method Responsible for the shift to the left of the paddle.
     */
    public void moveLeft() {
        //variable declaration:
        double x = rectangle.getUpperLeft().getX();
        double y = rectangle.getUpperLeft().getY();
        double width = rectangle.getWidth(), height = rectangle.getHeight();
        double widthOfGui = GameLevel.getGameWidth();
        //the border rectangle's width.
        double widthOfBorder = widthOfGui / 40;
        // condition for making the paddle border from right he can't pass.
        if (rectangle.getUpperLeft().getX() - speed < widthOfBorder) {
            return;
        }
        this.rectangle = new Rectangle(x - speed, y, width, height);
    }

    /**
     * this method Responsible for the shift to the right of the paddle.
     */
    public void moveRight() {
        //variable declaration:
        double x = rectangle.getUpperLeft().getX();
        double y = rectangle.getUpperLeft().getY();
        double width = rectangle.getWidth(), height = rectangle.getHeight();
        double widthOfGui = GameLevel.getGameWidth();
        //the border rectangle's width.
        double widthOfBorder = widthOfGui / 40;
        // condition for making the paddle border from right he can't pass.
        if (rectangle.getUpperRight().getX() + speed > (widthOfGui - widthOfBorder)) {
            //rectangle.getUpperLeft().getX() + rectangle.getWidth() + dx > (widthOfGui - widthOfBorder)
            return;
        }
        this.rectangle = new Rectangle(x + speed, y, width, height);
    }

    /**
     * this method Responsible for shift the paddle to the middle of the screen.
     */
    public void moveToMiddle() {
        //variable declaration:
        double y = rectangle.getUpperLeft().getY();
        double width = rectangle.getWidth(), height = rectangle.getHeight();
        double widthOfGui = GameLevel.getGameWidth();
        this.rectangle = new Rectangle(widthOfGui / 2 - (width / 2), y, width, height);
    }
    /**
     * the change by the time passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }
    /**
     this method creating the paddle moving effect by drawing him
     (and by time - drawing him in different position).
     * <p>
     * fillRectangle - filling the rectangle with the set color.
     * drawRectangle - drawing the border lines of the paddle.
     * <p>
     * @param d - the surface of the game.
     */

    public void drawOn(DrawSurface d) {
        double x = rectangle.getUpperLeft().getX();
        double y = rectangle.getUpperLeft().getY();
        double width = rectangle.getWidth(), height = rectangle.getHeight();
        //drawing the paddle on the surface.
        d.setColor(Color.yellow);
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);

        //drawing the black lines that make the ball's frame.
        d.setColor(Color.black);
        d.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     *  this method return the rectangle which the sprite collision with.
     * @return rectangle - the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

@Override
    public Velocity hit(Ball ball, Point collisionPoint, Velocity currentVelocity) {
        //velocity variables:
        Velocity velocity = currentVelocity;
        double angle, speedOf = 8;
        double dx = velocity.getDx(), dy = velocity.getDy();
        //variable represent in which part of the paddle the ball collision.
        int blockParts = 5;
        int sizeOfPart = (int) rectangle.getWidth() / blockParts;
        //represent on which part of the block the ball intersected with the paddle
        double partOnBlock;
        Line collisionPointL = new Line(collisionPoint, collisionPoint);
        //check if there is a collision with the upper lines of the paddle.
        if (rectangle.getUpperLine().isIntersecting(collisionPointL)) {
            // the part number in paddle which collision occur = (x of the collision
            // part - x of the start point of the paddle) / each blocks part size
            partOnBlock =  (collisionPointL.getStart().getX() - rectangle.getUpperLeft().getX()) / sizeOfPart;
            angle = 300 + (partOnBlock * 30);
            velocity = Velocity.fromAngleAndSpeed(angle, speedOf);
            //check if there is a collision with the right or left lines of the paddle.
        } else if (rectangle.getLeftLine().isIntersecting(collisionPointL)
                || rectangle.getRightLine().isIntersecting(collisionPointL)) {
            dx *= -1;
            velocity = new Velocity(dx, dy);
        }
        return velocity;
    }

    /**
     * this method adding the sprites and collidables objects to the game.
     * @param g - the game variable.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}


