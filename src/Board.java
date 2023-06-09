import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point; // identify whether ball collides with brick
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JFrame;


public class Board extends JPanel {

  private static Timer timer;
  private String message = "Game Over";
  private BallController ballController;
  private Ball ball;
  private Ball ball2;
  private Paddle paddle;
  private Brick[] bricks;
  private boolean inGame = true;
  private boolean done;
  private boolean power_done;
  int boss_hit = 0;

  public static int lvl = 0;
  private int rndm = (int) (Math.random() * 2) + 1;
  private int rndm2 = (int) (Math.random() * 3) + 1;
  // private int rndm = 1;
  // private int rndm2 = 1;
  //change later
  public static void setGameSpeed(int speed) {
    timer.setDelay(4);
    
  }

  public static boolean getl2() {
    return lvl == 1;
  }

  public static boolean getl3() {
    return lvl == 2;
  }

  public Board() {

    initBoard();
  }

  private void initBoard() {
    JOptionPane.showMessageDialog(null, "click ok to start the game");

   

    addKeyListener(new TAdapter());
    setFocusable(true);
    setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

    gameInit();
  }

  private void gameInit() {
    newGame(18, 1);
    // change later
  }

  private void gameInitLevel2() {
    done = false;
    lvl = 1;
    int num_bricks = rndm2;
    // Log the number of bricks
    // System.out.println("Number of bricks on x: " + num_bricks);
    newGame(num_bricks, 2); // half because x and y req
    // change later
  }

  private void gameInitLevel3() {
    done = false;
    lvl = 2;
    int num_bricks = 1;
    newGame(num_bricks, 1);
  }

  private void newGame(int NumberOfBricks, int numberOfBalls) {
    ballController = new BallController(numberOfBalls);
    // if lvl = 1, stop the timer
    if (lvl == 1) {
      timer.stop();
    }
    if (lvl == 2) {
      timer.stop();
    }
    if (lvl == 0) {
      power_done = false;
      bricks = new Brick[NumberOfBricks];

      ball = new Ball();
      paddle = new Paddle();

      int k = 0;

      for (int i = 0; i < 5; i++) {
        if (done)
          break;

        for (int j = 0; j < 6; j++) {
          if (done)
            break;

          bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
          k++;
          if (k == NumberOfBricks) {
            // break;
            done = true;
          }
        }
      }

      timer = new Timer(5, new GameCycle());
      timer.start();
    } else if (lvl == 1) {
      power_done = false;
      bricks = new Brick[NumberOfBricks * rndm];
      ball = new Ball();

      ball2 = new Ball();

      paddle = new Paddle();

      int k = 0;

      for (int i = 0; i < rndm; i++) {
        if (done)
          break;

        for (int j = 0; j < NumberOfBricks; j++) {
          if (done)
            break;

          bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
          k++;
          if (k == NumberOfBricks * rndm)
            done = true;
        }
      }

      // run through all the bricks in the array, and if the brick is null, send a
      // message using println
      for (int i = 0; i < bricks.length; i++) {
        if (bricks[i] == null) {
          System.out.println("bricks[" + i + "] is null");
        }
      }

      timer = new Timer((6), new GameCycle());
      timer.start();
      power_done = false;
    } else if (lvl == 2){
      power_done = true;
      done = false;
      bricks = new Brick[1];
      ball = new Ball();
      paddle = new Paddle();

      bricks[0] = new Brick(70, 40);


      for (int i = 0; i < bricks.length; i++) {
        if (bricks[i] == null) {
          System.out.println("bricks[" + i + "] is null");
        }
      }

      timer = new Timer(5, new GameCycle());
      timer.start();
    }

  }

  @Override
  public void paintComponent(Graphics g) {
  

    super.paintComponent(g);

    var g2d = (Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);

    if (inGame) {

      drawObjects(g2d);
    } else {

      gameFinished(g2d);
    }

    Toolkit.getDefaultToolkit().sync();
  }

  private void drawObjects(Graphics2D g2d) {
    g2d.setColor(Color.BLACK);
    g2d.setFont(new Font("Verdana", Font.BOLD, 12));
    g2d.drawString("level: " + (lvl + 1), 10, 20); // Prints "Level: X" at (10, 20)


    for (Ball ball : ballController.getBalls()) {
      g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
          ball.getImageWidth(), ball.getImageHeight(), this);
    }

    g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
        paddle.getImageWidth(), paddle.getImageHeight(), this);

    for (int i = 0; i < bricks.length; i++) {
      // if bricks is null, continue
      if (bricks[i] == null) {
        continue;
      }

      if (!bricks[i].isDestroyed()) {

        g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
            bricks[i].getY(), bricks[i].getImageWidth(),
            bricks[i].getImageHeight(), this);
      }
    }
  }

  private void gameFinished(Graphics2D g2d) {

    var font = new Font("Verdana", Font.BOLD, 18);
    FontMetrics fontMetrics = this.getFontMetrics(font);

    g2d.setColor(Color.BLACK);
    g2d.setFont(font);
    g2d.drawString(message,
        (Commons.WIDTH - fontMetrics.stringWidth(message)) / 2,
        Commons.WIDTH / 2);
  }

  private class TAdapter extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {

      paddle.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {

      paddle.keyPressed(e);
    }
  }

  private class GameCycle implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      doGameCycle();
    }
  }

  private void doGameCycle() {

    ballController.move();
    paddle.move();
    checkCollision();
    repaint();
  }

  private void stopGame() {

    inGame = false;
    timer.stop();
  }

  private void checkCollision() {
    for (Ball ball : ballController.getBalls()) {

      bounceOnBounds(ball);

      // ball goes off the bottom of the panel
      if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) { // returns largest y-coordinate of framing rectangle
        stopGame(); // you lose!
      }

      // determine whether user has destroyed all the bricks
      int j = 0;
      for (int i = 0; i < bricks.length; i++) {
        if (bricks[i] == null) {
          continue;
        }

        if (bricks[i].isDestroyed()) {
          j++;
        }
      }
      if (j==1 && lvl != 2 && power_done == false){
        PowerUp powerUp = new PowerUp(paddle);
        powerUp.activate_powerup();

        power_done = true;
      }

      if (j == bricks.length) {
        if (lvl == 1) {
          if (j == bricks.length) {
            gameInitLevel3();
            j=0;
          }
        }
        if (lvl == 2 && j == 1) {
          message = "victory";
          stopGame();
        }

        if (lvl == 0)
          gameInitLevel2();
      }

      // if ball and paddle collide
      if (sweptAABBCollision(ball.getRect(), paddle.getRect(), ball.getXDir(), ball.getYDir())) {
        // if ((ball.getRect()).intersects(paddle.getRect())) {

        // get the left most x-coordinate of the ball and paddle
        int paddleLPos = (int) paddle.getRect().getMinX();
        int ballLPos = (int) ball.getRect().getMinX();

        // paddle is 40 pixels wide; divide into 5 sections
        int first = paddleLPos + 8;
        int second = paddleLPos + 16;
        int third = paddleLPos + 24;
        int fourth = paddleLPos + 32;

        if (ballLPos < first) { // ball hits paddle in left corner

          ball.setXDir(-1); // to the left
          ball.setYDir(-1); // up
        }

        if (ballLPos >= first && ballLPos < second) { // ball hits panel left of center

          ball.setXDir(-1); // to the left
          ball.setYDir(-1 * ball.getYDir()); // reverse y-direction of ball
        }

        if (ballLPos >= second && ballLPos < third) {

          // ball moves straight up
          ball.setXDir(0);
          ball.setYDir(-1);
        }

        if (ballLPos >= third && ballLPos < fourth) {

          ball.setXDir(1);
          ball.setYDir(-1 * ball.getYDir()); // reverse y-direction ball
        }

        if (ballLPos > fourth) {

          ball.setXDir(1); // to the right
          ball.setYDir(-1); // up
        }
      }

      // if ball and brick collide
      for (int i = 0; i < bricks.length; i++) {
        if (bricks[i] != null) {
          if (sweptAABBCollision(ball.getRect(), bricks[i].getRect(), ball.getXDir(), ball.getYDir())) {
            // Add the brick collision logic here (same as before)
            // if (bricks[i] != null && (ball.getRect()).intersects(bricks[i].getRect())) {

            // get the coordinate of the upper left corner of the ball, as well as the ball
            // width and height
            int ballLeft = (int) ball.getRect().getMinX();
            int ballHeight = (int) ball.getRect().getHeight();
            int ballWidth = (int) ball.getRect().getWidth();
            int ballTop = (int) ball.getRect().getMinY();

            // determine points just above, below, to the left and right of ball
            var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
            var pointLeft = new Point(ballLeft - 1, ballTop);
            var pointTop = new Point(ballLeft, ballTop - 1);
            var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
            // this isdestroyed isnt broken
            if (!bricks[i].isDestroyed()) {

              if (bricks[i].getRect().contains(pointRight)) {

                ball.setXDir(-1); // the right hand side of the ball was touching the brick, so the ball must now
                                  // move left.
              } else if (bricks[i].getRect().contains(pointLeft)) {

                ball.setXDir(1);
              }

              if (bricks[i].getRect().contains(pointTop)) {

                ball.setYDir(1);
              } else if (bricks[i].getRect().contains(pointBottom)) {

                ball.setYDir(-1);
              }

              if(lvl == 2){
                if (boss_hit == 6){
                  bricks[0].setDestroyed(true);
                  boss_hit = 0;
                } else{
                  boss_hit++;
                }
              } else {
                bricks[i].setDestroyed(true);
              }

            }
          }
        }
      }
    }
  }

  private void bounceOnBounds(Ball ball) {
    Rectangle ballRect = ball.getRect();

    // Check if the ball has gone out of bounds on the left or right side
    if (ballRect.getMinX() < 0 || ballRect.getMaxX() > Commons.WIDTH) {
      ball.setXDir(-ball.getXDir()); // Reverse the x-direction of the ball
    }

    // Check if the ball has gone out of bounds on the top side
    if (ballRect.getMinY() < 0) {
      ball.setYDir(-ball.getYDir()); // Reverse the y-direction of the ball
    }
  }

  private boolean sweptAABBCollision(Rectangle ballRect, Rectangle targetRect, int xDir, int yDir) {
    // Check for a collision in the x-direction
    Rectangle expandedTargetX = new Rectangle(targetRect.x - xDir, targetRect.y, targetRect.width + Math.abs(xDir),
        targetRect.height);
    if (expandedTargetX.intersects(ballRect)) {
      // Check for a collision in the y-direction
      Rectangle expandedTargetY = new Rectangle(targetRect.x, targetRect.y - yDir, targetRect.width,
          targetRect.height + Math.abs(yDir));
      if (expandedTargetY.intersects(ballRect)) {
        return true;
      }
    }
    return false;
  }
}
