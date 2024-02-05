import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * this class represent a ball.
 */

public class Ball implements Sprite {

    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private int right;
    private int left = 30;
    private int top = 35;
    private GameEnvironment gameEnvironment;

    // constructor

    /**
     * this constructor creates a ball from a center point, radius, color and game environment.
     *
     * @param center          the center of the ball
     * @param r               ball's radius
     * @param color           ball's color
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * this constructor creates a ball from a center point, radius and color.
     *
     * @param center the center of the ball
     * @param r      ball's radius
     * @param color  ball's color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * this constructor creates a ball from x,y coordinates, radius, color, and bounds.
     *
     * @param x      x-coordinate of the center point
     * @param y      y-coordinate of the center point
     * @param r      the radius
     * @param color  the ball's color
     * @param bottom the bottom bound
     * @param right  the right bound
     */
    public Ball(int x, int y, int r, java.awt.Color color, int bottom, int right) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.right = right;
    }

    /**
     * this constructor creates a ball from a center point, radius, color and bounds.
     *
     * @param center the center point
     * @param r      the radius
     * @param color  the ball's color
     * @param bottom the bottom bound
     * @param right  the right bound
     */
    public Ball(Point center, int r, java.awt.Color color, int bottom, int right) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.right = right;
    }

    /**
     * this constructor creates a ball from a center point, radius, color and bounds.
     *
     * @param center the center point
     * @param r      the radius
     * @param color  the ball's color
     * @param top    the top bound
     * @param left   the left bound
     * @param bottom the bottom bound
     * @param right  the right bound
     */
    public Ball(Point center, int r, java.awt.Color color, int top, int left, int bottom, int right) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.top = top;
        this.right = right;
        this.left = left;
    }

    /**
     * this constructor creates a ball from x,y center point, radius, color, bounds and game environment.
     *
     * @param x               center point's x-coordinate
     * @param y               center point's y-coordinate
     * @param r               ball's radius
     * @param color           ball's color
     * @param bottom          bottom bound
     * @param right           right bound
     * @param gameEnvironment the game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, int bottom, int right,
                GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.right = right;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * this constructor creates a ball from x,y coordinates, radius and color.
     *
     * @param x     x coordinate of the center point
     * @param y     y coordinate of the center point
     * @param r     ball's radius
     * @param color ball's color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    // accessors

    /**
     * this methode allows access to the x coordinate of the center point.
     *
     * @return x coordinate of the center point
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * this methode allows access to the y coordinate of the center point.
     *
     * @return y coordinate of the center point
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * this methode allows access to the ball's radius.
     *
     * @return ball's radius
     */
    public int getSize() {
        return r;
    }

    /**
     * this methode allows access to the ball's color.
     *
     * @return ball's color
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * this methode draw the ball on the given DrawSurface.
     *
     * @param surface the draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * this methode checks if the given point able to be the ball's center point.
     * the methode checks according to the ball's bounds and changes the velocity accordingly.
     *
     * @param p the point we check
     * @return true if the point is in the bound, false otherwise
     */
    public boolean checkCenter(Point p) {
        if (p.getX() + this.r > this.right) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
            return false;
        }
        if (p.getX() - this.r < this.left) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
            return false;
        }

        if (p.getY() - this.r < this.top) {
            this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
            return false;
        }
        return true;
    }


    /**
     * this methode set a point to be the ball's center point.
     *
     * @param center the new center point
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * constructor creates the ball's velocity from given velocity.
     *
     * @param v ball's velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * constructor creates the ball's velocity from given x,y coordinates.
     *
     * @param dx x coordinate of the point the ball should get to
     * @param dy y coordinate of the point the ball should get to
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * this methode allows access to the ball's velocity.
     *
     * @return ball's velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * this methode moves the ball according to if there is a hit or not.
     * if there is a hit the velocity changes
     */
    public void moveOneStep() {
        if (this.checkCenter(this.center)) {
            Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
            if (gameEnvironment.getClosestCollision(trajectory) != null) {
                Point collisionPoint = gameEnvironment.getClosestCollision(trajectory).collisionPoint();
                Collidable collisionObject = gameEnvironment.getClosestCollision(trajectory).collisionObject();
                this.setVelocity(collisionObject.hit(this, collisionPoint, this.getVelocity()));
            }
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * this methode calls to moveOneStep().
     */
    public void timePassed() {
        this.checkLocation();
        this.moveOneStep();
    }

    /**
     * this methode add the ball as sprite object to the given game.
     *
     * @param game the given game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * this methode checks if the ball entered the paddle.
     * if it is, it will change it's center to be outside the paddle.
     */
    public void checkLocation() {
        Rectangle paddle = this.gameEnvironment.getCollidables().get(0).getCollisionRectangle();
        if ((this.getX() > paddle.getUpperLeft().getX())
                && (this.getY() > paddle.getUpperLeft().getY())) {
            if (this.getX() < paddle.getUpperLeft().getX() + paddle.getWidth()
                    && (this.getY() < paddle.getUpperLeft().getY() + paddle.getHeight())) {
                this.setCenter(new Point(this.getX(), (this.getY() - paddle.getHeight())));
            }
        }
    }

    /**
     * this methode removes the ball from the given game.
     *
     * @param game given game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}