package geometry;
import java.util.ArrayList;
/**
 * this class represents a rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * this constructor Creates a new rectangle with location, width and height.
     *
     * @param upperLeft starting location
     * @param width     rectangle's width
     * @param height    rectangle's height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * this methode finds the intersection points of the rectangle with given line.
     *
     * @param line the given line
     * @return list of intersection points or empty list if there is no points
     */
    public java.util.ArrayList<Point> intersectionPoints(Line line) {
        ArrayList<Point> interPoints = new ArrayList<>();

        Line[] recLines = new Line[4];
        recLines[0] = this.leftLine();
        recLines[1] = this.rightLine();
        recLines[2] = this.topLine();
        recLines[3] = this.bottomLine();

        for (Line recLine : recLines) {
            if (line.intersectionWith(recLine) != null) {
                interPoints.add(line.intersectionWith(recLine));
            }
        }
        return interPoints;
    }

    /**
     * get access to the rectangle's width.
     *
     * @return width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * get access to the rectangle's height.
     *
     * @return height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * get access to the upper-left point of the rectangle.
     *
     * @return upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * builds the top line of the rectangle.
     *
     * @return rectangle's top line
     */
    public Line topLine() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(upperRight, this.upperLeft);
    }

    /**
     * builds the bottom line of the rectangle.
     *
     * @return rectangle's bottom line
     */
    public Line bottomLine() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(downLeft, downRight);
    }

    /**
     * builds the right line of the rectangle.
     *
     * @return rectangle's right line
     */
    public Line rightLine() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(upperRight, downRight);
    }

    /**
     * builds the left line of the rectangle.
     *
     * @return rectangle's left line
     */
    public Line leftLine() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(downLeft, this.upperLeft);
    }

}