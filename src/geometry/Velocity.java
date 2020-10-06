package geometry;

/**@author Eldar Shlomi.
 * id 205616634.*/

public class Velocity {
    private double dx;
    private double dy;
    /**
     * constructors method for Velocity class.
     * @param dx - number which represent an edge of triangle.
     * @param dy   - number which represent an edge of triangle.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * this method convert from angle and speed values
     * of the velocity of the ball to dx and dy.
     * @param angle - the angle of the movement of the ball.
     * @param speed - the speed of the movement of the ball.
     * @return Velocity - the converted velocity, represented by dx and dy
     */
   public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //argument deceleration
       double dx = 0;
       double dy = 0;
       double rad = Math.toRadians(angle);
       // consideration in  case the angle is more than 360 degrees.
       if (angle >= 360) {
           angle %= 360;
       }
       //check if the angle is not 0, 90, 180, 270
        if ((angle == 0) || (angle % 90 == 0)) {
            if (angle == 0) {
                dx = 0;
                dy = -speed;

            } else if (angle == 90) {
                dx = speed;
                dy = 0;

            } else if (angle == 180) {
                dx = 0;
                dy = speed;

            } else if (angle == 270) {
                dx = -speed;
                dy = 0;
            }
            return new Velocity(dx, dy);
        }
        dy = -1 * Math.cos(rad) * speed;
        dx = Math.sin(rad) * speed;

     return new Velocity(dx, dy);
   }
    /**
     * this method change the ball position by adding him the
     * dx and dy to his center point and by that allowing the
     * illusion of moving ball.
     * @param p - the "old" point.
     * @return Point - return the new point.
     */

    public Point applyToPoint(Point p) {
        double x = p.getX() + dx;
        double y = p.getY() + dy;
        return new Point(x, y);
    }

    /**
     * The method return the x coordination.
     * @return -return the x coordination of a point
     */
    public double getDx() {
        return (dx);
    }
    /**
     * The method return the y coordination.
     * @return -return the y coordination of a point
     */
    public double getDy() {
        return (dy);
    }
}

