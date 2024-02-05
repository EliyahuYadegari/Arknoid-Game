package geometry;

/**
 * this class represents a line.
 */
public class Line {
    private Point start;
    private Point end;

    // constructors

    /**
     * this constructor creates a line from two given points.
     *
     * @param start line's start point
     * @param end   line's end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * this constructor creates a line from x,y coordinates for start point and an end point.
     *
     * @param x   start's x-coordinate
     * @param y   start's y-coordinate
     * @param end end point
     */
    public Line(double x, double y, Point end) {
        this.start = new Point(x, y);
        this.end = end;
    }

    /**
     * this constructor creates a line from x, y coordinates.
     *
     * @param x1 x coordinate for start point
     * @param y1 y coordinate for start point
     * @param x2 x coordinate for end point
     * @param y2 y coordinate for end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * this methode calculates the line's length.
     * using the distance between start and end point
     *
     * @return length of the line
     */
    public double length() {
        return (this.start.distance(this.end));
    }

    /**
     * this methode finds the middle point of the line.
     * using average between start and end coordinates
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }

    /**
     * this methode allows access to the start point of the line.
     *
     * @return start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * this methode allows access to the end point of the line.
     *
     * @return end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * this methode calculates the line's slop.
     *
     * @return line's slop
     */
    public double slop() {
        return ((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()));
    }

    /**
     * this methode finds the line intersection point with y.
     *
     * @return intersection point with y
     */
    public double intersectionWithY() {
        return this.start.getY() - (this.slop() * this.start.getX());
    }

    /**
     * this methode finds if a potential intersection point is on the line.
     *
     * @param some the intersection point we check
     * @return true is the point on the line, false if it is not
     */
    public boolean includesPoint(Point some) {
        // case the line is a point, and it is not equal to some point.
        if (Math.abs(this.start.getX() - this.end.getX()) <= 1E-10) {
            if (this.start.getX() != some.getX()) {
                return false;
            }
            // checks if the x-coordinate and y-coordinate of some point is on the segment.
            if (this.inRange(some)) {
                return true;
            }
        }
        // checks if the point is on the line according to the line's equation.
        if (Math.abs(some.getY() - ((this.slop() * some.getX()) + this.intersectionWithY())) <= 1E-10) {
            return this.inRange(some);
        }
        return false;
    }

    /**
     * this methode checks if a point is in thr range of the segment.
     * the methode checks every case of the range Symmetrically:
     * if start smaller than end or the opposite for each line.
     *
     * @param some the point we check
     * @return true is the point in the range, false otherwise
     */
    public boolean inRange(Point some) {
        if ((this.start.getX() <= some.getX()) && (some.getX() <= this.end.getX())) {
            if ((this.start.getY() <= some.getY()) && (some.getY() <= this.end.getY())) {
                return true;
            }
        }
        if ((this.end.getX() <= some.getX()) && (some.getX() <= this.start.getX())) {
            if ((this.start.getY() <= some.getY()) && (some.getY() <= this.end.getY())) {
                return true;
            }
        }
        if ((this.start.getX() <= some.getX()) && (some.getX() <= this.end.getX())) {
            if ((this.end.getY() <= some.getY()) && (some.getY() <= this.start.getY())) {
                return true;
            }
        }
        if ((this.end.getX() <= some.getX()) && (some.getX() <= this.start.getX())) {
            return (this.end.getY() <= some.getY()) && (some.getY() <= this.start.getY());
        }
        return false;
    }

    /**
     * this methode checks if the line intersects with other line.
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {

        // case we got the same line.
        if (this.equals(other)) {
            return true;
        }
        // if we have an intersection point the lines are definitely intersecting.
        if (this.intersectionWith(other) == null) {

            // the lines are verticals or parallels.
            if ((Math.abs(this.start.getX() - this.end.getX()) <= 1E-10)
                    && (Math.abs(other.start().getX() - other.end().getX()) <= 1E-10)
                    || (Math.abs(this.slop() - other.slop()) <= 1E-10)) {
                // case the lines are sharing start/end point.
                return (this.start.equals(other.start())) || (this.start.equals(other.end())
                        || (this.end.equals(other.start()) || (this.end.equals(other.end()))));
            }
            return false;
        }
        return true;
    }

    /**
     * this methode check and return the intersection point if the lines intersect and null otherwise.
     *
     * @param other the line we find intersection with
     * @return intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {

        // case both lines are just a point
        if ((this.start.equals(this.end)) && (other.start().equals(other.end()))) {
            if (this.start.equals(other.start())) {
                return this.start;
            }
            return null;
        }
        // 'this' is a point
        if (this.start.equals(this.end)) {
            if (other.includesPoint(this.start)) {
                return this.start;
            }
            return null;
        }
        // other is a point
        if (other.start().equals(other.end())) {
            if (this.includesPoint(other.start())) {
                return other.start();
            }
            return null;
        }

        // the lines are verticals or parallels
        if (((Math.abs(this.start.getX() - this.end.getX()) <= 1E-10)
                && Math.abs(other.start().getX() - other.end().getX()) <= 1E-10)
                || (Math.abs(this.slop() - other.slop()) <= 1E-10)) {
            // if the lines share a start/ end point
            if (this.start.equals(other.start())) {
                // the lines share only one point
                if (!this.includesPoint(other.end()) && !other.includesPoint(this.end)) {
                    return this.start;
                }
            }
            // if the lines share a start/ end point
            if (this.start.equals(other.end)) {
                // the lines share only one point
                if (!this.includesPoint(other.start()) && !other.includesPoint(this.end)) {
                    return this.start;
                }
            }
            // if the lines share a start/ end point
            if (this.end.equals(other.start())) {
                // the lines share only one point
                if (!this.includesPoint(other.end()) && !other.includesPoint(this.start)) {
                    return this.end;
                }
            }
            // if the lines share a start/ end point
            if (this.end.equals(other.end)) {
                // the lines share only one point
                if (!this.includesPoint(other.start()) && !other.includesPoint(this.start)) {
                    return this.end;
                }
            }
            return null;
        }

        // case this line is vertical and the other is not.
        if (Math.abs(this.start.getX() - this.end.getX()) <= 1E-10) {
            double interY = (other.slop() * this.start.getX()) + other.intersectionWithY();
            Point intersection = new Point(this.start.getX(), interY);
            if (this.includesPoint(intersection) && other.includesPoint(intersection)) {
                return intersection;
            }
            return null;
        }

        // case other line is vertical and this is not.
        if (Math.abs(other.start().getX() - other.end().getX()) <= 1E-10) {
            double interY = (this.slop() * other.start().getX()) + this.intersectionWithY();
            Point intersection = new Point(other.start().getX(), interY);
            if (this.includesPoint(intersection) && other.includesPoint(intersection)) {
                return intersection;
            }
            // the lines are verticals or parallels and never marge
            return null;
        }

        // case none of the lines is vertical, and they are not parallels or marge.
        double interX = (this.intersectionWithY() - other.intersectionWithY()) / (other.slop() - this.slop());
        double interY = (this.slop() * interX) + this.intersectionWithY();
        Point intersection = new Point(interX, interY);
        if (this.includesPoint(intersection) && other.includesPoint(intersection)) {
            return intersection;
        }
        // if we don't have any case of intersection point
        return null;
    }

    /**
     * this methode checks if two lines are equals.
     *
     * @param other the line we check this line with
     * @return true is equals, false otherwise
     */
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.end.equals(other.start()) && this.start.equals(other.end()));
    }

    /**
     * this methode finds the closest intersection point with the rectangle to the start of the line.
     *
     * @param rect the rectangle
     * @return the closest intersection point, or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).size() == 0) {
            return null;
        }
        Point closest = new Point(rect.intersectionPoints(this).get(0));
        if (rect.intersectionPoints(this).size() == 1) {
            return closest;
        }
        double minDis = closest.distance(this.start);
        for (Point inter : rect.intersectionPoints(this)) {
            if (inter.distance(this.start) < minDis) {
                closest = inter;
            }
        }
        return closest;
    }
}