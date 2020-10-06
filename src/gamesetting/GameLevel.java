package gamesetting;
/**@author Eldar Shlomi.
 * id 205616634.*/

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Ball;
import geometry.Block;
import geometry.Velocity;
import geometry.Rectangle;
import geometry.Paddle;

import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 this class connect all the information together into one game.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterBlock;
    private Counter counterBall;
    private Counter counterPoint;
    private Counter counterLive;
    private Counter counterName;
    private Paddle paddle;
    private Ball ball;
    private biuoop.KeyboardSensor keyboard;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private int counter = 2;
    // height of the game screen.
    private static final int GAME_HIGHT = 600;
    // width of the game screen.
    private static final int GAME_WIDTH = 800;

    //the border blocks width
    private static final int BORDER_WIDTH = GAME_WIDTH / 40;

    /**
     * constructor for Game variable.
     * @param level the specific level
     * @param live the number of lives that the user have
     * @param score the score of the user
     * @param anima the animation to run
     * @param key the keyboard of the game
     */
    public GameLevel(LevelInformation level, Counter live, Counter score, AnimationRunner anima, KeyboardSensor key) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.counterBlock = new Counter();
        this.counterBall = new Counter();
        this.counterPoint = score;
        this.counterLive = live;
        this.counterName = new Counter();
        this.runner = anima;
        this.keyboard = key;
        this.level = level;

    }


    /**
     *Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        //variables declaration:
        // add to the game the different counters
        addingCounters();
        sprites.addSprite(this.level.getBackground());
        //bounds - adding the game's bounds
        gameBoundsByBlocks(this, GAME_WIDTH, GAME_HIGHT);
        //adding blocks to the game.
        addingBlocks();
       // adding balls to the game.
        addingBall(this.level.numberOfBalls());

        //adding paddle to the game
        addingPaddle();
        // adding dead zone to the game
        deadZone(BORDER_WIDTH, GAME_HIGHT, GAME_WIDTH - 2 * BORDER_WIDTH, 10, this);
        }

    /**
     * adding the counters to the game.
     */
    public void addingCounters() {
        // adding to the game the score counter.
        Rectangle scoreRec = new Rectangle(0, BORDER_WIDTH, GAME_WIDTH, BORDER_WIDTH);
        ScoreIndicator score = new ScoreIndicator(scoreRec, Color.lightGray, this.counterPoint);
        this.addSprite(score);
        //adding to the game the lives counter.
        LivesIndicator livesCounter = new LivesIndicator(scoreRec, this.counterLive);
        this.addSprite(livesCounter);

        // adding the level name to the game
        NameIndicator name = new NameIndicator(scoreRec, this.counterName, this.level.levelName());
        this.addSprite(name);
    }
    /**
     * method for creating and adding blocks to the game.
     */
    public void addingBlocks() {
        List<Block> blocks = new ArrayList<>();
        blocks = this.level.blocks();
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            BlockRemover remover = new BlockRemover(this, this.counterBlock);
            ScoreTrackingListener pointsBlock = new ScoreTrackingListener(this.counterPoint);
            //adding the blocks to the hitlistener game's variable list.
            block.addHitListener(remover);
            block.addHitListener(pointsBlock);
            block.addToGame(this);
            //increasing 1 to the count of blocks.
            this.counterBlock.increase(1);
            }
        }

    /**
     * this method adding the ball to the game, making him considering in the blocks at game.
     * @param numOfBalls the number of ball to create
     */

    public void addingBall(int numOfBalls) {
        //variable declaration:
        int ballSize = 6;
        List<Velocity> velocities  = new ArrayList<>();
        velocities = this.level.initialBallVelocities();
        // loop for making x balls.
        for (int h = 1; h <= numOfBalls; h++) {


            //making the ball by choosing his location (x and y coordinate),
            // ball size, color and velocity.
            ball = new Ball((GAME_WIDTH / 2), (GAME_HIGHT / 10 * 9), ballSize, Color.white);
            ball.setVelocity(velocities.get(h - 1));
            ball.addToGame(this);
            ball.setGameE(this.environment);
            //increasing 1 to the count of balls.
            this.counterBall.increase(1);
        }
    }
    /**
     * the method adding the paddle to the game lower in the surface.
     */
    public void addingPaddle() {
        this.keyboard = this.runner.getGui().getKeyboardSensor();
        this.paddle = new Paddle(keyboard, GAME_WIDTH, GAME_HIGHT, this.level.paddleWidth(),
                15, BORDER_WIDTH, level.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * this method makes the surface game bounds by creating blocks.
     * @param game - the game variable.
     * @param width - the width of the game screen.
     * @param height - the height of the game screen.
     */
    public void gameBoundsByBlocks(GameLevel game, double width, double height) {
        //variables declaration:
        Color color = null;
        //variables represent the 3 bound blocks.
        double upperX = 0, upperY = BORDER_WIDTH, upperWidth = width, upperHeight = BORDER_WIDTH;
        double leftX = 0, leftY = BORDER_WIDTH, leftWidth = BORDER_WIDTH,
                leftHeight = height - BORDER_WIDTH;
        double rightX = width - BORDER_WIDTH, rightY = BORDER_WIDTH, rightWidth
                = BORDER_WIDTH, rightHeight = height - BORDER_WIDTH;

        //creating the 3 blocks.
        Block blockUpper = new Block(new Rectangle(upperX, upperY, upperWidth, upperHeight), color.GRAY);
        Block blockLeft = new Block(new Rectangle(leftX, leftY, leftWidth, leftHeight), color.gray);
        Block blockRight = new Block(new Rectangle(rightX, rightY, rightWidth, rightHeight), color.gray);

        //adding the 3 blocks to the game
        blockUpper.addToGame(game);
        blockLeft.addToGame(game);
        blockRight.addToGame(game);
    }

    /**
     * method for adding a "deadzone" to the game. occur by adding a "killing" block.
     * @param upperLeftX the x coordinate of the upper left point of the deadzone block.
     * @param upperLeftY the y coordinate of the upper left point of the deadzone block.
     * @param blockWidth the deadzone block width.
     * @param blockHeight the deadzone block height.
     * @param game the game variable.
     */
    public void deadZone(double upperLeftX, double upperLeftY, double blockWidth, double blockHeight, GameLevel game) {
        Block deadZone = new Block(new Rectangle(upperLeftX, upperLeftY,
                blockWidth, blockHeight), Block.randomColor());
        BallRemover remover2 = new BallRemover(this, this.counterBall);
        //adding the block to the hitlistener and game variable list.
        deadZone.addHitListener(remover2);
        deadZone.addToGame(game);
    }
    /**
     *  this method run the game - start the animation loop.
     */
    public void run() {
        //adding ball for the x > 1 rounds.
        if (counterBall.getValue() == 0) {
            addingBall(this.level.numberOfBalls());
        }
        // countdown before turn starts
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.running = true;
        this.runner.run(this);

    }

    /**
     * this method adding collidable objects to the environment of the game.
     * @param c - a collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * this method removing collidable objects to the environment of the game.
     * @param c - a collidable object.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }
    /**
     * this method adding sprite objects to the environment of the game.
     * @param s - a sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * this method remove sprite objects from the environment of the game.
     * @param s the sprite variable.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * getter for the block's counter.
     * @return the counter value.
     */
    public Counter getCounterBlock() {
        return counterBlock;
    }
    /**
     * getter for the ball's counter.
     * @return the counter value.
     */
    public Counter getCounterBall() {
        return counterBall;
    }
    /**
     * getter for live counter.
     * @return the live counter
     */
    public Counter getCounterLive() {
        return counterLive;
    }

    /**
     * getter to the game screen width.
     * @return the width
     */
    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    /**
     * getter to the game screen height.
     * @return the height
     */
    public static int getGameHeight() {
        return GAME_HIGHT;
    }

    /**
     *  the borders of the game width.
     * @return the width
     */
    public static int getBorderWidth() {
        return BORDER_WIDTH;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.level.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();
        // stopping condition
        // if there is no more lives - print "end screen" and close the game.
        if (counterLive.getValue() == 0) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "SPACE_KEY",
                    new EndScreen(this.counterPoint.getValue(), 1)));
            runner.getGui().close();
        }
        // if there is no balls in the game - decrease 1 live.
        if (counterBall.getValue() == 0) {
            counterLive.decrease(1);
            this.paddle.moveToMiddle();
            addingBall(this.level.numberOfBalls());
        }
        // this condition make the game keep a second after destroying the the last  block, so the
        // user will see the destroying of the last block.
        if ((counter % 4 == 1)) {
            this.running = false;
            this.counterPoint.increase(100);
        }
        if (counterBlock.getValue() == 0) {
            counter++;
        }

        // pausing the game when 'p' key is pressed.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "SPACE_KEY", new PauseScreen()));
        }
    }
    @Override
    public boolean shouldStop() {
         return !this.running;
    }
}
