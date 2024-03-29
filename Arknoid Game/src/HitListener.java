/**
 * interface for listeners.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block
     * @param hitter   the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
