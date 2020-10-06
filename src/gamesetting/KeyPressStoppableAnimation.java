package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * decorator-class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private String key;
    private Animation animation;
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param keyboard the game keyboard
     * @param key the key which press make the action
     * @param animation the specific animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
         this.keyboard = keyboard;
         this.animation = animation;
         this.key = key;
         this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                if (isAlreadyPressed) {
                    return;
                }
                this.stop = true;
                this.isAlreadyPressed = false;
                return;
            }
            this.isAlreadyPressed = false;
        }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}