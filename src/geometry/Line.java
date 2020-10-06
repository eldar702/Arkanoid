package geometry;
/**@author Eldar Shlomi.
 * id 205616634.*/

import java.util.List;

/**
 * class for lines variables class.
 */
public class Line {

    private Point start;
    private Point end;
    private Point middle;

    /**
     * The method is constructors that build a Line argument.
     *
     * @param start - number which represent an edge of triangle.
     * @param end   - number which represent an edge of triangle.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * The method is constructors that build a Line argument.
     *
     * @param x1 - represent the x coordination of the start point of the line.
     * @param x2 - represent the x coordination of the end point of the line.
     * @param y1 - represent the y coordination of the start point of the line.
     * @param y2 - represent the y coordination of the end point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * The function will determine the length of a lines, by checking the distance
     * between the 2 edge points.
     * @return this.start.distance(end) - the method return the length of the line. */

    public double length() {
        return (this.start.distance(end));
    }
    /**
     * The method will determine the length of a lines, by checking the distance
     * between the 2 edge points.
     * @return new Point - the method return new point which she created. the new
     * point represent the middle of the given segment line. */

    public Point middle() {
        middle = new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
        return middle;
    }
    /**
     * The method check what is the start point of a segment line.
     * @return start - the method return the values of the start point of a segment line */
    public Point start() {
        return (start);
    }

    /**
     *  getter for the start point of the line.
     * @return the start point.
     */
    public Point getStart() {
        return start;
    }
    /**
     * The function will determine the orientation between 3 given dots - clockwise,
     * counter clockwise and colinear
     * (a helper method to the method "intersection").
     * @param  p which represents one point from the 3 we want to check
     * @param  q which represents one point from the 3 we want to check
     * @param  s which represents one point from the 3 we want to check
     * @return the method return int value which represent the different cases
     * of the orientation between the points. */

    static double orientationHelper(Point p, Point q, Point s) {
        //check the Orientation of 3 ordered points
        double k = (q.getY() - p.getY()) * (s.getX() - q.getX()) - (q.getX() - p.getX()) * (s.getY() - q.getY());

        if (k == 0) {
            return 0; // the point are on the same collinear line
        }
        if (k > 0) {
            return 1; //this 3 dots are clockwise
        }
        //if k < 0
        return 2; // counter clockwise
    }
    /**
     * The method will determine if the Given three points p, q, s lies on line segment 'ps'
     * (a helper method to the method "intersection").
     * @param  p which represents one point from the 3 we want to check
     * @param  q which represents one point from the 3 we want to check
     * @param  s which represents one point from the 3 we want to check
     * @return the method return a point. */
    static boolean segmentHelper(Point p, Point q, Point s) {
        boolean helper = (q.getX() <= Math.max(p.getX(), s.getX()) && q.getX() >= Math.min(p.getX(), s.getX())
                && q.getY() <= Math.max(p.getY(), s.getY()) && q.getY() >= Math.min(p.getY(), s.getY()));
        return helper;
    }

    /**
     * The method will check the intersection point between 2 segment lines.
     * <p>
     * the method first check if there is a intersection point (using the method "intersection" which
     * appears above. if yes the method will continues to check what is the intersection point,
     * distinguish between different kind of lines - horizon lines, verticals lines, lines
     * that are just a point and "normal" ones.
     * <p>
     * @param other that represents the line that we want to check (like described above)
     * @return the function return value is Point - the intersection point */
    public Point intersectionWith(Line other) {
        // variable declaration
        Point interPoint;
        double l1x1 = this.start.getX();
        double l1x2 = this.end.getX();
        double l1y1 = this.start.getY();
        double l1y2 = this.end.getY();
        double l2x1 = other.start.getX();
        double l2x2 = other.end.getX();
        double l2y1 = other.start.getY();
        double l2y2 = other.end.getY();
        double m1;
        double m2;
        double c1;
        double c2;
        double intersecX;
        double intersecY;
        // check if their is no  other line
        if (other == null) {
            return null;
        }
        // check if their is intersection (attlist 1 point)
        if (!this.isIntersecting(other)) {
            return (null);
        }
        //check if the lines are points
        if ((this.dotHelper(other)) != null) {
            interPoint = this.dotHelper(other);
            return interPoint;
        }
        // check if the 2 lines is the same
        if (this.equals(other)) {
            return (null);
        }

        int vertical = checkVerticalHorizon(l1x1, l1x2, l2x1, l2x2);
        int horizon = checkVerticalHorizon(l1y1, l1y2, l2y1, l2y2);
        //check if the lines are vertical or horizontal.
        if (vertical > 0 || horizon > 0) {
            interPoint = this.helperVerticalHorizon2(other, vertical, horizon);
            return (interPoint);
        }
        m1 = slopeHelper(l1x1, l1x2, l1y1, l1y2);
        c1 = cHelper(l1x1, l1y1, m1);
        m2 = slopeHelper(l2x1, l2x2, l2y1, l2y2);
        c2 = cHelper(l2x1, l2y1, m2);
        intersecX = (c2 - c1) / (m1 - m2);
        //finding the y of the intersection of the two lines
        intersecY = intersecX * m1 + c1;
        interPoint = new Point(intersecX, intersecY);
        return interPoint;
    }

    /**
     * The function will determine if 2 lines are intersecting or not.
     * <p>
     * The method will use another more 2 methods, for checking
     * if 2 given lines are intersection:
     * orientationHelper: the method will determine the orientation between 3 given
     * dots - clockwise, counter clockwise or collinear.
     *  onSegment: The method will determine if the Given three points
     *  lies on the same line segment.
     * <p>
     * @param other - the second line.
     * @return the method return boolean value - true if there is an intersection point
     * and false otherwise. */

    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        //check all the 4 options of combination of 3 dots's orientation of the two lines.
        double orient1 = orientationHelper(this.start, this.end, other.start);
        double orient2 = orientationHelper(this.start, this.end, other.end);
        double orient3 = orientationHelper(other.start, other.end, this.start);
        double orient4 = orientationHelper(other.start, other.end, this.end);

// General case
        if (orient3 != orient4 && orient1 != orient2) {
            return true;
        }
        // Special Cases
        // this.start, this.end and other.start are collinear and other.start lies on segment this.start
        if (orient1 == 0 && segmentHelper(this.start, other.start, this.end)) {
            return true;
        }

        // this.start, this.end and other.end are collinear and other.end lies on segment this.start this.end
        if (orient2 == 0 && segmentHelper(this.start, other.end, this.end)) {
            return true;
        }

        // other.start, other.end and this.start are colinear and this.start lies on segment other.startother.end
        if (orient3 == 0 && segmentHelper(other.start, this.start, other.end)) {
            return true;
        }

        // other.start, other.end and this.end are colinear and this.end lies on segment other.startother.end
        if (orient4 == 0 && segmentHelper(other.start, this.end, other.end)) {
            return true;
        }

        return false; // there is no intersection, and therefore return false
    }
    /**
     * The method will determine if the lines are equals by checking if
     * they have the same start and end points.
     * @param other  which represents the seccond point checked
     * @return true uf they equals and false otherwise */
    //check if the lines are equals
    public boolean equals(Line other) {
        //check if they have the same points of start and end
        if ((this.start.getX() == other.start.getX()) && (this.start.getY() == other.start.getY())) {
            if (this.end.getX() == other.end.getX() && this.end.getY() == other.end.getY()) {
                return true;
            }
        }
        ;
        return false;
    }

    /**
     * The method will determine if one (or 2) of the given lines are points
     * (by checking if the end and start point of him are the same).
     * @param other  which represents the line point checked
     * @return true if they equals and false otherwise */
    public Point dotHelper(Line other) {
        // variable declaration
        Point interPoint;
        double l1x1 = this.start.getX();
        double l1x2 = this.end.getX();
        double l1y1 = this.start.getY();
        double l1y2 = this.end.getY();
        double l2x1 = other.start.getX();
        double l2x2 = other.end.getX();
        double l2y1 = other.start.getY();
        double l2y2 = other.end.getY();
        //check if there attlist 1 point. if yes continues to
        // the other checks, if no return

        if (!((l1x1 == l1x2 && l1y1 == l1y2) || (l2x1 == l2x2 && l2y1 == l2y2))) {
            return null;
        }
        //Check which one of the lines is a point.
        //if no only line 2 is a point.
        if ((l1x1 == l1x2 && l1y1 == l1y2)) {
            // if no i know that just line 1 (this) is a point.
            // if yes i know that both of the lines are points.
            if ((l2x1 == l2x2 && l2y1 == l2y2)) {
                interPoint = new Point(l2x2, l2y2);
                return interPoint;
            }
            // line 1 is a point. line 2 is not a point.
            interPoint = new Point(l1x1, l1y1);
            return interPoint;
        }
        // only line 2 is a point.
        interPoint = new Point(l2x1, l2y1);
        return interPoint;
    }

    /**
     * The method will determine if one (or 2) of the given.
     * lines are vertical/horizon check only one thing from
     * the 2 options, dependent if the argument which given is y/x.
     * @param l1x1  the x/y coordinate of the start point of the first line.
     * @param l1x2  the x/y coordinate of the end point of the first line.
     * @param l2x1  the x/y coordinate of the start point of the first line.
     * @param l2x2  the x/y coordinate of the end point of the first line.
     * @return an int value which represent a specific case for the lines.
     **/
    public int checkVerticalHorizon(double l1x1, double l1x2, double l2x1, double l2x2) {
        //check if there attlist 1 vertical/horizon line. if yes continues to
        // the other checks, if no return
        if (!((l1x1 == l1x2) || (l2x1 == l2x2))) {

            return 0;
        }
        //Check which one of the lines is vertical/horizon.
        //if no only line 2 is vertical/horizon.
        if (l1x1 == l1x2) {
            // if no i know that just line 1 (this) is vertical/horizon.
            // if yes i know that both of the lines are verticals/horizon.
            if ((l2x1 == l2x2)) {
                return 3;
            }
            // line 1 is a not vertical/horizon. line 2 is not a vertical/horizon.
            return 1;
        }
        // only line 2 is a vertical.
        return 2;
    }

    /**
     * The method will determine if one (or 2) of the given
     * lines are one of the special cases:
     * vertical, horizontal, or a point.
     * @param other represents the seconds line.
     * @param vertical  which represents the line point checked
     * @param horizon  which represents the line point checked
     * @return interPoint - the intersection point of the 2 lines
     * (or null otherwise).
     **/
    public Point helperVerticalHorizon2(Line other, int vertical, int horizon) {
        // variable declaration
        Point interPoint;
        double l1x1 = this.start.getX(), l1x2 = this.end.getX();
        double l1y1 = this.start.getY(), l1y2 = this.end.getY();
        double l2x1 = other.start.getX(), l2x2 = other.end.getX();
        double l2y1 = other.start.getY(), l2y2 = other.end.getY();
        double m1, m2;
        double c1, c2;
        double intersecX, intersecY;
        //check if the lines are verticals
        switch (vertical) {
            case 0: // both of the lines are not vertical
                break;
            case 1: // only line 1 is a vertical
                m2 = slopeHelper(l2x1, l2x2, l2y1, l2y2);
                c2 = cHelper(l2x1, l2y1, m2);
                intersecX = l1x1;
                intersecY = m2 * l1x1 + c2;
                interPoint = new Point(intersecX, intersecY);
                return interPoint;
            case 2: // only line 2 is a vertical
                m1 = slopeHelper(l1x1, l1x2, l1y1, l1y2);
                c1 = cHelper(l1x1, l1y1, m1);
                intersecX = l2x1;
                intersecY = m1 * l2x1 + c1;
                interPoint = new Point(intersecX, intersecY);
                return interPoint;
            case 3: // both of the lines are vertical
                //check if they have 1 intersection point or more.
                //check if they have common start or end point
                int yesCommon = samePointHelper(other);
                //one line is contained in the other and they have infiny intersection points
                if (yesCommon == 0) {
                    return null;
                }
                // the lines has same edge points (attlist 1)
                if (yesCommon >= 1) {
                    //yes if 1 point:
                    if (this.length() >= other.length()) {
                        //if yes:
                        //line 2 (other) is contained in line 1 - infinity intersection points.
                        if ((Math.max(l1y1, l1y2) >= Math.max(l2y1, l2y2))
                                && Math.min(l1y1, l1y2) <= Math.min(l2y1, l2y2)) {
                            return null;
                        }
                    }
                    //if yes:
                    //line 1 (other) is contained in line 2 - infinity intersection points.
                    if (this.length() <= other.length()) {
                        if ((Math.max(l1y1, l1y2) <= Math.max(l2y1, l2y2))
                                && Math.min(l1y1, l1y2) >= Math.min(l2y1, l2y2)) {
                            return null;
                        }
                    }
                    //for point's cases
                    if (yesCommon == 1 || yesCommon == 2) {
                        intersecX = l1x1;
                        intersecY = l1y1;
                        interPoint = new Point(intersecX, intersecY);
                        return (interPoint);
                    }
                    //for point's cases
                    if (yesCommon == 3 || yesCommon == 4) {
                        intersecX = l1x2;
                        intersecY = l1y2;
                        interPoint = new Point(intersecX, intersecY);
                        return (interPoint);
                    }
                }
            default:
        } //check if the lines are horizontal
        switch (horizon) {
            case 0: // both of the lines are not horizontal
                break;
            case 1: // only line 1 is a horizontal //////////////////////
                m2 = slopeHelper(l2x1, l2x2, l2y1, l2y2);
                c2 = cHelper(l2x1, l2y1, m2);
                intersecY = l1y1;
                intersecX = (l1y1 - c2) / m2;
                interPoint = new Point(intersecX, intersecY);
                return interPoint;
            case 2: // only line 2 is a horizontal ////////////// same as 1
                m1 = slopeHelper(l1x1, l1x2, l1y1, l1y2);
                c1 = cHelper(l1x1, l1y1, m1);
                intersecY = l2y1;
                intersecX = (l2y1 - c1) / m1;
                interPoint = new Point(intersecX, intersecY);
                return interPoint;
            case 3: // both of the lines are horizontal
                //check if they have 1 intersection point or more.
                //check if they have common start or end point
                int yesCommon = samePointHelper(other);
                //one line is contained in the other and they have infiny intersection points
                if (yesCommon == 0) {
                    return null;
                }
                // the lines has same edge points (attlist 1)
                if (yesCommon >= 1) {
                    //yes if 1 point:
                    if (this.length() >= other.length()) {
                        //if yes:
                        //line 2 (other) is contained in line 1 - infinity intersection points.
                        if ((Math.max(l1x1, l1x2) >= Math.max(l2x1, l2x2))
                                && Math.min(l1x1, l1x2) <= Math.min(l2x1, l2x2)) {
                            return null;
                        }
                    }
                    //if yes:
                    //line 1 (this) is contained in line 2 - infinity intersection points.
                    if (this.length() <= other.length()) {
                        if ((Math.max(l1x1, l1x2) <= Math.max(l2x1, l2x2))
                                && Math.min(l1x1, l1x2) >= Math.min(l2x1, l2x2)) {
                            return null;
                        }
                    }
                    if (yesCommon == 1 || yesCommon == 2) {
                        intersecX = l1x1;
                        intersecY = l1y1;
                        interPoint = new Point(intersecX, intersecY);
                        return (interPoint);
                    }
                    if (yesCommon == 3 || yesCommon == 4) {
                        intersecX = l1x2;
                        intersecY = l1y2;
                        interPoint = new Point(intersecX, intersecY);
                        return (interPoint);
                    }
                }
            default:
                break;
        }
        //check if one line is vertical and the other horizontal
        if (vertical >= 1 && horizon >= 1) {
            //If yes line 1 is vertical and line 2 is horizontal
            if (vertical == 1) {
                intersecX = l1x1;
                intersecY = l2y1;
            }
            // If yes line 1 is horizontal and line 2 is vertical
            intersecX = l2x1;
            intersecY = l1y1;
        }
        return null;
    }
    /**
     * The method will determine if the point have the same start/end point.
     * @param other the second line.
     * @return an int number represent different cases
     * (specified above every one of them)
     **/
    public int samePointHelper(Line other) {
        double l1x1 = this.start.getX(), l1x2 = this.end.getX();
        double l1y1 = this.start.getY(), l1y2 = this.end.getY();
        double l2x1 = other.start.getX(), l2x2 = other.end.getX();
        double l2y1 = other.start.getY(), l2y2 = other.end.getY();
        // check if they a part of the same line and have only one intersection point
        if (((l1x1 == l2x1) && (l1y1 == l2y1)) || ((l1x1 == l2x2) && (l1y1 == l2y2))
                || ((l2x1 == l1x2) && (l2y1 == l1y2)) || ((l2x2 == l1x2) && (l2y2 == l1y2))) {

            // intersection point: l1start == l2start
            if ((l1x1 == l2x1) && (l1y1 == l2y1)) {
                return 1;
            }
            // intersection point: l1start == l2end
            if ((l1x1 == l2x2) && (l1y1 == l2y2)) {
                return 2;
            }
            // intersection point: l2start == l1end
            if ((l2x1 == l1x2) && (l2y1 == l1y2)) {
                return 3;
            }
            // intersection point: l2end == l1end
            if ((l2x2 == l1x2) && (l2y2 == l1y2)) {
                return 4;
            }
        }
        return 0;
    }
    /**
     * The method will calculate the slope of a given line.
     * @param x1 : the x coordination of the start point of the line.
     * @param x2 : the x coordination of the end point of the line.
     * @param y1 : the y coordination of the start point of the line.
     * @param y2 : the y coordination of the end point of the line.
     * @return the slope (calculate by the equation (y1-y2) / (x1-x2)
     **/
    public double slopeHelper(double x1, double x2, double y1, double y2) {
        return ((y1 - y2) / (x1 - x2));
    }
    /**
     * The method will calculate the c of given line (y=mx + C).
     * @param x : the x coordination of the checked line.
     * @param y : the y coordination of the checked line.
     * @param m : the slope of the checked line.
     * @return the slope (calculate by the equation (y - (m * x)
     **/
    public double cHelper(double x, double y, double m) {
        return (y - (m * x));
    }

    //

    /**
     * this method check if the line is intersect with the rectangle. if no
     * return null.Otherwise, return the closest intersection point to the
     * line (if there is 2, if there is only 1 - this point return).
     *<p>
     * distance - use this method to check the distance between the different intersection
     * points, to calculate which one is closer.
     *<p>
     * @param rect - the rectangle which checked if had been clashed.
     * @return point - the point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line helper = new Line(this.start, this.end);
        int numOfElements = rect.intersectionPoints(helper).size();
        List<Point> list = rect.intersectionPoints(helper);
        // get in if there is 0 intersection point between the line and the rectangle.
        if (numOfElements == 0) {
            return null;
            //there is 1 intersection point between the line and the rectangle.
        } else if (numOfElements == 1) {
            return list.get(0);
        }
        //there is 2 intersection point2 between the line and the rectangle.
        //check which one of the intersection points is closer to the start point of the rectangle.
        if (helper.start.distance(list.get(0)) < helper.start.distance(list.get(1))) {
            return list.get(0);
        } else {
            return list.get(1);
        }
    }
}
