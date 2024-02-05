import biuoop.DrawSurface;

import java.awt.Color;

/**
 * this class represents the game's score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * constructor.
     *
     * @param scoreCounter score counter
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        // create score area
        d.setColor(Color.BLACK);
        d.drawText(370, 17, "Score:" + scoreCounter.getValue(), 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the objects as sprite to the game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
