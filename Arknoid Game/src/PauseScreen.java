import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class represents pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k Keyboard Sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * this methode does one frame of animation on given draw surface.
     *
     * @param d draw surface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * returns when the animation should stop.
     *
     * @return true when should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}