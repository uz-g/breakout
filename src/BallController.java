import java.util.ArrayList;
import java.util.List;

public class BallController {

    private List<Ball> balls;

    public BallController(int numberOfBalls) {
        balls = new ArrayList<>();
        initBalls(numberOfBalls);
    }

    private void initBalls(int numberOfBalls) {
        for (int i = 0; i < numberOfBalls; i++) {
            balls.add(new Ball());
        }
    }

    public void move() {
        for (Ball ball : balls) {
            ball.move();
        }
    }

    public void resetState() {
        for (Ball ball : balls) {
            ball.resetState();
        }
    }

    public void setXDir(int index, int x) {
        balls.get(index).setXDir(x);
    }

    public void setYDir(int index, int y) {
        balls.get(index).setYDir(y);
    }

    public int getYDir(int index) {
        return balls.get(index).getYDir();
    }

    public Ball getBall(int index) {
        return balls.get(index);
    }

    public List<Ball> getBalls() {
        return balls;
    }
}
