package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.KeyboardSensor;
import interfaces.LevelInformation;

import java.util.List;

/**
 * Responsible for the flow of the various stages.
 */
public class GameFlow {
    private AnimationRunner animationR;
    private KeyboardSensor keyboardS;
    private Counter liveCounter;
    private Counter scoreCounter;
    private int numOfLevels;

    /**
     * constructor.
     * @param ar the animation which run
     * @param ks the keyboard sensor which in the game
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationR = ar;
        this.keyboardS = ks;
        this.scoreCounter = new Counter();
        this.liveCounter = new Counter();
        this.liveCounter.increase(3);
        this.numOfLevels = 0;
    }

    /**
     * run all the levels.
     * @param levels A list containing all the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        levels.size();

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, liveCounter, scoreCounter, animationR, keyboardS);

            level.initialize();

            while (level.getCounterLive().getValue() != 0 && level.getCounterBlock().getValue() != 0) {
                level.run();

            }
        }
        animationR.run(new KeyPressStoppableAnimation(animationR.getGui().getKeyboardSensor(), "SPACE_KEY",
                new EndScreen(this.scoreCounter.getValue(), 2)));
        this.animationR.getGui().close();
    }
}
