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
import java.util.Random;
/**
 * class for level 3.
 */
public class LevelThree implements LevelInformation {

    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    /**
     * constructor for level 3.
     */
    public LevelThree() {
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
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 5;
        ballsVelocities = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int angle = new Random().nextInt(140) - 70;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
            ballsVelocities.add(velocity);
        }
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
       levelName = "Green 3";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        //making the background to the game level by sprite kind class
        return new LevelThreeBG();
    }

    @Override
    public List<Block> blocks() {
        double widthBlock = 50;
        double heightBlock = 25;
        double upperLeftX = (GameLevel.getGameWidth() - GameLevel.getBorderWidth() - widthBlock);
        double upperLeftY = (GameLevel.getGameHeight() / 3);
        blocks = new ArrayList<>();
        Color color = Block.randomColor();
        for (int i = 0; i < 5; i++, upperLeftY += heightBlock) {
            for (int j = 0; j < 10 - i; j++, upperLeftX -= widthBlock) {
                Rectangle rectangle = new Rectangle(upperLeftX, upperLeftY, widthBlock, heightBlock);
                Block block = new Block(rectangle, color);
                blocks.add(block);
            }
            upperLeftX = (GameLevel.getGameWidth() - GameLevel.getBorderWidth() - widthBlock);
            color = Block.randomColor();
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}
