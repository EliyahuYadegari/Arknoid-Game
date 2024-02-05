import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class represents the game's end screen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Boolean win;
    private Counter score;

    /**
     * constructor.
     *
     * @param k     keyboard sensor
     * @param win   if user wins or loses
     * @param score the final score
     */
    public EndScreen(KeyboardSensor k, Boolean win, Counter score) {
        this.keyboard = k;
        this.stop = false;
        this.win = win;
        this.score = score;
    }

    /**
     * this methode does one frame of animation on given draw surface.
     *
     * @param d draw surface
     */
    public void doOneFrame(DrawSurface d) {
        if (this.win) {
            d.drawText(10, d.getHeight() / 2, "YOU WIN! Your score is: " + this.score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "GAME OVER. Your score is: " + this.score.getValue(), 32);
        }
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

