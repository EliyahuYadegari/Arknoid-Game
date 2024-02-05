// Eliyahu Yadegari

import java.util.ArrayList;
import java.util.List;

/**
 * this class has the main that runs the game.
 */
public class Ass6Game {

    /**
     * the main that runs the game according to given level's order.
     *
     * @param args order of levels
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        DirectHit level1 = new DirectHit();
        WideEasy level2 = new WideEasy();
        Green3 level3 = new Green3();
        FinalFour level4 = new FinalFour();

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                // if there is no integer --> skip
                try {
                    if (Integer.parseInt(args[i]) == 1) {
                        levels.add(level1);
                    }
                    if (Integer.parseInt(args[i]) == 2) {
                        levels.add(level2);
                    }
                    if (Integer.parseInt(args[i]) == 3) {
                        levels.add(level3);
                    }
                    if (Integer.parseInt(args[i]) == 4) {
                        levels.add(level4);
                    }
                } catch (NumberFormatException exception) {
                }
            }
        }
        if (levels.size() == 0) {
            levels.add(level3);
            levels.add(level4);
            levels.add(level1);
            levels.add(level2);

        }
        GameFlow game = new GameFlow();
        game.runLevels(levels);
    }
}
