import biuoop.DrawSurface;

/**
 * interface for animation's objects.
 */
public interface Animation {
    /**
     * this methode does one frame of animation on given draw surface.
     *
     * @param d draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * returns when the animation should stop.
     *
     * @return true when should stop, false otherwise.
     */
    boolean shouldStop();
}