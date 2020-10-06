package geometry;
/**@author Eldar Shlomi.
 * id 205616634.*/

public class Point {
    private double x;
    private double y;

    /**
     * this method is constructor that creating Point argument.
     *
     * @param x - variable of the x value of the point.
     * @param y - variable of the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * The method check the distance between 2 points.
     * @param other - the coordinate of the other point.
     * @return - return the distance of this point to the other point
      */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double distance = (Math.sqrt(((x - other.x) * (x - other.x)) + ((y - other.y) * (y - other.y))));
        return distance;
    }
    /**
     * The method return true is the points are equal, false otherwise.
     * @param other - the coordinate of the other point.
     * @return -return true is the points are equal, false otherwise
     * 3 edged of right angled triangle or false if not. */
    public boolean equals(Point other) {
        return x == other.x && y == other.y;
    }
    /**
     * The method return the x coordination.
     * @return -return the x coordination of a point
     */
    public double getX() {
        return (x);
    }
    /**
     * The method return the y coordination.
     * @return -return the y coordination of a point
     */
    public double getY() {
        return (y);
    }
}
