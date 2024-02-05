import java.util.List;

/**
 * interface for level information.
 */
public interface LevelInformation {

    /**
     * returns the number of balls at the beginning of the game.
     *
     * @return number of balls
     */
    int numberOfBalls();

    /**
     * return list of balls' velocities.
     *
     * @return balls' velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the paddle's speed.
     *
     * @return paddle's speed
     */
    int paddleSpeed();

    /**
     * returns the paddle's width.
     *
     * @return paddle's width
     */
    int paddleWidth();

    /**
     * returns the level's name.
     *
     * @return level's name
     */
    String levelName();

    /**
     * creates the level's background.
     *
     * @return level's background
     */
    Sprite getBackground();


    /**
     * creates the blocks.
     *
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * returns the number of blocks in the level.
     *
     * @return number of blocks
     */
    int numberOfBlocksToRemove();
}