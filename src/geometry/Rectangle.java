package geometry;

/**@author Eldar Shlomi.
 * id 205616634.*/

import java.util.ArrayList;
import java.util.List;

 /**
 this class responsible for the rectangle shape objects.
 */
public class Rectangle {
    // private variable deceleration:
    private Point upperLeft, upperRight, downLeft, downRight;
    private Line upperLine, downLine, leftLine, rightLine;
    private double width;
    private double height;

    /**
     * constructor for a new rectangle with location and width/height.
     * @param upperLeft - the upper left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        //creating the 4 corner of the rectangle.
        this.upperLeft = upperLeft;
        this.downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        this.upperRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        this.downRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        this.width = width;
        this.height = height;

        //creating the rectangle by creating 4 lines.
        this.upperLine = new Line(upperLeft, upperRight);
        this.downLine = new Line(downLeft, downRight);
        this.leftLine = new Line(upperLeft, downLeft);
        this.rightLine = new Line(upperRight, downRight);

    }
    /**
     * constructor for a new rectangle with location and width/height.
     * @param x - the x coordinate of the upper left point of the rectangle.
     * @param y - the y coordinate of the upper left point of the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
        this.upperRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        this.downRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        this.width = width;
        this.height = height;

        //creating the rectangle by creating 4 lines.
        this.upperLine = new Line(upperLeft, upperRight);
        this.downLine = new Line(downLeft, downRight);
        this.leftLine = new Line(upperLeft, downLeft);
        this.rightLine = new Line(upperRight, downRight);

    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line - the specified line.
     * @return - the list which contain the intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //variable deceleration:
        List<Point> interPointList = new ArrayList<>();
        // checking the intersection point of the lines with the rectangle lines.
        Point interPointUp = this.upperLine.intersectionWith(line);
        Point interPointDown = this.downLine.intersectionWith(line);
        Point interPointLeft = this.leftLine.intersectionWith(line);
        Point interPointRight = this.rightLine.intersectionWith(line);
        // if there is intersection points between the lines and the
        // rectangle lines, add them to the list.

        //intersection point between the line and the upper rectangle's side
        if (interPointUp != null) {
            interPointList.add(interPointUp);
        }
        //intersection point between the line and the lower rectangle's side
        if (interPointDown != null) {
            interPointList.add(interPointDown);
        }
        //intersection point between the line and the left rectangle's side
        if (interPointLeft != null) {
            interPointList.add(interPointLeft);
        }
        //intersection point between the line and the right rectangle's side
        if (interPointRight != null) {
            interPointList.add(interPointRight);
        }
        return interPointList;
    }

//          getters.
    /**
     * getter for the upper left point of the rectangle.
     * @return upperLeft - the upper left point.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * getter for the upper right point of the rectangle.
     * @return upperRight - the upper right point.
     */
    public Point getUpperRight() {
        return upperRight;
    }
    /**
     * getter for the down left point of the rectangle.
     * @return downLeft - the down left point.
     */
    public Point getDownLeft() {
        return downLeft;
    }
    /**
     * getter for the upper right point of the rectangle.
     * @return downRight - the upper right point.
     */
    public Point getDownRight() {
        return downRight;
    }
    /**
     * getter for the upper line of the rectangle.
     * @return upperLine - the upper line of the rectangle.
     */
    public Line getUpperLine() {
        return upperLine;
    }
    /**
     * getter for the upper right point of the rectangle.
     * @return downLine - the upper line of the rectangle.
     */
    public Line getDownLine() {
        return downLine;
    }
    /**
     * getter for the left line of the rectangle.
     * @return leftLine - the line of the rectangle.
     */
    public Line getLeftLine() {
        return leftLine;
    }
    /**
     * getter for the right line of the rectangle.
     * @return rightLine - the right right point.
     */
    public Line getRightLine() {
        return rightLine;
    }
    /**
     * getter for the width size of of the rectangle.
     * @return width - the width.
     */
    public double getWidth() {
        return width;
    }
    /**
     * getter for the height of the rectangle.
     * @return height - the height.
     */
    public double getHeight() {
        return height;
    }

}
