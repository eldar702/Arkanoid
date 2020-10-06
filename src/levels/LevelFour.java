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
 * class for level 4.
 */
public class LevelFour implements LevelInformation {

    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * constructor for level 4.
     */
    public LevelFour() {
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
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 10;
        List<Velocity> ballsVelocitiess = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int angle = new Random().nextInt(140) - 70;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
            ballsVelocitiess.add(velocity);
        }
        return ballsVelocitiess;
    }

    @Override
    public int paddleSpeed() {
        return 12;
    }

    @Override
    public int paddleWidth() {
        int paddleWid = GameLevel.getGameWidth() / 10;
        return (paddleWid);
    }

    @Override
    public String levelName() {
        String levelNam = "Final Four";
        return levelNam;
    }

    @Override
    public Sprite getBackground() {
        //making the background to the game level by sprite kind class
        return new LevelFourBG();
    }

    @Override
    public List<Block> blocks() {
        double widthBlock = 40;
        double heightBlock = 20;
        double upperLeftX = (GameLevel.getGameWidth() - GameLevel.getBorderWidth() - widthBlock);
        double upperLeftY = (GameLevel.getGameHeight() / 4);
        List<Block> blocksList = new ArrayList<>();
        Color color = Block.randomColor();
        for (int i = 0; i < 7; i++, upperLeftY += heightBlock) {
            for (int j = 0; j < 19; j++, upperLeftX -= widthBlock) {
                Rectangle rectangle = new Rectangle(upperLeftX, upperLeftY, widthBlock, heightBlock);
                Block block = new Block(rectangle, color);
                blocksList.add(block);
            }
            upperLeftX = (GameLevel.getGameWidth() - GameLevel.getBorderWidth() - widthBlock);
            color = Block.randomColor();
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks.size();
    }
}
