package levels;
/**@author Eldar Shlomi.
 * id 205616634.*/
import gamesetting.GameLevel;
import geometry.Block;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class for level 1.
 */
public class LevelOne implements LevelInformation {

    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * constructor for level 1.
     */
    public LevelOne() {
       this.ballsVelocities = initialBallVelocities();
       this.paddleSpeed = paddleSpeed();
       this.paddleWidth = paddleWidth();
       this.levelName = levelName();
       this.getBackground = getBackground();
       this.blocks = blocks();
       this.numberOfBlocksToRemove = numberOfBlocksToRemove();

    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 10;
        ballsVelocities = new ArrayList<>();
            Velocity velocity = Velocity.fromAngleAndSpeed(0, speed);
        ballsVelocities.add(velocity);
        return ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return 12;
    }

    @Override
    public int paddleWidth() {
        paddleWidth = GameLevel.getGameWidth() / 10;
        return (paddleWidth);
    }

    @Override
    public String levelName() {
        levelName = "Direct Hit";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        //making the background to the game level by sprite kind class
        return new LevelOneBG();
    }

    @Override
    public List<Block> blocks() {
        double widthBlock = GameLevel.getGameWidth() / 32;
        double heightBlock = widthBlock;
        double upperLeftX = (GameLevel.getGameWidth() / 2) - (widthBlock / 2);
        double upperLeftY = (GameLevel.getGameHeight() / 3 - 13);

        Rectangle rectangle = new Rectangle(upperLeftX, upperLeftY, widthBlock, heightBlock);
        Block block =  new Block(rectangle, Color.red);
       blocks = new ArrayList<>();
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}
