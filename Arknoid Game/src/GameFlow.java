import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * this class runs the game's levels.
 */
public class GameFlow {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private GUI gui;
    private Counter score;
    private boolean win = true;

    /**
     * constructor.
     */
    public GameFlow() {
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        this.ar = new AnimationRunner(this.gui, 60);
        this.ks = this.gui.getKeyboardSensor();
        this.score = new Counter(0);
    }

    /**
     * this methode runs the levels according to level's list.
     *
     * @param levels list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.gui, this.score);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }

            if (level.getBallCounter().getValue() == 0) {
                this.win = false;
                break;
            }
        }
        EndScreen end = new EndScreen(this.ks, this.win, this.score);
        ar.run(new KeyPressStoppableAnimation(end, this.ks));
        this.gui.close();
    }
}
