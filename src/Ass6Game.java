/**@author Eldar Shlomi.
 * id 205616634.*/
import gamesetting.AnimationRunner;
import gamesetting.GameFlow;
import interfaces.LevelInformation;
import levels.LevelFour;
import levels.LevelOne;
import levels.LevelThree;
import levels.LevelTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass6Game class.
 * this class is the main method of the game, initialize and runs the game.
 */
public class Ass6Game {
    /**
     * main method for initialize the game and run him.
     * initialize - Initialize a new game: create the Blocks
     * and Ball (and Paddle) and add them to the game.
     * run - this method run the game - start the animation loop.
     * @param args - not used in this method.
     */
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner(60);
        biuoop.KeyboardSensor ks = ar.getGui().getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<>();
        GameFlow game = new GameFlow(ar, ks);

        // check for each different args value if it legal, if yes - add the
        // correspond level, if no - continue the check for the other args
        for (String arg : args) {
            switch (arg) {
                // the 4 legal values
                case "1":
                    levels.add(new LevelOne());
                    break;
                case "2":
                    levels.add(new LevelTwo());
                    break;
                case "3":
                    levels.add(new LevelThree());
                    break;
                case "4":
                    levels.add(new LevelFour());
                    break;
                // the value is not legal (not 1-4)
                default:
                    break;
            }

        }
        // if the levels list is empty = or the user didn't put args value
        // or he put illegal values, put the default option - level 1 - 4.
        if (levels.isEmpty()) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }
        game.runLevels(levels);
    }
}