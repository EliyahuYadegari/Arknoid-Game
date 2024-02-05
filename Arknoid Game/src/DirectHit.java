import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents the level Direct Hit.
 */
public class DirectHit implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * returns the number of balls at the beginning of the game.
     *
     * @return number of balls
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * return list of balls' velocities.
     *
     * @return balls' velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 3));
        return velocities;
    }

    /**
     * returns the paddle's speed.
     *
     * @return paddle's speed
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * returns the paddle's width.
     *
     * @return paddle's width
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * returns the level's name.
     *
     * @return level's name
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * creates the level's background.
     *
     * @return level's background
     */
    @Override
    public Sprite getBackground() {
        Sprite background = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, WIDTH, HEIGHT);
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 20);
                d.setColor(Color.BLACK);
                d.drawText(600, 17, "Level Name: Direct Hit", 15);
                d.setColor(Color.BLUE);
                d.drawCircle(400, 165, 120);
                d.drawCircle(400, 165, 90);
                d.drawCircle(400, 165, 60);
                d.drawLine(400, 40, 400, 140);
                d.drawLine(400, 195, 400, 300);
                d.drawLine(265, 165, 380, 165);
                d.drawLine(420, 165, 535, 165);
            }

            @Override
            public void timePassed() {
            }
        };
        return background;
    }

    /**
     * creates the blocks.
     *
     * @return list of blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(385, 150, 30, 30, Color.RED));
        return blocks;
    }

    /**
     * returns the number of blocks in the level.
     *
     * @return number of blocks
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

}
