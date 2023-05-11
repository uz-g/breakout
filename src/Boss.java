import javax.swing.ImageIcon;

public class Boss extends Sprite {

  private boolean destroyed;
  public Boss(int x, int y) {

    initBoss(x, y);
  }

  
  private void initBoss(int x, int y) {

    this.x = x;
    this.y = y;

    destroyed = false;

    loadImage();
    getImageDimensions();
  }

  private void loadImage() {
    var ii = new ImageIcon("src/images_breakout/boss.png");
    image = ii.getImage();
  }

  boolean isDestroyed() {

    return destroyed;
  }

  void setDestroyed(boolean val) {

    destroyed = val;
  }

}
