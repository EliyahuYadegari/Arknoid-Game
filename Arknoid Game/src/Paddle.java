import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Rectangle;
import geometry.Point;
import geometry.Line;

import java.awt.Color;

/**
 * this class represent the paddle.
 * this class implements Sprite and Collidable
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rectangle;
    private Color color;
    private biuoop.KeyboardSensor keyboard;
    private int speed;
    private int boardRightBound;

    /**
     * a constructor that creates paddle from rectangle, color, gui and right bound.
     *
     * @param rect  the paddle's shape
     * @param color
     * @param gui   the gui
     * @param right the right bound
     * @param speed the paddle's speed
     */
    public Paddle(Rectangle rect, Color color, GUI gui, int right, int speed) {
        this.rectangle = rect;
        this.color = color;
        this.keyboard = gui.getKeyboardSensor();
        this.boardRightBound = right;
        this.speed = speed;
    }

    /**
     * constructor that creates paddle from location, width and height, color, gui and right bound.
     *
     * @param p
     * @param width  paddle's width
     * @param height paddles height
     * @param color  the paddle's color
     * @param gui    the gui
     * @param right  the right bound
     * @param speed  paddle's speed
     */
    public Paddle(Point p, int width, int height, Color color, GUI gui, int right, int speed) {
        this.rectangle = new Rectangle(p, width, height);
        this.color = color;
        this.keyboard = gui.getKeyboardSensor();
        this.boardRightBound = right;
        this.speed = speed;
    }

    /**
     * this methode moves the paddle to the left when the left key is pressed.
     */
    public void moveLeft() {
        int newX = (int) this.rectangle.getUpperLeft().getX() - this.speed;
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && newX > 20) {
            this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * this methode moves the paddle to the right when the right key is pressed.
     */
    public void moveRight() {
        int newX = (int) this.rectangle.getUpperLeft().getX() + this.speed;
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && newX + this.rectangle.getWidth() < this.boardRightBound) {
            this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                    this.rectangle.getWidth(), this.rectangle.getHeight());
        }
    }

    /**
     * this methode calls to moveLeft() and moveRight().
     */
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    /**
     * this methode draws the paddle on a given draw surface.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * this methode returns the paddle's rectangle.
     *
     * @return the paddle's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * return the new velocity expected after the hit based on the current velocity.
     *
     * @param hitter          the ball that hits
     * @param collisionPoint  the collision point of the ball with the paddle
     * @param currentVelocity the ball's current velocity
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity);
        if (this.rectangle.rightLine().includesPoint(collisionPoint)
                || this.rectangle.leftLine().includesPoint(collisionPoint)) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        if (this.rectangle.bottomLine().includesPoint(collisionPoint)
                || this.rectangle.topLine().includesPoint(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * return the new velocity expected after the hit based on where the ball hits the paddle.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return new velocity
     */
    public Velocity hitSegments(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity);
        double length = this.rectangle.getWidth() / 5;
        Line[] segments = new Line[5];
        for (int i = 0; i < 5; ++i) {
            segments[i] = new Line(this.rectangle.getUpperLeft().getX() + i * length,
                    this.rectangle.getUpperLeft().getY(),
                    this.rectangle.getUpperLeft().getX() + (i + 1) * length,
                    this.rectangle.getUpperLeft().getY());
        }
        if (segments[0].includesPoint(collisionPoint)) {
            newVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.speed());
        }
        if (segments[1].includesPoint(collisionPoint)) {
            newVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.speed());
        }
        if (segments[2].includesPoint(collisionPoint)) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        if (segments[3].includesPoint(collisionPoint)) {
            newVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.speed());
        }
        if (segments[4].includesPoint(collisionPoint)) {
            newVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.speed());
        }

        return newVelocity;
    }
}