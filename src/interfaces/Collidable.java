package interfaces;
/**@author Eldar Shlomi.
 * id 205616634.*/

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 *  Collidable interface.
 *  interface for collidable objeects.
 *  (for now classes Block and Paddle).
 */
public interface Collidable {
    /**
     * getter for the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter the hitter ball.
     * @param collisionPoint - the collision point of the sprite object with the collidable object.
     * @param currentVelocity - the velocity of the collision object.
     * @return the new velocity after the hit.
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}