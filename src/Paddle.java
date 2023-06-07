import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite {

  private int dx;
  private boolean paddle_big = PowerUp.paddle_big;
  private boolean paddle_fast = PowerUp.paddle_fast;

  public Paddle() {

    initPaddle();
  }

  private void initPaddle() {

    loadImage();
    getImageDimensions();

    resetState();
  }
  public void setPaddleBig(){
    paddle_big = true;
    loadImage();
  }
  public void setPaddleFast(){
    paddle_fast = true;
  }
  

  
  private  void loadImage() {

    if(paddle_big == false){
      var ii = new ImageIcon("src/images_breakout/paddle.png");
      image = ii.getImage();
    }else{
      var ii = new ImageIcon("src/images_breakout/paddle_big.png");
      image = ii.getImage();
    }
      
  }

  void move() {
    if(paddle_big){
      loadImage();
    }

    x += dx;

    if (x <= 0) {

      x = 0;
    }

    if (x >= Commons.WIDTH - imageWidth) {

      x = Commons.WIDTH - imageWidth;
    }
  }

  void keyPressed(KeyEvent e) {
    if(paddle_big){
      loadImage();
    }

    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {

      if(paddle_fast == true){
        dx = -3;
      }
      else{
        dx = -1;
      }

    }

    if (key == KeyEvent.VK_RIGHT) {

      if(paddle_fast == true){
        dx = 3;
      }
      else{
        dx = 1;
      }
    }
  }

  void keyReleased(KeyEvent e) {

    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {

      dx = 0;
    }

    if (key == KeyEvent.VK_RIGHT) {

      dx = 0;
    }
  }

  private void resetState() {
    if(paddle_big){
      loadImage();
    }

    x = Commons.INIT_PADDLE_X;
    y = Commons.INIT_PADDLE_Y;
  }
}
