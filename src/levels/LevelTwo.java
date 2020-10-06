package levels;
/**@author Eldar Shlomi.
 * id 205616634.*/
import gamesetting.GameLevel;
import geometry.Block;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * class for level 2.
 */
public class LevelTwo implements LevelInformation {

    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    /**
     * constructor for level 2.
     */
    public LevelTwo() {
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
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        int speed = 10;
        this.ballsVelocities = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls(); i++) {
            int angle = new Random().nextInt(140) - 70;
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, speed);
            ballsVelocities.add(velocity);
        }
        return ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        //variable declaration:
        return 12;
    }

    @Override
    public int paddleWidth() {
        paddleWidth = GameLevel.getGameWidth() / 4 * 3;
        return (paddleWidth);
    }

    @Override
    public String levelName() {
        levelName = "Wide Easy";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        //making the background to the game level by sprite kind class
        return new LevelTwoBG();
    }

    @Override
    public List<Block> blocks() {
        double widthBlock = 50 + (2 / 3.0);
        double heightBlock = 15;
        double upperLeftX = GameLevel.getBorderWidth();
        double upperLeftY = (270);
        double helper = widthBlock;
        blocks = new ArrayList<>();
        for (int i = 0; i < 15; i++, upperLeftX += widthBlock) {
            Rectangle rectangle = new Rectangle(upperLeftX, upperLeftY, widthBlock + 1, heightBlock);
            Block block = new Block(rectangle, Block.randomColor());
            blocks.add(block);
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
