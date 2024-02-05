import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents Wide Easy level.
 */
public class WideEasy implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * returns the number of balls at the beginning of the game.
     *
     * @return number of balls
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * return list of balls' velocities.
     *
     * @return balls' velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(-35 + 10 * i, 4);
            velocities.add(velocity);
        }
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
        return 600;
    }

    /**
     * returns the level's name.
     *
     * @return level's name
     */
    @Override
    public String levelName() {
        return "Wide Easy";
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
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, WIDTH, HEIGHT);
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 20);
                d.setColor(Color.BLACK);
                d.drawText(600, 17, "Level Name: Wide Easy", 15);
                d.setColor(Color.ORANGE);
                for (int i = 0; i < 70; i++) {
                    d.drawLine(200, 200, 0 + 10 * i, 300);
                }
                d.setColor(Color.pink);
                d.fillCircle(200, 200, 50);
                d.setColor(Color.ORANGE);
                d.fillCircle(200, 200, 40);
                d.setColor(Color.YELLOW);
                d.fillCircle(200, 200, 30);
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
        for (int i = 0; i < 2; i++) {
            Block b = new Block(20 + 50 * i, 300, 50, 20, Color.RED);
            blocks.add(b);
        }
        for (int i = 0; i < 2; i++) {
            Block b = new Block(120 + 50 * i, 300, 50, 20, Color.ORANGE);
            blocks.add(b);
        }
        for (int i = 0; i < 2; i++) {
            Block b = new Block(220 + 50 * i, 300, 50, 20, Color.YELLOW);
            blocks.add(b);
        }
        for (int i = 0; i < 3; i++) {
            Block b = new Block(320 + 50 * i, 300, 50, 20, Color.GREEN);
            blocks.add(b);
        }
        for (int i = 0; i < 2; i++) {
            Block b = new Block(470 + 50 * i, 300, 50, 20, Color.BLUE);
            blocks.add(b);
        }
        for (int i = 0; i < 2; i++) {
            Block b = new Block(570 + 50 * i, 300, 50, 20, Color.PINK);
            blocks.add(b);
        }
        for (int i = 0; i < 2; i++) {
            Block b = new Block(670 + 50 * i, 300, 50, 20, Color.CYAN);
            blocks.add(b);
        }
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
