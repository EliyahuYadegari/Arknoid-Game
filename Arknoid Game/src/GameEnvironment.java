import geometry.Line;
import geometry.Point;

import java.util.ArrayList;

/**
 * this class represents the game environment.
 * holds a list of collidable objects
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * a constructor that creates new list of collidables.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add a given collidable to the environment.
     *
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * this methode checks if a given object will collide with any of the collidables in this collection.
     *
     * @param trajectory the trajectory of the ball
     * @return the information about the closest collision that is going to occur, or null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (collidables.size() == 0) {
            return null;
        }
        // find the first object the ball collides with.
        int i = 0;
        while (i < collidables.size()
                && collidables.get(i).getCollisionRectangle().intersectionPoints(trajectory).size() == 0) {
            ++i;
        }
        if (i == collidables.size()) {
            return null;
        }
        Collidable collidableObject = collidables.get(i);
        Point check = trajectory.closestIntersectionToStartOfLine(collidableObject.getCollisionRectangle());
        Point collisionPoint = check;
        double minDis = collisionPoint.distance(trajectory.start());
        for (Collidable c : collidables) {
            check = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (check != null) {
                if (check.distance(trajectory.start()) < minDis) {
                    collidableObject = c;
                    collisionPoint = check;
                }
            }
        }
        return new CollisionInfo(collisionPoint, collidableObject);
    }

    /**
     * getter for the collidables list.
     *
     * @return collidables list
     */
    public java.util.List<Collidable> getCollidables() {
        return this.collidables;
    }
}
