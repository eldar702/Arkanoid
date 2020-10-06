package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;


/**
 this class connect all the information about the game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     *add the given collidable to the environment or the game by adding it to the collidable list.
     * @param c - the collidable argument.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     *removing the given collidable from the environment of the game by removing it to the collidable list.
     * @param c the collidable argument.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
    /**
     * this method return a list of the collidables variables.
     * @return the list.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    //constructor

    /**
     * constructor for collidables array list.
     */
    public GameEnvironment() {
        collidables = new ArrayList<Collidable>();
    }

    /**
     * check if the ball collision with an object.
     * if not return null, if yes return the closest intersection point. calculated
     * by the length from the start point of the ball to the collision points.
     *<p>
     * closestIntersectionToStartOfLine - check if the line is intersect with the rectangle.
     * if no return null.Otherwise, return the closest intersection point to the line.
     * distance - use to check, by the shorter length which intersection point
     * represented a collision accrue first.
     *<p>
     * @param trajectory - the ball movement "line".
     * @return helper - the closet intersection point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int i = 0;
        Point collision, collision2 = null;
        CollisionInfo helper = null;
        for (Collidable c : this.collidables) {
            Rectangle rectangle = c.getCollisionRectangle();
            collision = trajectory.closestIntersectionToStartOfLine(rectangle);
            if (collision != null) {
                i++;
                //check if there is only 1 intersection point.
                if (i == 1) {
                    collision2 = collision;
                    helper = new CollisionInfo(c, collision2);
                    //check if there is 2 intersection points.
                } else if (collision.distance(trajectory.start()) < collision2.distance(trajectory.start())) {
                    collision2 = collision;
                    helper = new CollisionInfo(c, collision2);
                }
            }
        }
        if (collision2 != null) {
            return helper;
        }
        return null;
    }
}

