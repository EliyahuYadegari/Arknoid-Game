import geometry.Point;
/**
 * this class represent a velocity.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * this methode creates the velocity from given angle and speed.
     *
     * @param angle to the velocity's direction
     * @param speed of the velocity
     * @return the new point that creates the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle - 90);
        // convert degrees to radians
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

    // constructors

    /**
     * this constructor creates point that will define the velocity.
     *
     * @param dx x coordinate of the point
     * @param dy y coordinate of the point
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * this constructor creates velocity from given velocity.
     *
     * @param v given velocity
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * this methode allows access to the x coordinate of the point.
     *
     * @return x coordinate
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * this methode allows access to the y coordinate of the point.
     *
     * @return y coordinate
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * this methode take a point with position (x,y) and return a new point.
     *
     * @param p given point
     * @return new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return (new Point(p.getX() + this.dx, p.getY() + this.dy));
    }

    /**
     * this setter set new x-coordinate in the velocity.
     *
     * @param x x-coordinate
     */
    public void setDx(double x) {
        this.dx = x;
    }

    /**
     * this setter set new y-coordinate in the velocity.
     *
     * @param dy y-coordinate
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * this methode calculates the velocity's speed.
     *
     * @return velocity's speed
     */
    public double speed() {
        Point point = new Point(this.dx, this.dy);
        return point.distance(new Point(0, 0));
    }

}
