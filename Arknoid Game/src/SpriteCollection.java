import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a sprite collection.
 */
public class SpriteCollection {

    private java.util.ArrayList<Sprite> sprites;

    //constructor

    /**
     * constructor that creates new list of sprite objects.
     */
    public SpriteCollection() {
        this.sprites = new java.util.ArrayList<>();
    }

    /**
     * this methode adds a given sprite to the list.
     *
     * @param s the given sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        if (sprites.size() != 0) {
            List<Sprite> spriteList = new ArrayList<>(this.sprites);
            for (Sprite s : spriteList) {
                s.timePassed();
            }
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        if (sprites.size() != 0) {
            for (Sprite s : sprites) {
                s.drawOn(d);
            }
        }
    }

    /**
     * getter.
     *
     * @return sprites
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}