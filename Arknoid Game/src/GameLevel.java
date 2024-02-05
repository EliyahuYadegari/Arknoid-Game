import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;

import java.awt.*;

/**
 * this class represents a game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private static final int BOUND = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private biuoop.KeyboardSensor keyboard;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;


    /**
     * constructor that creates new game.
     *
     * @param levelInformation level information
     * @param keyboard         Keyboard Sensor
     * @param runner           Animation Runner
     * @param gui              gui
     * @param score            score
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard,
                     AnimationRunner runner, GUI gui, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter(0);
        this.ballCounter = new Counter(levelInformation.numberOfBalls());
        this.gui = gui;
        this.running = true;
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.score = score;
    }

    /**
     * this methode adds given collidable to the game environment.
     *
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * this methode adds given sprite to the sprite collection.
     *
     * @param s the given sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks, Ball and Paddle, and add them to the game.
     */
    public void initialize() {
        // create background
        this.addSprite(this.levelInformation.getBackground());


        // create the paddle
        Paddle paddle = new Paddle(new Point((WIDTH - this.levelInformation.paddleWidth()) / 2,
                560), this.levelInformation.paddleWidth(),
                20, Color.YELLOW, this.gui, WIDTH - BOUND, this.levelInformation.paddleSpeed());
        paddle.addToGame(this);

        // create listeners
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);

        // create boundary blocks

        Block leftBlock = new Block(0, 20, BOUND, HEIGHT, Color.DARK_GRAY);
        leftBlock.addToGame(this);

        Block topBlock = new Block(BOUND, 20, WIDTH - BOUND, BOUND, Color.DARK_GRAY);
        topBlock.addToGame(this);

        Block bottomBlock = new Block(BOUND, HEIGHT - 5, WIDTH - BOUND, 5, Color.BLACK);
        bottomBlock.addToGame(this);
        bottomBlock.addHitListener(ballRemover);

        Block rightBlock = new Block(WIDTH - BOUND, BOUND, BOUND, HEIGHT - BOUND, Color.DARK_GRAY);
        rightBlock.addToGame(this);

        for (Block b : this.levelInformation.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracking);
        }
        this.blockCounter.increase(this.levelInformation.numberOfBlocksToRemove());

        // create the balls
        int i = 0;
        for (Velocity v : this.levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(WIDTH / (2 + i), 555, 6, Color.WHITE, HEIGHT - 20, WIDTH - 20, this.environment);
            ball.setVelocity(v);
            ball.addToGame(this);
            ++i;
        }

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
    }

    /**
     * Run the game - start the animation loop.
     * the loop ends when no balls\blocks left
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * removes Collidable object from the game.
     *
     * @param c given Collidable
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }

    /**
     * removes sprite object from the game.
     *
     * @param s given sprite
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }

    /**
     * this methode does one frame of animation on given draw surface.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(this.keyboard), this.keyboard));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.blockCounter.getValue() == 0) {
            score.increase(100);
            this.sprites.drawAllOn(d);
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * returns when the animation should stop.
     *
     * @return true when should stop, false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * returns the current number of balls.
     *
     * @return current number of balls
     */
    public Counter getBallCounter() {
        return ballCounter;
    }
}