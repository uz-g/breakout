import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main extends JFrame {

  public Main() {
        initUI();
  }

  private void initUI() {

    add(new Board());
    setTitle("Breakout");

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    pack();
  }

  
  public static void main(String[] args) {

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        Main ex = new Main();
        ex.setVisible(true);
      }
    });
  }
}