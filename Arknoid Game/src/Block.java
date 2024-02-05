import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represent block that implements the collidable and sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private Point start;
    private int height;
    private int width;
    private List<HitListener> hitListeners;


    /**
     * the constructor creates a block from given rectangle.
     *
     * @param rectangle the given rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.start = rectangle.getUpperLeft();
        this.height = (int) rectangle.getHeight();
        this.width = (int) rectangle.getWidth();
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * the constructor creates a block from given location, width and height and color.
     *
     * @param x      x-coordinate
     * @param y      y-coordinate
     * @param width  rectangle's width
     * @param height rectangle's height
     * @param color  block's color
     */
    public Block(int x, int y, int width, int height, Color color) {
        this.start = new Point(x, y);
        this.rectangle = new Rectangle(this.start, width, height);
        this.color = color;
        this.width = width;
        this.height = height;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * the constructor creates a block from given location, width and height.
     *
     * @param x      x-coordinate
     * @param y      y-coordinate
     * @param width  rectangle's width
     * @param height rectangle's height
     */
    public Block(int x, int y, int width, int height) {
        this.start = new Point(x, y);
        this.rectangle = new Rectangle(this.start, width, height);
        this.width = width;
        this.height = height;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Return the "collision shape" of the object.
     *
     * @return block's rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * return the new velocity expected after the hit based on the current velocity.
     *
     * @param hitter          the ball that hits
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the new velocity after the hit
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
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * this methode draws the block on a given draw surface.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) start.getX(), (int) start.getY(), (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) start.getX(), (int) start.getY(), (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
    }

    /**
     * this methode notify the block that time has passed.
     */
    public void timePassed() {
    }

    /**
     * adds this block to the game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * removes a block from the game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * adds hit listener.
     *
     * @param hl listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removes hit listener.
     *
     * @param hl listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify all listeners about a hit.
     *
     * @param hitter the ball that hits
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
