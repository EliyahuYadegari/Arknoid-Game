import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the final four level.
 */
public class FinalFour implements LevelInformation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * returns the number of balls at the beginning of the game.
     *
     * @return number of balls
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * return list of balls' velocities.
     *
     * @return balls' velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        Velocity v1 = new Velocity(4, 4);
        Velocity v2 = new Velocity(3, 3);
        Velocity v3 = new Velocity(-3.5, -3.5);
        velocities.add(v1);
        velocities.add(v2);
        velocities.add(v3);
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
        return "Final Four";
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
                d.setColor(Color.BLUE);
                d.fillRectangle(0, 0, WIDTH, HEIGHT);
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 20);
                d.setColor(Color.BLACK);
                d.drawText(600, 17, "Level Name: Final Four", 15);
                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(80 + 10 * i, 415, 70 + 10 * i, 590);
                }

                for (int i = 0; i < 10; i++) {
                    d.drawLine(620 + 10 * i, 500, 610 + 10 * i, 590);
                }
                d.setColor(Color.lightGray);
                d.fillCircle(130, 400, 35);
                d.fillCircle(650, 500, 35);
                d.fillCircle(160, 400, 22);
                d.fillCircle(620, 500, 22);
                d.setColor(Color.WHITE);
                d.fillCircle(110, 415, 27);
                d.fillCircle(80, 415, 17);
                d.fillCircle(670, 510, 25);
                d.fillCircle(700, 510, 17);
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
        //first row of blocks
        Block[] firstRow = new Block[15];
        for (int i = 0; i < firstRow.length; ++i) {
            firstRow[i] = new Block(WIDTH - 20 - 50 - i * 50, 100, 50, 20, Color.GRAY);
            blocks.add(firstRow[i]);
        }

        //second row of blocks
        Block[] secondRow = new Block[15];
        for (int i = 0; i < secondRow.length; ++i) {
            secondRow[i] = new Block(WIDTH - 20 - 50 - i * 50, 100 + 20, 50, 20, Color.RED);
            blocks.add(secondRow[i]);
        }

        //third row of blocks
        Block[] thirdRow = new Block[15];
        for (int i = 0; i < thirdRow.length; ++i) {
            thirdRow[i] = new Block(WIDTH - 20 - 50 - i * 50, 100 + 2 * 20, 50, 20, Color.YELLOW);
            blocks.add(thirdRow[i]);
        }

        //forth row of blocks
        Block[] forthRow = new Block[15];
        for (int i = 0; i < forthRow.length; ++i) {
            forthRow[i] = new Block(WIDTH - 20 - 50 - i * 50, 100 + 3 * 20, 50, 20, Color.GREEN);
            blocks.add(forthRow[i]);
        }

        //fifth row of blocks
        Block[] fifthRow = new Block[15];
        for (int i = 0; i < fifthRow.length; ++i) {
            fifthRow[i] = new Block(WIDTH - 20 - 50 - i * 50, 100 + 4 * 20, 50, 20, Color.WHITE);
            blocks.add(fifthRow[i]);
        }

        //fifth row of blocks
        Block[] sixthRow = new Block[15];
        for (int i = 0; i < sixthRow.length; ++i) {
            sixthRow[i] = new Block(WIDTH - 20 - 50 - i * 50, 100 + 5 * 20, 50, 20, Color.PINK);
            blocks.add(sixthRow[i]);
        }
        //fifth row of blocks
        Block[] seventhRow = new Block[15];
        for (int i = 0; i < seventhRow.length; ++i) {
            seventhRow[i] = new Block(WIDTH - 20 - 50 - i * 50, 100 + 6 * 20, 50, 20, Color.CYAN);
            blocks.add(seventhRow[i]);
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
        return blocks().size();
    }
}
