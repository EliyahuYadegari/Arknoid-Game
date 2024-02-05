package geometry;

/**
 * this class represents a point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * this constructor creates a point from given x,y coordinates.
     *
     * @param x x-coordinate
     * @param y y-coordinates
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this constructor creates a point from given point.
     *
     * @param p given point
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * this methode calculates the distance of this point to the other point.
     *
     * @param other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double xDis = this.x - other.getX();
        double yDis = this.y - other.getY();
        double distance = Math.sqrt((xDis * xDis) + (yDis * yDis));
        return distance;
    }

    /**
     * this methode checks if the points are equal.
     *
     * @param other point
     * @return return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if ((Math.abs(this.x - other.getX()) <= 1E-10) && (Math.abs(this.y - other.getY()) <= 1E-10)) {
            return true;
        }
        return false;
    }

    /**
     * this methode allows access to the x coordinate of the point.
     *
     * @return x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * this methode allows access to the y coordinate of the point.
     *
     * @return y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * this methode is a setter for the x-coordinate.
     *
     * @param x given x
     */
    public void setX(double x) {
        this.x = x;
    }
}
