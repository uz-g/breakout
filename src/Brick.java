import javax.swing.ImageIcon;

public class Brick extends Sprite {

  private boolean destroyed;
  private boolean l2 = Board.getl2();

  public Brick(int x, int y) {

    initBrick(x, y);
  }

  
  private void initBrick(int x, int y) {

    this.x = x;
    this.y = y;

    destroyed = false;

    loadImage();
    getImageDimensions();
  }

  private void loadImage() {

    if (l2) {
      var ii = new ImageIcon("src/images_breakout/brick2.png");
      image = ii.getImage();
    } else {
      var ii = new ImageIcon("src/images_breakout/brick.png");
      image = ii.getImage();
    }

    // image = ii.getImage();
  }

  boolean isDestroyed() {

    return destroyed;
  }

  void setDestroyed(boolean val) {

    destroyed = val;
  }

}
