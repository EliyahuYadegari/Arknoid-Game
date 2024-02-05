import geometry.Point;
/**
 * this class represent the collision info.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * the constructor creates collision info from give collision point and object.
     *
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * getter for the collision point.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.

    /**
     * getter for the collision object.
     *
     * @return the collision object
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}