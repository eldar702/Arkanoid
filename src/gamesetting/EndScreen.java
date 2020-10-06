package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/
import biuoop.DrawSurface;
import interfaces.Animation;

/**
 * the screen which appears when the game finish, Whether the user
 * win or lose, saying the result of the game and the user's score.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private int pointNum;
    private int cases;

    /**
     * constructor.
     * @param point the user points
     * @param cases the result of the game - if the user win or lose
     */
    public EndScreen(int point, int cases) {
        this.stop = false;
        this.pointNum = point;
        this.cases = cases;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // happened when player lose
        if (cases == 1) {
            d.drawText(50, d.getHeight() / 2, "Game Over. Your score is " + this.pointNum, 50);
            }

        // happened when player win
        if (cases == 2) {
            d.drawText(50, d.getHeight() / 2, "You Win! Your score is " + this.pointNum, 50);
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
