package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import geometry.Point;
import interfaces.Collidable;

/**
 this class save the info about the collision objects.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    //constructor

    /**
     * constructor for CollisionInfo variable.
     * @param collidable - the collidable object.
     * @param collisonPoint - the collision point between a spirte and collidable objects.
     */
    public CollisionInfo(Collidable collidable, Point collisonPoint) {
        this.collidable = collidable;
        this.collisionPoint = collisonPoint;
    }
    /**
     * getter of  the point at which the collision occurs.
     * @return - the point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * getter of the collidable object involved in the collision.
     * @return - the collidable object.
     */
        public Collidable collisionObject() {
        return this.collidable;
    }
}
