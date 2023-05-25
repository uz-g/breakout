import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerUp implements ActionListener {
    private Timer timer;
    private Paddle paddle;
    private BallController ballController;
    public static boolean paddle_big;
    public static boolean paddle_fast;

    public PowerUp() {
        this.paddle = paddle;
        this.ballController = ballController;
        timer = new Timer(5000, this); // Set the timer interval to 5 seconds (adjust as needed)
        timer.setRepeats(false); // Set the timer to only trigger once
    }

    public static boolean paddle_big() {
        return paddle_big;
    }
    public static boolean paddle_fast(){
        return paddle_fast;
    }

    public void activate_powerup() {
        // Choose a random power-up type
        int powerUpType = (int) (Math.random() * 3); // Generates a random number between 0 and 2

        // Apply the selected power-up effect
        switch (powerUpType) {
            case 0: // Larger paddle
                paddle_big = true;
                System.out.println("paddle_big");
                break;
            case 1: // Slower game
                Board.setGameSpeed(2);
                System.out.println("slower game");
                break;
            case 2: // Faster paddle
                System.out.println("paddle_fast");
                paddle_fast = true;
                break;
        }

        // Start the timer to deactivate the power-up after a certain duration
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Deactivate the power-up
        resetPowerUp();
    }

    private void resetPowerUp() {
        // Reset the effects of the power-up
        paddle_big = false;
        paddle_fast = false;
        Board.setGameSpeed(1);
    }
}
