import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class represents object with Key Press Stoppable behavior.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     *
     * @param animation animation
     * @param keyboard  keyboard sensor
     */
    public KeyPressStoppableAnimation(Animation animation, KeyboardSensor keyboard) {
        this.animation = animation;
        this.keyboard = keyboard;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * this methode does one frame of animation on given draw surface.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)
                && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * returns when the animation should stop.
     *
     * @return true when should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
